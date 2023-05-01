package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile {

    GamePanel gp;

    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        
        this.gp = gp;

        name = "Fireball";
        speed = 3;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        if(type == 0){ // player
            up1 = setup("/res/projectile/fireball_1", gp.tileSize, gp.tileSize);
            up2 = setup("/res/projectile/fireball_2", gp.tileSize, gp.tileSize);
            down1 = setup("/res/projectile/fireball_1", gp.tileSize, gp.tileSize);
            down2 = setup("/res/projectile/fireball_2", gp.tileSize, gp.tileSize);
            left1 = setup("/res/projectile/fireball_1", gp.tileSize, gp.tileSize);
            left2 = setup("/res/projectile/fireball_2", gp.tileSize, gp.tileSize);
            right1 = setup("/res/projectile/fireball_1", gp.tileSize, gp.tileSize);
            right2 = setup("/res/projectile/fireball_2", gp.tileSize, gp.tileSize);
        }
        else if(type == 3){ //AJN
            up1 = setup("/res/projectile/AJN_attack-1", gp.tileSize*2, gp.tileSize*2);
            up2 = setup("/res/projectile/AJN_attack-2", gp.tileSize*2, gp.tileSize*2);
            up3 = setup("/res/projectile/AJN_attack-3", gp.tileSize*2, gp.tileSize*2);
            down1 = setup("/res/projectile/AJN_attack-1", gp.tileSize*2, gp.tileSize*2);
            down2 = setup("/res/projectile/AJN_attack-2", gp.tileSize*2, gp.tileSize*2);
            down3 = setup("/res/projectile/AJN_attack-3", gp.tileSize*2, gp.tileSize*2);
            left1 = setup("/res/projectile/AJN_attack-1", gp.tileSize*2, gp.tileSize*2);
            left2 = setup("/res/projectile/AJN_attack-2", gp.tileSize*2, gp.tileSize*2);
            left3 = setup("/res/projectile/AJN_attack-3", gp.tileSize*2, gp.tileSize*2);
            right1 = setup("/res/projectile/AJN_attack-1", gp.tileSize*2, gp.tileSize*2);
            right2 = setup("/res/projectile/AJN_attack-2", gp.tileSize*2, gp.tileSize*2);
            right3 = setup("/res/projectile/AJN_attack-3", gp.tileSize*2, gp.tileSize*2);
        }
        else if(type == 2){ //mon
            up1 = setup("/res/projectile/fireball_up_1", gp.tileSize*2, gp.tileSize*2);
            up2 = setup("/res/projectile/fireball_up_2", gp.tileSize*2, gp.tileSize*2);
            down1 = setup("/res/projectile/fireball_down_1", gp.tileSize*2, gp.tileSize*2);
            down2 = setup("/res/projectile/fireball_down_2", gp.tileSize*2, gp.tileSize*2);
            left1 = setup("/res/projectile/fireball_left_1", gp.tileSize*2, gp.tileSize*2);
            left2 = setup("/res/projectile/fireball_left_2", gp.tileSize*2, gp.tileSize*2);
            right1 = setup("/res/projectile/fireball_right_1", gp.tileSize*2, gp.tileSize*2);
            right2 = setup("/res/projectile/fireball_right_2", gp.tileSize*2, gp.tileSize*2);
        }
        
    }

    
}
