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
            //Bj gwakgwak 3000
            if (hit(0,20, 28, "up") == true) {
                teleport(5, 24, 34);
            }
            else if (hit(5,24, 34, "down") == true) {
                gp.player.life = 6;
                teleport(0, 20, 28);
            }
            else if (hit(5,24, 28, "up") == true) {
                if (gp.blackJack.getElfLife() > 0){
                    gp.gameState = gp.miniGameBlackJack;
                    gp.blackJack.reset();
                    gp.blackJack.addBotCard();
                } else {
                    gp.gameState = gp.dialoguePopup;
                    gp.ui.currentDialogue = "Hoffman: I'm already dead remember?";
                }
            }
            else if (hit(5,24, 29, "up") == true && gp.blackJack.getElfLife() > 0) {
                gp.player.keyH.upPressed = false;
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "Hoffman: Welcome Traveller.";
                canTouchEvent = false;
            }
            //Millgame
            else if (hit(0,22, 28, "up") == true) {
                teleport(4, 24, 39);
            }
            else if (hit(4,24, 39, "down") == true) {
                teleport(0, 22, 28);
            }
            else if (hit(4,24, 38, "up") == true && gp.player.gateScore < 3) {
                gp.player.keyH.upPressed = false;
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "Elf: Easy And-Or-Not gate puzzle.";
                canTouchEvent = false;
            }
            //Newgame
            else if (hit(0,26, 28, "up") == true) {
                teleport(1, 1, 1);
            }
            else if (hit(1,1, 1, "up") == true) {
                teleport(0, 26, 28);
            }
            //PuayStatue
            else if (hit(0,24, 21, "up") == true) {
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "PuayTheStatue: ...";
            }
            //Mookgame
            else if(hit(0,28,28,"up") == true) {
                teleport(2, 19, 18);
            }
            else if(hit(2,28,28,"up") == true) {
                teleport(0, 28, 28);
            }
            else if(hit(2,21,18,"any") == true) {
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "Your ready!";
            }
            if(hit(2,25,20,"down") == true) {
                gp.gameState = gp.playState;
            }
            
            //Pokemon
            else if (hit(0,24, 28, "up") == true) {
                teleport(7, 24, 24);
            }
            else if (hit(7,24, 24, "down") == true) {
                teleport(0, 24, 28);
            }
            else if (hit(7,24, 20, "up") == true) {
                gp.gameState = gp.miniGamePokemon;
            }
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

    

    public boolean checkMove(){
        if (hit(2 , 22, 18,"any") == true) {
            return true;
        }
        else if (hit(2,23, 18, "any") == true) { 
            return true;
        }
        else if (hit(2,24, 18, "any") == true) { 
            return true;
        }
        else if (hit(2,25, 18, "any") == true) { 
            return true;
        }
        else if (hit(2,25, 19, "any") == true) { 
            return true;
        }
        else return false;
    }
    public void teleport(int map, int col, int row) {
        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        previosEventX = gp.player.worldX;
        previosEventY = gp.player.worldY;
        canTouchEvent = false;
    }

}
