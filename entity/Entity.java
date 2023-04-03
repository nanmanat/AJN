package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1 ,down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public String dialogue[] = new String[20];

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setDialogue() {}
    public void speak() {}
}
