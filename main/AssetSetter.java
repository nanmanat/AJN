package main;

import entity.NPC_BigRock;
import entity.NPC_OldMan;
import monster.MON_Dragon;
import object.OBJ_Key;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;

        if(gp.blackJack.getElfLife() == 0) {
            mapNum = 5;
            gp.obj[mapNum][0] = new OBJ_Key(gp);
            gp.obj[mapNum][0].worldX = 24 * gp.tileSize;
            gp.obj[mapNum][0].worldY = 31 * gp.tileSize;
        }
    }

    public void setNPC() {
        int mapNum = 0;

        //Gate
        mapNum = 4;
        gp.npc[mapNum][1] = new NPC_BigRock(gp);
        gp.npc[mapNum][1].worldX = gp.tileSize * 24;
        gp.npc[mapNum][1].worldY = gp.tileSize * 32;

        gp.npc[mapNum][2] = new NPC_BigRock(gp);
        gp.npc[mapNum][2].worldX = gp.tileSize * 24;
        gp.npc[mapNum][2].worldY = gp.tileSize * 30;

        gp.npc[mapNum][3] = new NPC_BigRock(gp);
        gp.npc[mapNum][3].worldX = gp.tileSize * 24;
        gp.npc[mapNum][3].worldY = gp.tileSize * 28;

        //Casino
        mapNum = 5;
        gp.npc[mapNum][0] = new NPC_OldMan(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 24;
        gp.npc[mapNum][0].worldY = gp.tileSize * 17;
    }
    
    public void setMonster() {
        int mapNum = 0;

        mapNum = 1;
        gp.monster[mapNum][0] = new MON_Dragon(gp);
        gp.monster[mapNum][0].worldX = gp.tileSize * 27;
        gp.monster[mapNum][0].worldY = gp.tileSize * 16;
    }
}
