package entity;


import main.GamePanel;

public class NPC_Elfmook extends Entity {
    
    public NPC_Elfmook (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        collision = true;

        getImage();
    }

    public void getImage() {
        down1 = setup("/res/npc/elf_mook", gp.tileSize, gp.tileSize);
    }
}   
