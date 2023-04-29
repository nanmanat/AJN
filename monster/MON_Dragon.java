package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Key;

public class MON_Dragon extends Entity {
    GamePanel gp;

    public MON_Dragon(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Dragon";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        direction = "down";

        getImage();

    }
    
    public void getImage() {
        int i = 2;
        up1 = setup("/res/monster/mon_1", gp.tileSize*i, gp.tileSize*i);
        up2 = setup("/res/monster/mon_1", gp.tileSize*i, gp.tileSize*i);
        down1 = setup("/res/monster/mon_1", gp.tileSize*i, gp.tileSize*i);
        down2 = setup("/res/monster/mon_1", gp.tileSize*i, gp.tileSize*i);
        left1 = setup("/res/monster/mon_1", gp.tileSize*i, gp.tileSize*i);
        left2 = setup("/res/monster/mon_1", gp.tileSize*i, gp.tileSize*i);
        right1 = setup("/res/monster/mon_1", gp.tileSize*i, gp.tileSize*i);
        right2 = setup("/res/monster/mon_1", gp.tileSize*i, gp.tileSize*i);
    }

    public void setAction() {
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
    
    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
    
    public void checkDrop() {
        dropItem(new OBJ_Key(gp));
    }
    
}
