package tile_interactive;

import main.GamePanel;

public class IT_AndPlate extends InteractiveTile {

    GamePanel gp;
    public static final String itName = "And Plate";
    
    public IT_AndPlate(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        direction = plateDirection;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        name = itName;

        down1 = setup("/res/tiles/7", gp.tileSize, gp.tileSize);
        up1 = setup("/res/npc/oldman_down_1", gp.tileSize, gp.tileSize);


        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
