package entity;


import main.GamePanel;

public class NPC_Elfnew extends Entity {
    
    public NPC_Elfnew (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        collision = true;

        getImage();
    }

    public void getImage() {
        down1 = setup("/res/npc/elf_new", gp.tileSize, gp.tileSize);
    }
}   
