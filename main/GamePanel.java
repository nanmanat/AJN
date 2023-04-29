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
import tile_interactive.InteractiveTile;
import game.BlackJack;
import game.CodeGame;
import game.Gate;
import object.OBJ_Key;

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
    public final int maxMap = 10;
    public int currentMap = 0;

    //for full Screen
    public int screenWidth2 = screenWidth;
    public int screenHeight2 = screenHeight;
    public Graphics2D g2;

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
    public final int blackjackScore = 11;
    public final int gameOverState = 12;

    //system
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);
    public Sound sound = new Sound();
    public Sound se = new Sound();
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    public Thread gameThread;
    public EnvironmentManager eManager = new EnvironmentManager(this);
    public BlackJack blackJack = new BlackJack(this);
    public Gate gate = new Gate(this);
    public CodeGame code = new CodeGame(this);
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][20];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][20];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][5];
    public ArrayList<Entity> projectileList = new ArrayList<>();
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
        aSetter.setInteractiveTile();
        gameState = titleState;
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
            // System.out.println(gameState + " " + currentMap);

        }
    }

    public void retry(int x , int y) {
        player.restoreLife();
        player.setDefaultPositions(x,y);
        aSetter.setNPC();
        aSetter.setMonster();
    }

    public void update() {
        // player
        player.update();
        //monster
        for (int i = 0; i < monster[1].length; i++) {
            if (monster[currentMap][i] != null) {
                if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                    monster[currentMap][i].update();
                }
                if (monster[currentMap][i].alive == false) {
                    // monster[currentMap][i].checkDrop();
                    monster[currentMap][i] = null;
                }
            }
        }
        for (int i = 0; i < projectileList.size(); i++) {
            if (projectileList.get(i) != null) {
                if (projectileList.get(i).alive == true) {
                    projectileList.get(i).update();
                }
                if (projectileList.get(i).alive == false) {
                    projectileList.remove(i);
                }
            }
        }
        for (int i = 0; i < iTile[1].length; i++) {
            if (iTile[currentMap][i] != null) {
                iTile[currentMap][i].update();
            }
        }
        if(blackJack.getElfLife() == 0) {
            blackJack.checkDrop(24,31);
            blackJack.hurtElf();
        }
        if(player.gateScore == 3) {
            gate.checkDrop(24,36);
            player.gateScore = 4;
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
        else if(currentMap == 1){
            tileM.draw(g2);
            entityList.add(player);
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
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
        else if(currentMap == 2){
            tileM.draw(g2);
            entityList.add(player);
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
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
        else if(gameState == playState || tmpState == playState){
            //Tile
            tileM.draw(g2);

            //Interactive Tile
            for (int i = 0; i < iTile[1].length; i++) {
                if(iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            // add entities to entity list
            entityList.add(player);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
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
        se.setFile(i);
        se.play();
    }

    public void wait(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

