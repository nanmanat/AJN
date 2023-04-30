package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.*;
import object.OBJ_Fireball;

public class Player extends Entity{
    
    public KeyHandler keyH;
    
    public int gateScore = 0;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attack = 1;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttack();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 24;
        speed = 4;
        direction = "down";

        //player status
        maxLife = 6;
        life = maxLife;
        projectile = new OBJ_Fireball(gp);
    }

    public void setDefaultPositions(int x , int y) {
        worldX = gp.tileSize * x;
        worldY = gp.tileSize * y;
        direction = "down";
    }

    public void restoreLife() {
        life = maxLife;
        invincible = false;
    }

    public void getPlayerImage() {
        up1 = setup("/res/player/16Jae-11", gp.tileSize, gp.tileSize);
        up2 = setup("/res/player/16Jae-12", gp.tileSize, gp.tileSize);
        down1 = setup("/res/player/16Jae-2", gp.tileSize, gp.tileSize);
        down2 = setup("/res/player/16Jae-3", gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/16Jae-8", gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/16Jae-9", gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/16Jae-5", gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/16Jae-6", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttack() {

        attackUp1 = setup("/res/player/attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/res/player/attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackUp3 = setup("/res/player/attack_up_3", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/res/player/attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/res/player/attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackDown3 = setup("/res/player/attack_down_3", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/res/player/attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/res/player/attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackLeft3 = setup("/res/player/attack_left_3", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/res/player/attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/res/player/attack_right_2", gp.tileSize * 2, gp.tileSize);
        attackRight3 = setup("/res/player/attack_right_3", gp.tileSize * 2, gp.tileSize);
    }

    public void update() {

        if (attacking == true) {
            attacking();
        }

        else if (keyH.downPressed == true || keyH.upPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            } else if (keyH.enterPressed == true) {
                attacking = true;
            }

            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            contactNpc(npcIndex);

            //check event
            gp.eHandler.checkEvent();

            // if collision is false, player can move
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
        }

        if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30) {
            
            projectile.set(worldX, worldY, direction, true, this);

            gp.projectileList.add(projectile);

            shotAvailableCounter = 0;
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        if(life <= 0 && gp.gameState != gp.gameOverState) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.playSE(2);
        }

    }
    
    private void attacking() {

        spriteCounter++;
        if (spriteCounter <= 10) {
            spriteNum = 1;
        }
        if (spriteCounter > 10 && spriteCounter <= 20) {
            spriteNum = 2;

            // Save current worldX, worldY, and solidArea
            int currentworldX = worldX;
            int currentworldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX, worldY, and attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            
            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check m0onster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            // Set back
            worldX = currentworldX;
            worldY = currentworldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 20 && spriteCounter <= 30) {
            spriteNum = 2;
        }
        if (spriteCounter > 30) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {

        if (i != 999) {

            String objectName = gp.obj[gp.currentMap][i].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[gp.currentMap][i] = null;
                    System.out.println("Key: " + hasKey);
                    break;
            }
        }
    }
    
    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
                gp.playSE(3);
                life -= 1;
                invincible = true;
            }

        }
    }

    public void contactNpc(int i) {
        if (i != 999) {
            // if (invincible == false && gp.monster[i].dying == false) {
            //     life -= 1;
            //     invincible = true;
            // }
            gp.npc[gp.currentMap][i].move(direction);
        }
    }
    
    public void damageMonster(int i, int attack) {
        if (i != 999) {
            if (gp.monster[gp.currentMap][i].invincible == false) {
                knockBack(gp.monster[gp.currentMap][i]);
                gp.monster[gp.currentMap][i].life -= attack;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1)
                        image = up1;
                    if (spriteNum == 2)
                        image = up2;
                }
                if (attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1)
                        image = attackUp1;
                    if (spriteNum == 2)
                        image = attackUp2;
                    if (spriteNum == 3)
                        image = attackUp3;
                }
                break;
            case "down":
            if (!attacking) {
                    if (spriteNum == 1)
                        image = down1;
                    if (spriteNum == 2)
                        image = down2;
                }
                if (attacking) {
                    if (spriteNum == 1)
                        image = attackDown1;
                    if (spriteNum == 2)
                        image = attackDown2;
                    if (spriteNum == 3)
                        image = attackDown3;
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1)
                        image = left1;
                    if (spriteNum == 2)
                        image = left2;
                }
                if (attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1)
                        image = attackLeft1;
                    if (spriteNum == 2)
                        image = attackLeft2;
                    if (spriteNum == 3)
                        image = attackLeft3;
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1)
                        image = right1;
                    if (spriteNum == 2)
                        image = right2;
                }
                if (attacking) {
                    if (spriteNum == 1)
                        image = attackRight1;
                    if (spriteNum == 2)
                        image = attackRight2;
                    if (spriteNum == 3)
                        image = attackRight3;
                }
                break;
        }
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        
        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
    }

    public void knockBack( Entity entity){
        entity.direction = direction;
        entity.speed += 10;
        entity.knockBack = true;
    }

    public void setDialogue() {
        dialogue[0] = " ";
    }

    public void setMove(String direction){
        if(direction.equals("up")){
            this.direction = direction;
            worldY -= gp.tileSize;
        }
        if(direction.equals("down")){
            this.direction = direction;
            worldY += gp.tileSize;
        }
        if(direction.equals("left")){
            this.direction = direction;
            worldX -= gp.tileSize;
        }
        if(direction.equals("right")){
            this.direction = direction;
            worldX += gp.tileSize;
        }
        int playerX = ( worldX + solidArea.x ) / gp.tileSize;
        int playerY = ( worldY + solidArea.y ) / gp.tileSize;
        int tileNum = gp.tileM.mapTileNum[gp.currentMap][playerX][playerY];
        if(tileNum == 137 || tileNum == 140 || tileNum == 146 || tileNum == 143 || tileNum == 147){
            gp.code.resetMove();
            gp.player.life--;
            gp.eHandler.teleport(8, 19, 25);
            gp.player.worldY = 1200-15;
            gp.player.direction = "right";
        }
        
    }

}