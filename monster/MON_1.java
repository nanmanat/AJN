package monster;


import entity.Entity;
import main.GamePanel;

public class MON_1 extends Entity {
    GamePanel gp;

    public MON_1(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "MON_1";
        maxLife = 20;
        life = maxLife;

        getImage();

    }
    
    public void getImage() {
        poImage = setup("/res/npc/Pie", gp.tileSize, gp.tileSize);
    }
    
}
