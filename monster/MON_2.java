package monster;


import entity.Entity;
import main.GamePanel;

public class MON_2 extends Entity {
    GamePanel gp;

    public MON_2(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "MON_2";
        maxLife = 20;
        life = maxLife;

        direction = "down";

        getImage();

    }
    
    public void getImage() {
        int i = 2;
        poImage = setup("/res/npc/ThreeManDown", gp.tileSize*i, gp.tileSize*i);
    }
    
}
