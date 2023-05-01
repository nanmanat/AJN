package monster;

import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Fireball;

public class MON_Dragon extends Entity {
    GamePanel gp;

    public MON_Dragon(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Dragon";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 2;
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

    }
    
    public void getImage() {
        int i = 2;
        up1 = setup("/res/monster/mon_7", gp.tileSize*i, gp.tileSize*i);
        up2 = setup("/res/monster/mon_8", gp.tileSize*i, gp.tileSize*i);
        down1 = setup("/res/monster/mon_9", gp.tileSize*i, gp.tileSize*i);
        down2 = setup("/res/monster/mon_10", gp.tileSize*i, gp.tileSize*i);
        left1 = setup("/res/monster/mon_1", gp.tileSize*i, gp.tileSize*i);
        left2 = setup("/res/monster/mon_2", gp.tileSize*i, gp.tileSize*i);
        left3 = setup("/res/monster/mon_3", gp.tileSize*i, gp.tileSize*i);
        right1 = setup("/res/monster/mon_4", gp.tileSize*i, gp.tileSize*i);
        right2 = setup("/res/monster/mon_5", gp.tileSize*i, gp.tileSize*i);
        right3 = setup("/res/monster/mon_6", gp.tileSize*i, gp.tileSize*i);
    }

    public void update() {
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance =  (xDistance + yDistance) / gp.tileSize;
        
        if(onPath == false && tileDistance < 5){
            int i = new Random().nextInt(100)+1;
            if( i > 50){
                onPath = true;
            }
        }
        if(onPath == true && tileDistance > 10){
            onPath = false;
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if(direction == "up" || direction == "down"){
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;
                else 
                    spriteNum = 1;
            }
            else if (direction == "left" || direction == "right"){
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 3;
                else if (spriteNum == 3)
                    spriteNum = 1;
                else 
                    spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void setAction() {
        if(onPath == true){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol,goalRow);
            
            if(projectile.alive == false && shotAvailableCounter == 180 ){
                projectile.set(worldX+(gp.tileSize/2), worldY+(gp.tileSize/2), direction, true, this);
                gp.projectileList.add(projectile);
                shotAvailableCounter = 0;
            }
            if (shotAvailableCounter < 180) {
                shotAvailableCounter++;
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
    }
    
    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
    }
}
