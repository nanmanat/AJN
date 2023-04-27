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

        if (hit(27, 16, "right") == true) { 
            // spike(gp.dialoguePopup);
            System.out.print("yo ");
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
        // System.out.println(gp.gameState);
        gp.player.keyH.rightPressed = false;
        gp.ui.currentDialogue = "You got hit by a balls!";
        gp.player.life -= 1;
    }

    public boolean move(int eventCol, int eventRow, String reqDirection) {
        eventRect.width = 16;
        eventRect.height = 16;
        boolean move = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                move = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return move;
    }

    public boolean checkMove(){
        if (move(22, 18, "any") == true) { 
            return true;
        }
        if (move(23, 18, "any") == true) { 
            return true;
        }
        if (move(24, 18, "any") == true) { 
            return true;
        }
        // if(gp.player.solidArea.intersects(eventRect)) {
        //     return true;
        // }
        else return false;
    }
}
