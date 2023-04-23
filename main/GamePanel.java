package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    
    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile size
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile size
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 960 pixel width
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixel height

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //for full Screen
    public int screenWidth2 = screenWidth;
    public int screenHeight2 = screenHeight;
    Graphics2D g2;

    //FPS
    final int FPS = 60;

    //game state
    // private JFrame panel;
    public int gameState;
    public int tmpState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int optionsState = 4;
    public final int dialoguePopup = 5;
    public final int miniGameMaze = 6;
    public final int miniGamePuzzle = 7;
    public final int miniGameCode = 8;
    public final int miniGamePokemon = 9;
    public final int miniGameBlackJack = 10;
    public final int gameOverState = 12;

    //system
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);
    public Sound sound = new Sound();
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    public Thread gameThread;
    public EnvironmentManager eManager = new EnvironmentManager(this);

    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    public CollisionChecker cChecker = new CollisionChecker(this);
    
    public GamePanel() {
        // this.panel = panel;
        this.setPreferredSize(new Dimension( screenWidth , screenHeight ));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame(){

        aSetter.setNPC();
        aSetter.setObject();
        aSetter.setMonster();
        gameState = titleState;
        if(gameState == playState)
            playMusic(0);
        eManager.setup();
    }

    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; //0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null) {
            //1.Update: update information such as character position
            update();
            //2.Draw: draw the screen with updated info
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // System.out.println(player.life);

        }
    }

    public void update() {
        tileM.update();
        // player
        player.update();
        //monster
        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) {
                if (monster[i].alive == true && monster[i].dying == false) {
                    monster[i].update();
                }
                if (monster[i].alive == false) {
                    monster[i] = null;
                }
            }
        }
        if(gameState == pauseState){
            //nothing
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //title screen
        if(gameState  == titleState){
            ui.draw(g2);
        }
        //mini game Maze
        else if(gameState == miniGameMaze){

            tileM.draw(g2);
            entityList.add(player);
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }

            // draw entity
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            // empty entity list
            entityList.clear();

            // Enviroment (lightning)
            eManager.draw(g2);

            //UI
            ui.draw(g2);
            g2.dispose();
        }
        else {
            tileM.draw(g2);

            // add entities to entity list
            entityList.add(player);
            
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            //sort
            Collections.sort(entityList, new Comparator<Entity>(){

                @Override
                public int compare(Entity e1, Entity e2) {
                    
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
                
            });

            // draw entity
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            // empty entity list
            entityList.clear();

            //UI
            ui.draw(g2);
            g2.dispose();
        }
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }

}

