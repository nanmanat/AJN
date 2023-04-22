package object;

import entity.Entity;
import main.GamePanel;

//ไม่ใช้ละ
public class OBJ_Setting extends Entity{
    
    public OBJ_Setting(GamePanel gp) {
        super(gp);
        worldX = gp.tileSize/2;
        worldY = gp.tileSize/2;
        name = "setting";
        image = setup("/res/objects/setting", gp.tileSize, gp.tileSize);
    }

}
