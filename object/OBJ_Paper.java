package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Paper extends Entity {

    GamePanel gp;

    public OBJ_Paper(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Paper";

        down1 = setup("/res/objects/Paper", gp.tileSize, gp.tileSize);
    }
}
