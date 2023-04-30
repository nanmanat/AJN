package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class CodeGame {
    public boolean codeMove[][] = new boolean[4][10];
    private GamePanel gp;
    public BufferedImage up1, down1,  left1,  right1 , enter;
    public boolean endPoint = false;
    // up 0 down 1 left 2 right 3

    public CodeGame(GamePanel gp){
        this.gp = gp;
        resetMove();
        getButtomImage();
    }

    public void getButtomImage() {
        up1 = setup("/res/player/16Jae-11", gp.tileSize, gp.tileSize);
        down1 = setup("/res/player/16Jae-2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/16Jae-8", gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/16Jae-5", gp.tileSize, gp.tileSize);
        enter = setup("/res/monster/mon_1", gp.tileSize, gp.tileSize);
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaledImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    
    public void addMove(int direction , int index, boolean move){
        codeMove[direction][index] = move;
    }

    public void clearMove(int index){
        for (int i = 0; i < 4; i++) {
            codeMove[i][index] = false;
        }
    }

    public void movePlayer(){
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 4; i++) {
                if(codeMove[i][j] == true){
                    if(i==0) gp.player.setMove("up");
                    else if(i==1) gp.player.setMove("down");
                    else if(i==2) gp.player.setMove("left");
                    else if(i==3) gp.player.setMove("right");
                    gp.cChecker.checkTile(gp.player);
                    System.out.println(gp.player.collisionOn);
                    if(gp.player.collisionOn == true){
                        resetMove();
                        gp.player.life--;
                        gp.eHandler.teleport(8, 19, 25);
                        gp.player.worldY = 1200-15;
                        gp.player.direction = "right";
                    }
                }
            }
                // else if (gp.player.collisionOn == true && codeMove[i][j] == true){
                //     gp.currentMap = 8;
                //     gp.eHandler.teleport(8, 19, 25);
                //     gp.player.worldY = 1200-15;
                //     gp.player.direction = "right";
                //     gp.player.life--;
                //     resetMove();
                // }
        }
    }


    public void resetMove(){
        for (int i = 0; i < codeMove.length; i++) {
            for (int j = 0; j < codeMove[i].length; j++) {
                codeMove[i][j] = false;
            }
        }
    }

    public void print(){
        for (int i = 0; i < codeMove.length; i++) {
            for (int j = 0; j < codeMove[i].length; j++) {
                System.out.print(codeMove[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
