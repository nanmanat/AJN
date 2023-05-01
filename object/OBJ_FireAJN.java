package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_FireAJN extends Projectile{
    GamePanel gp;

    public OBJ_FireAJN(GamePanel gp) {
        super(gp);
        
        this.gp = gp;

        name = "FireAJN";
        speed = 3;
        maxLife = 200;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/res/monster/AJN_attack-1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/AJN_attack-2", gp.tileSize, gp.tileSize);
        up3 = setup("/res/monster/AJN_attack-3", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/AJN_attack-1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/AJN_attack-2", gp.tileSize, gp.tileSize);
        down3 = setup("/res/monster/AJN_attack-3", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/AJN_attack-1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/AJN_attack-2", gp.tileSize, gp.tileSize);
        left3 = setup("/res/monster/AJN_attack-3", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/AJN_attack-1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/AJN_attack-2", gp.tileSize, gp.tileSize);
        right3 = setup("/res/monster/AJN_attack-3", gp.tileSize, gp.tileSize);
    }
}
