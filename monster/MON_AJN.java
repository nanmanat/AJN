package monster;

import java.util.Random;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Fireball;
import object.OBJ_Paper;

public class MON_AJN extends Entity {
    GamePanel gp;

    public MON_AJN(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 3;
        name = "AJN";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 60;
        life = maxLife;
        direction = "down";
        
        solidArea.x = gp.tileSize/2;
        solidArea.y = gp.tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 40;
        attackArea.width = 48;
        attackArea.height = 48;

        projectile = new OBJ_Fireball(gp);

        getImage();
        getAttackImage();
    }
    
    public void getImage() {
        int i = 2;
        up1 = setup("/res/monster/non_4", gp.tileSize*i, gp.tileSize*i);
        up2 = setup("/res/monster/non_5", gp.tileSize*i, gp.tileSize*i);
        up3 = setup("/res/monster/non_6", gp.tileSize*i, gp.tileSize*i);
        down1 = setup("/res/monster/non_1", gp.tileSize*i, gp.tileSize*i);
        down2 = setup("/res/monster/non_2", gp.tileSize*i, gp.tileSize*i);
        down3 = setup("/res/monster/non_3", gp.tileSize*i, gp.tileSize*i);
        left1 = setup("/res/monster/non_11", gp.tileSize*i, gp.tileSize*i);
        left2 = setup("/res/monster/non_12", gp.tileSize*i, gp.tileSize*i);
        left3 = setup("/res/monster/non_13", gp.tileSize*i, gp.tileSize*i);
        left4 = setup("/res/monster/non_14", gp.tileSize*i, gp.tileSize*i);
        right1 = setup("/res/monster/non_7", gp.tileSize*i, gp.tileSize*i);
        right2 = setup("/res/monster/non_8", gp.tileSize*i, gp.tileSize*i);
        right3 = setup("/res/monster/non_9", gp.tileSize*i, gp.tileSize*i);
        right4 = setup("/res/monster/non_10", gp.tileSize*i, gp.tileSize*i);
    }

    public void getAttackImage() {
        int i = 2;
        attackDown1 = setup("/res/monster/AJN_attack_down_1", gp.tileSize*i, gp.tileSize*i*i);
        attackDown2 = setup("/res/monster/AJN_attack_down_2", gp.tileSize*i, gp.tileSize*i*i);
        attackDown3 = setup("/res/monster/AJN_attack_down_3", gp.tileSize*i, gp.tileSize*i*i);
        attackDown4 = setup("/res/monster/AJN_attack_down_4", gp.tileSize*i, gp.tileSize*i*i);
        attackDown5 = setup("/res/monster/AJN_attack_down_5", gp.tileSize*i, gp.tileSize*i*i);

        attackLeft1 = setup("/res/monster/AJN_attack_left_1", gp.tileSize*i*i, gp.tileSize*i);
        attackLeft2 = setup("/res/monster/AJN_attack_left_2", gp.tileSize*i*i, gp.tileSize*i);
        attackLeft3 = setup("/res/monster/AJN_attack_left_3", gp.tileSize*i*i, gp.tileSize*i);
        attackLeft4 = setup("/res/monster/AJN_attack_left_4", gp.tileSize*i*i, gp.tileSize*i);
        attackLeft5 = setup("/res/monster/AJN_attack_left_5", gp.tileSize*i*i, gp.tileSize*i);

        attackRight1 = setup("/res/monster/AJN_attack_right_1", gp.tileSize*i*i, gp.tileSize*i);
        attackRight2 = setup("/res/monster/AJN_attack_right_2", gp.tileSize*i*i, gp.tileSize*i);
        attackRight3 = setup("/res/monster/AJN_attack_right_3", gp.tileSize*i*i, gp.tileSize*i);
        attackRight4 = setup("/res/monster/AJN_attack_right_4", gp.tileSize*i*i, gp.tileSize*i);
        attackRight5 = setup("/res/monster/AJN_attack_right_5", gp.tileSize*i*i, gp.tileSize*i);

        attackUp1 = setup("/res/monster/AJN_attack_up_1", gp.tileSize*i, gp.tileSize*i*i);
        attackUp2 = setup("/res/monster/AJN_attack_up_2", gp.tileSize*i, gp.tileSize*i*i);
        attackUp3 = setup("/res/monster/AJN_attack_up_3", gp.tileSize*i, gp.tileSize*i*i);
        attackUp4 = setup("/res/monster/AJN_attack_up_4", gp.tileSize*i, gp.tileSize*i*i);
        attackUp5 = setup("/res/monster/AJN_attack_up_5", gp.tileSize*i, gp.tileSize*i*i);
    }

