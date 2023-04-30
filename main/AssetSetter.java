package main;

// import entity.NPC_BigRock;
import entity.NPC_OldMan;
import entity.NPC_RockAnd;
import entity.NPC_RockNot;
import entity.NPC_RockOr;
import monster.MON_Dragon;
import tile_interactive.IT_AndPlate;
import tile_interactive.IT_NotPlate;
import tile_interactive.IT_OrPlate;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // int mapNum = 0;
        // int i = 0;

        // mapNum = 5;
        // gp.obj[mapNum][i] = new OBJ_Key(gp);
        // gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 31 * gp.tileSize;  
        // i++;  
        // gp.obj[mapNum][i] = new OBJ_Key(gp);
        // gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 33 * gp.tileSize;  
        // i++;  
        // gp.obj[mapNum][i] = new OBJ_Key(gp);
        // gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 29 * gp.tileSize;  
        // i++;  
    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        //Gate
        mapNum = 4;
        gp.npc[mapNum][i] = new NPC_RockAnd(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 24;
        gp.npc[mapNum][i].worldY = gp.tileSize * 28;
        i++;

        gp.npc[mapNum][i] = new NPC_RockOr(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 24;
        gp.npc[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.npc[mapNum][i] = new NPC_RockNot(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 24;
        gp.npc[mapNum][i].worldY = gp.tileSize * 32;
        i++;

        //Pokemomn
        mapNum = 7;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 24;
        gp.npc[mapNum][i].worldY = gp.tileSize * 14;

        // gp.npc[mapNum][4] = new NPC_BigRock(gp);
        // gp.npc[mapNum][4].worldX = gp.tileSize * 24;
        // gp.npc[mapNum][4].worldY = gp.tileSize * 28;

        //Casino
        mapNum = 5;
        gp.npc[mapNum][0] = new NPC_OldMan(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 24;
        gp.npc[mapNum][0].worldY = gp.tileSize * 26;
    }
    
    public void setMonster() {
        int mapNum = 0;

        mapNum = 1;
        gp.monster[mapNum][0] = new MON_Dragon(gp);
        gp.monster[mapNum][0].worldX = gp.tileSize * 25;
        gp.monster[mapNum][0].worldY = gp.tileSize * 24;

        gp.monster[mapNum][1] = new MON_Dragon(gp);
        gp.monster[mapNum][1].worldX = gp.tileSize * 19;
        gp.monster[mapNum][1].worldY = gp.tileSize * 12;

        gp.monster[mapNum][2] = new MON_Dragon(gp);
        gp.monster[mapNum][2].worldX = gp.tileSize * 13;
        gp.monster[mapNum][2].worldY = gp.tileSize * 30;

        gp.monster[mapNum][3] = new MON_Dragon(gp);
        gp.monster[mapNum][3].worldX = gp.tileSize * 19;
        gp.monster[mapNum][3].worldY = gp.tileSize * 35;

        gp.monster[mapNum][4] = new MON_Dragon(gp);
        gp.monster[mapNum][4].worldX = gp.tileSize * 31;
        gp.monster[mapNum][4].worldY = gp.tileSize * 18;

        gp.monster[mapNum][5] = new MON_Dragon(gp);
        gp.monster[mapNum][5].worldX = gp.tileSize * 25;
        gp.monster[mapNum][5].worldY = gp.tileSize * 35;

        gp.monster[mapNum][6] = new MON_Dragon(gp);
        gp.monster[mapNum][6].worldX = gp.tileSize * 36;
        gp.monster[mapNum][6].worldY = gp.tileSize * 23;
    }

    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;

        //Gate
        mapNum = 4;
        gp.iTile[mapNum][i] = new IT_AndPlate(gp,16,32);i++;
        gp.iTile[mapNum][i] = new IT_NotPlate(gp,18,22);i++;
        gp.iTile[mapNum][i] = new IT_OrPlate(gp,31,23);i++;
    }

}
