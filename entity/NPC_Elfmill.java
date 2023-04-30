package entity;


import main.GamePanel;

public class NPC_Elfmill extends Entity {
    
    public static final String npcName = "elfMill";
    
    public NPC_Elfmill (GamePanel gp) {
        super(gp);

        name = npcName;
        direction = "down";
        speed = 1;
        collision = true;

        getImage();
    }

    public void getImage() {
        down1 = setup("/res/npc/elf_mill", gp.tileSize, gp.tileSize);
    }
}   
