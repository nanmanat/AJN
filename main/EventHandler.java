package main;

public class EventHandler {
    
    GamePanel gp;
    EventRect eventRect[][][];
    int previosEventX, previosEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 20;
            eventRect[map][col][row].height = 20;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;

                if(row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }

    }

    public void checkEvent() {
        //Check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previosEventX);
        int yDistance = Math.abs(gp.player.worldY - previosEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if(canTouchEvent == true) {
            if (hit(0,25, 25, "any") == true) { 
                // spike(gp.dialoguePopup);
                spike(gp.dialoguePopup);
            }
            else if (hit(0,14, 16, "any") == true) {
                teleport(5, 24, 34);
            }
            else if (hit(5,24, 34, "any") == true) {
                teleport(0, 14, 16);
            }
            else if (hit(5,24, 19, "up") == true) {
                gp.gameState = gp.miniGameBlackJack;
                gp.blackJack.reset();
                gp.blackJack.addBotCard();
            }
            else if (hit(5,24, 20, "up") == true) {
                gp.player.keyH.upPressed = false;
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "Hoffman: Welcome Traveller.";
                canTouchEvent = false;
            }
            else if (hit(0,14, 14, "any") == true) {
                teleport(4, 24, 37);
            }
            else if (hit(4,24, 37, "any") == true) {
                teleport(0, 14, 14);
            }
            // if (move(27, 14, "right") == true) { 
            //     System.out.print("ye ");
            // }
        }

    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row])) {
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previosEventX = gp.player.worldX;
                    previosEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void spike(int gameStates) {
        gp.gameState = gameStates;
        // System.out.println(gp.gameState);

        gp.player.keyH.rightPressed = false;
        gp.ui.currentDialogue = "You got hit by a balls!";
        gp.player.life -= 1;
    }

    public void teleport(int map, int col, int row) {
        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        previosEventX = gp.player.worldX;
        previosEventY = gp.player.worldY;
        canTouchEvent = false;
    }

    // public boolean move(int eventCol, int eventRow, String reqDirection) {
    //     eventRect.width = gp.tileSize;
    //     eventRect.height = gp.tileSize;
    //     boolean move = false;
    //     gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
    //     gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
    //     eventRect.x = eventCol*gp.tileSize + eventRect.x;
    //     eventRect.y = eventRow*gp.tileSize + eventRect.y;

    //     if(gp.player.solidArea.intersects(eventRect)) {
    //         if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
    //             move = true;
    //         }
    //     }

    //     gp.player.solidArea.x = gp.player.solidAreaDefaultX;
    //     gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    //     eventRect.x = eventRectDefaultX;
    //     eventRect.y = eventRectDefaultY;

    //     return move;
    // }
}
