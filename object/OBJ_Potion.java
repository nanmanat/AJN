package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Potion extends Entity {

    GamePanel gp;

    public OBJ_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Potion";

        down1 = setup("/res/objects/Potion", gp.tileSize, gp.tileSize);
    }
}
