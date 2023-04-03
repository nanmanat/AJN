package object;

import java.io.FileInputStream;
import javax.imageio.ImageIO;

import main.GamePanel;

//ไม่ใช้ละ
public class OBJ_Setting extends SuperObject{
    
    public OBJ_Setting(GamePanel gp){
        worldX = gp.tileSize/2;
        worldY = gp.tileSize/2;
        name = "setting";
        try {
            image = ImageIO.read(new FileInputStream("src/res/objects/setting.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
