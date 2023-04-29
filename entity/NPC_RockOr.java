package entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import main.GamePanel;
import tile_interactive.IT_OrPlate;
import tile_interactive.InteractiveTile;

public class NPC_RockOr extends Entity {

    public static final String npcName = "Or Rock";
    
    public NPC_RockOr (GamePanel gp) {
        super(gp);

        name = npcName;

        speed = 3;
        collision = true;

        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 6;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 40;

        getImage();
    }

    public void getImage() {
        up1 = setup("/res/npc/27", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/27", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/27", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/27", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/27", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/27", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/27", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/27", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
    }

    public void update() {
    }
    
    public void move(String d) {

        this.direction = d;
        
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        int contactObj = gp.cChecker.checkEntity(this, gp.npc);

        if (!contactPlayer && contactObj == 999) {
            
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        detectPlate();
    }

    public void detectPlate() {
        ArrayList<InteractiveTile> plateList = new ArrayList<InteractiveTile>();
        ArrayList<Entity> rockList = new ArrayList<Entity>();

        //Create a plate list
        for (int i = 0; i < gp.iTile[1].length; i++) {

            if(gp.iTile[gp.currentMap][i] != null &&
                    gp.iTile[gp.currentMap][i].name.equals(IT_OrPlate.itName)) {
                plateList.add(gp.iTile[gp.currentMap][i]);
            }
        }
        //Create a rock list
        for (int i = 0; i < gp.npc[1].length; i++) {

            if(gp.npc[gp.currentMap][i] != null &&
                    gp.npc[gp.currentMap][i].name.equals(NPC_RockOr.npcName)) {
                rockList.add(gp.npc[gp.currentMap][i]);
            }
        }

        int count = 0;

        //Scan the plate list
        for (int i = 0; i < plateList.size(); i++) {
            int xDistance = Math.abs(worldX - plateList.get(i).worldX);
            int yDistance = Math.abs(worldY - plateList.get(i).worldY);
            int distance = Math.max(xDistance, yDistance);

            if(distance < 8) {
                
                if(linkedEntity == null) {
                    linkedEntity = plateList.get(i);
                    gp.player.gateScore++;
                    gp.playSE(3);
                }
            } else {
                if(linkedEntity == plateList.get(i)) {
                    gp.player.gateScore--;
                    linkedEntity = null;
                }
            }
        }

        //scan the rock list
        for (int i = 0; i < rockList.size(); i++) {
            if (rockList.get(i).linkedEntity != null) {
                count++;
            }
        }
        //if all rock on plates
        if(count == rockList.size()) {
            System.out.println(gp.player.gateScore);
            gp.playSE(3);
        }
    }
}   
