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
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 17 * gp.tileSize;
        gp.obj[0].worldY = 18 * gp.tileSize;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;

        gp.npc[1] = new NPC_BigRock(gp);
        gp.npc[1].worldX = gp.tileSize * 22;
        gp.npc[1].worldY = gp.tileSize * 23;
    }
    
    public void setMonster() {
        gp.monster[0] = new MON_Dragon(gp);
        gp.monster[0].worldX = gp.tileSize * 27;
        gp.monster[0].worldY = gp.tileSize * 16;
    }
}
