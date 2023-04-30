package entity;


import main.GamePanel;

public class NPC_Elfarm extends Entity {
    
    public NPC_Elfarm (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        collision = true;

        getImage();
    }

    public void getImage() {
        down1 = setup("/res/npc/elf_arm", gp.tileSize, gp.tileSize);
    }
}   
