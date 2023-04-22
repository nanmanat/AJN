package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Dragon extends Entity {

    public MON_Dragon(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Dragon";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        direction = "down";

        getImage();

    }
    
    public void getImage() {
        up1 = setup("res/monster/mon_1");
        up2 = setup("res/monster/mon_1");
        down1 = setup("res/monster/mon_1");
        down2 = setup("res/monster/mon_1");
        left1 = setup("res/monster/mon_1");
        left2 = setup("res/monster/mon_1");
        right1 = setup("res/monster/mon_1");
        right2 = setup("res/monster/mon_1");
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
    
}
