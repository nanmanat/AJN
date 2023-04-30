package entity;


import main.GamePanel;

public class NPC_OldMan extends Entity {
    
    public NPC_OldMan (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        collision = true;

        getImage();
    }

    public void getImage() {
        up1 = setup("/res/npc/elf_arm", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/elf_arm", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/elf_arm", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/elf_arm", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/elf_arm", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/elf_arm", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/elf_arm", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/elf_arm", gp.tileSize, gp.tileSize);
    }

    @Override
    public void setDialogue() {
        dialogue[0] = "Hello";
    }

    @Override
    public void speak() {
        // super.speak();
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex++;
    }

    public void setAction() {
    }

}   
