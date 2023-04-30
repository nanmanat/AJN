package monster;


import entity.Entity;
import main.GamePanel;

public class MON_3 extends Entity {
    GamePanel gp;

    public MON_3(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "MON_3";
        maxLife = 20;
        life = maxLife;

        direction = "down";

        getImage();

    }
    
    public void getImage() {
        int i = 2;
        poImage = setup("/res/npc/MegaMind", gp.tileSize*i, gp.tileSize*i);
    }
    
}
