package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackDown1, attackDown2, attackDown3, attackLeft1,
    attackLeft2, attackLeft3, attackRight1, attackRight2, attackRight3;
    public String direction = "down";
    public static String plateDirection = "down";
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public int type; // 0 = player, 1 = npc, 2 = monster
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public Projectile projectile;
    public int maxMana;
    public int mana;
    public int useCost;
    public int attack;
    public int value;
    public Entity linkedEntity;

    // counter
    public int invincibleCounter = 0;
    public int actionLockCounter = 0;
    public int spriteCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;

    public int dialogueIndex = 0;
    public String dialogue[] = new String[20];

    // CHARACTER STATUS
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {
    }

    public void damageReaction() {
    }

    public void checkDrop() {}  

    public void checkDrop(int x, int y) {}

    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public void dropItem(Entity droppedItem, int x, int y) {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = gp.tileSize * x;
                gp.obj[gp.currentMap][i].worldY = gp.tileSize * y;
                break;
            }
        }
    }
    
    public void move(String direction) {
    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gp.player.invincible == false) {
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 1;
            spriteCounter = 0;
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1)
                        image = up1;
                    else if (spriteNum == 2)
                        image = up2;
                    break;
                case "down":
                    if (spriteNum == 1)
                        image = down1;
                    else if (spriteNum == 2)
                        image = down2;
                    break;
                case "left":
                    if (spriteNum == 1)
                        image = left1;
                    else if (spriteNum == 2)
                        image = left2;
                    break;
                case "right":
                    if (spriteNum == 1)
                        image = right1;
                    else if (spriteNum == 2)
                        image = right2;
                    break;
            }

            // Monster HP bar
            if (type == 2) {

                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
            }


            if (invincible == true) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            if (dying == true) {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
    
    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;

        if (dyingCounter <= 5) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > 5 && dyingCounter <= 10) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > 10 && dyingCounter <= 15) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > 15 && dyingCounter <= 20) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > 20 && dyingCounter <= 25) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > 25 && dyingCounter <= 30) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > 30 && dyingCounter <= 35) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > 35 && dyingCounter <= 40) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > 40) {
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
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

    public void setDialogue() {}
    public void speak() {}
}
