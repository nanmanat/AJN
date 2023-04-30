package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_AJN extends Entity {
    GamePanel gp;

    public MON_AJN(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 3;
        name = "AJN";
        speed = 1;
        maxLife = 20;
        life = maxLife;
        direction = "down";
        
        solidArea.x = 4;
        solidArea.y = 4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 40;
        attackArea.width = 48;
        attackArea.height = 48;

        // projectile = new OBJ_Fireball(gp);

        getImage();
        getAttackImage();
    }
    
    public void getImage() {
        int i = 1;
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
        int i = 1;
        attackDown1 = setup("/res/monster/AJN_attack_down_1", gp.tileSize*i, gp.tileSize*i);
        attackDown2 = setup("/res/monster/AJN_attack_down_2", gp.tileSize*i, gp.tileSize*i);
        attackDown3 = setup("/res/monster/AJN_attack_down_3", gp.tileSize*i, gp.tileSize*i);
        attackDown4 = setup("/res/monster/AJN_attack_down_4", gp.tileSize*i, gp.tileSize*i);
        attackDown5 = setup("/res/monster/AJN_attack_down_5", gp.tileSize*i, gp.tileSize*i);

        attackLeft1 = setup("/res/monster/AJN_attack_left_1", gp.tileSize*i, gp.tileSize*i);
        attackLeft2 = setup("/res/monster/AJN_attack_left_2", gp.tileSize*i, gp.tileSize*i);
        attackLeft3 = setup("/res/monster/AJN_attack_left_3", gp.tileSize*i, gp.tileSize*i);
        attackLeft4 = setup("/res/monster/AJN_attack_left_4", gp.tileSize*i, gp.tileSize*i);
        attackLeft5 = setup("/res/monster/AJN_attack_left_5", gp.tileSize*i, gp.tileSize*i);

        attackRight1 = setup("/res/monster/AJN_attack_right_1", gp.tileSize*i, gp.tileSize*i);
        attackRight2 = setup("/res/monster/AJN_attack_right_2", gp.tileSize*i, gp.tileSize*i);
        attackRight3 = setup("/res/monster/AJN_attack_right_3", gp.tileSize*i, gp.tileSize*i);
        attackRight4 = setup("/res/monster/AJN_attack_right_4", gp.tileSize*i, gp.tileSize*i);
        attackRight5 = setup("/res/monster/AJN_attack_right_5", gp.tileSize*i, gp.tileSize*i);

        attackUp1 = setup("/res/monster/AJN_attack_up_1", gp.tileSize*i, gp.tileSize*i);
        attackUp2 = setup("/res/monster/AJN_attack_up_2", gp.tileSize*i, gp.tileSize*i);
        attackUp3 = setup("/res/monster/AJN_attack_up_3", gp.tileSize*i, gp.tileSize*i);
        attackUp4 = setup("/res/monster/AJN_attack_up_4", gp.tileSize*i, gp.tileSize*i);
        attackUp5 = setup("/res/monster/AJN_attack_up_5", gp.tileSize*i, gp.tileSize*i);
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
    }

    public void setAction() {
        if(onPath == true){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol,goalRow);
            
            if(projectile.alive == false && shotAvailableCounter == 180 ){
                projectile.set(worldX, worldY, direction, true, this);
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
        // direction = gp.player.direction;
        onPath = true;
    }
    
    public void checkDrop() {
        // dropItem(new OBJ_Key(gp));
    }
    
}
