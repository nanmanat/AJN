package game;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Key;

public class Gate extends Entity {

    GamePanel gp;

    public Gate(GamePanel gp) {
        super(gp);
        this.gp = gp;
    }

    public void checkDrop(int x, int y) {
        dropItem(new OBJ_Key(gp),x,y);
    }
    
}
