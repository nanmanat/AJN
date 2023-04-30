package entity;


import main.GamePanel;

public class NPC_dog extends Entity {
    
    public NPC_dog (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        collision = true;

        getImage();
    }

    public void getImage() {
        down1 = setup("/res/npc/dog", gp.tileSize, gp.tileSize);
    }
}   
