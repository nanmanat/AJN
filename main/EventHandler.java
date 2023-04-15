package main;

import java.awt.Rectangle;

public class EventHandler {
    
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 20;
        eventRect.height = 20;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

    }

    public void checkEvent() {

        if(hit(27,16,"right") == true) {
            spike(gp.dialogueSpike);
        }

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void spike(int gameStates) {
        gp.gameState = gameStates;
        gp.ui.currentDialogue = "You got hit by a spike!";
        gp.player.life -= 1;
    }


}
