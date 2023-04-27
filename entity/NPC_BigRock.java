package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_BigRock extends Entity {

    public static final String npcName = "BigRock";
    
    public NPC_BigRock (GamePanel gp) {
        super(gp);

        name = npcName;

        direction = "down";
        speed = 4;
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
        up1 = setup("/res/npc/bigrock", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/bigrock", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/bigrock", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/bigrock", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/bigrock", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/bigrock", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/bigrock", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/bigrock", gp.tileSize, gp.tileSize);
    }

    @Override
    public void setDialogue() {
        dialogue[0] = "Hello PiggyBooBoo";
        dialogue[1] = "Egy Chop Pen Sa-Goy";
        dialogue[2] = "Pai Gub Pu Boi Pu Pa Pai Skrt!";
    }

    public void setAction() {
    }

    public void update() {
    }
    
    public void move(String d) {

        this.direction = d;
        
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (!contactPlayer) {
            
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
    }

    @Override
    public void speak() {
        // super.speak();
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex++;
    }

    

}   
