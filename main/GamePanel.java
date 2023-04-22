package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
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
    public BufferedImage tempScreen;
    public boolean fullScreenOn = false;
    Graphics2D g2;

    //FPS
    final int FPS = 60;

    //game state
    // private JFrame panel;
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialoguePlayerState = 3;
    public final int dialogueAJN = 4;
    public final int optionsState = 5;
    public final int dialoguePopup = 6;

    //system
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);
    public Sound sound = new Sound();
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    public Thread gameThread;

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
        if(gameState == dialoguePlayerState)
            playMusic(0);
        tempScreen = new BufferedImage( screenWidth , screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
        // setFullScreen();

    }

    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; //0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null) {
            //1.Update: update information such as character position
            update();
            //2.Draw: draw the screen with updated info
            // repaint();
            drawToTempScreen(); //draw everything to the buffered image
            drawToScreen(); //draw the buffered image to the screen
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
                monster[i].update();
            }
        }
    }
    
    public void setFullScreen(){
        //get local screen device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //get full screen width and height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2 , screenHeight2 , null);
        g.dispose();
    }

    public void drawToTempScreen(){
        //title screen
        if(gameState  == titleState){
            ui.draw(g2);
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

            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
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
            
            ui.draw(g2);
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