    public void update() {
        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance =  (xDistance + yDistance) / gp.tileSize;
        
        if(onPath == false && tileDistance < 10){
            int i = new Random().nextInt(100)+1;
            if( i > 50){
                onPath = true;
            }
        }
        if(onPath == true && tileDistance < 3 ){
            attacking = true;
        }
        if(onPath == true && tileDistance > 3 ){
            attacking = false;
        }
        if(onPath == true && tileDistance > 20){
            onPath = false;
        }
        if(attacking == true){
            attacking();
        }
        if(gp.player.invincible == true){
            attacking = false;
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 3;
            else if (spriteNum == 3)
                spriteNum = 4;
            else if (spriteNum == 4)
                spriteNum = 5;
            else if (spriteNum == 5)
                spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void setAction() {
        if(onPath == true){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol,goalRow);
            if(attacking == false){
                if(projectile.alive == false && shotAvailableCounter == 180 ){
                    projectile.set(worldX+(gp.tileSize/2), worldY+gp.tileSize, direction, true, this);
                    gp.projectileList.add(projectile);
                    shotAvailableCounter = 0;
                }
                if (shotAvailableCounter < 180) {
                    shotAvailableCounter++;
                }
            }
            else {

            }
        } 
        else {

            actionLockCounter++;

            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }


        if ( attacking == false) {
            checkAttackOrNot(30, gp.tileSize*2, gp.tileSize);
        }
    }

    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

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
                    if (spriteNum == 3)
                        image = up3;
                }
                if (attacking) {
                    tempScreenY = screenY - up1.getHeight();
                    if (spriteNum == 1)
                        image = attackUp1;
                    if (spriteNum == 2)
                        image = attackUp2;
                    if (spriteNum == 3)
                        image = attackUp3;
                    if (spriteNum == 4)
                        image = attackUp4;
                    if (spriteNum == 5)
                        image = attackUp5;
                }
                break;
            case "down":
            if (!attacking) {
                    if (spriteNum == 1)
                        image = down1;
                    if (spriteNum == 2)
                        image = down2;
                    if (spriteNum == 3)
                        image = down3;
                }
                if (attacking) {
                    if (spriteNum == 1)
                        image = attackDown1;
                    if (spriteNum == 2)
                        image = attackDown2;
                    if (spriteNum == 3)
                        image = attackDown3;
                    if (spriteNum == 4)
                        image = attackDown4;
                    if (spriteNum == 5)
                        image = attackDown5;
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1)
                        image = left1;
                    if (spriteNum == 2)
                        image = left2;
                    if (spriteNum == 3)
                        image = left3;
                    if (spriteNum == 4)
                        image = left4;
                }
                if (attacking) {
                    tempScreenX = screenX - left1.getWidth();
                    if (spriteNum == 1)
                        image = attackLeft1;
                    if (spriteNum == 2)
                        image = attackLeft2;
                    if (spriteNum == 3)
                        image = attackLeft3;
                    if (spriteNum == 4)
                        image = attackLeft4;
                    if (spriteNum == 5)
                        image = attackLeft5;
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1)
                        image = right1;
                    if (spriteNum == 2)
                        image = right2;
                    if (spriteNum == 3)
                        image = right3;
                    if (spriteNum == 4)
                        image = right4;
                }
                if (attacking) {
                    if (spriteNum == 1)
                        image = attackRight1;
                    if (spriteNum == 2)
                        image = attackRight2;
                    if (spriteNum == 3)
                        image = attackRight3;
                    if (spriteNum == 4)
                        image = attackRight4;
                    if (spriteNum == 5)
                        image = attackRight5;
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

    public void attacking() {

        spriteCounter++;
        if (spriteCounter <= 10) {
            spriteNum = 1;
        }
        // if (spriteCounter > 5 && spriteCounter <= 10) {
        //     spriteNum = 2;
        // }
        // if (spriteCounter > 10 && spriteCounter <= 15) {
        //     spriteNum = 3;
        // }
        if (spriteCounter > 10 && spriteCounter <= 20) {
            spriteNum = 4;
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

            // // Check m0onster collision
            // int monsterIndex = gp.cChecker.checkEntity(gp.player, gp.monster);
            // gp.player.damageMonster(monsterIndex, attack);

            if(gp.cChecker.checkPlayer(this) == true){
                gp.player.life--;
                attacking = false;
            }

            // Set back
            worldX = currentworldX;
            worldY = currentworldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 20 && spriteCounter <= 30) {
            spriteNum = 5;
        }
        if (spriteCounter > 30) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void checkAttackOrNot(int rate , int straight , int horizontal) {
        boolean targetInRange = false;
        int xDis = Math.abs(worldX - gp.player.worldX);
        int yDis = Math.abs(worldY - gp.player.worldY);

        switch (direction) {
            case "up":      if(gp.player.worldY < worldY && yDis < straight && xDis < horizontal) 
                            targetInRange = true; break;
            case "down":    if(gp.player.worldY > worldY && yDis < straight && xDis < horizontal)
                            targetInRange = true; break;
            case "left":    if(gp.player.worldX < worldX && xDis < straight && yDis < horizontal)
                            targetInRange = true; break;
            case "right":   if(gp.player.worldX > worldX && xDis < straight && yDis < horizontal)
                            targetInRange = true; break;
            default: break;
        }

        if(targetInRange == true){
            // check if it initiates an attack
            int i = new Random().nextInt(rate);
            if(i==0){
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }
    
    public void damageReaction() {
        actionLockCounter = 0;
        // direction = gp.player.direction;
        onPath = true;
    }
    
    public void checkDrop() {
        gp.stopMusic();
        dropItem(new OBJ_Paper(gp));
    }
    
}
