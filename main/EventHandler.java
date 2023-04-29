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
                teleport(1, 11, 10);
            }
            else if (hit(1,15, 12, "right") == true) teleport(1, 17, 12);
            else if (hit(1,17, 12, "left") == true) teleport(1, 15, 12);
            else if (hit(1,21, 12, "right") == true) teleport(1, 23, 12);
            else if (hit(1,23, 12, "left") == true) teleport(1, 21, 12);
            else if (hit(1,27, 12, "right") == true) teleport(1, 29, 12);
            else if (hit(1,29, 12, "left") == true) teleport(1, 27, 12);
            else if (hit(1,33, 12, "right") == true) teleport(1, 35, 12);
            else if (hit(1,35, 12, "left") == true) teleport(1, 33, 12);

            else if (hit(1,15, 18, "right") == true) teleport(1, 17, 18);
            else if (hit(1,17, 18, "left") == true) teleport(1, 15, 18);
            else if (hit(1,21, 18, "right") == true) teleport(1, 23, 18);
            else if (hit(1,23, 18, "left") == true) teleport(1, 21, 18);
            else if (hit(1,27, 18, "right") == true) teleport(1, 29, 18);
            else if (hit(1,29, 18, "left") == true) teleport(1, 27, 18);
            else if (hit(1,33, 18, "right") == true) teleport(1, 35, 18);
            else if (hit(1,35, 18, "left") == true) teleport(1, 33, 18);

            else if (hit(1,15, 24, "right") == true) teleport(1, 17, 24);
            else if (hit(1,17, 24, "left") == true) teleport(1, 15, 24);
            else if (hit(1,21, 24, "right") == true) teleport(1, 23, 24);
            else if (hit(1,23, 24, "left") == true) teleport(1, 21, 24);
            else if (hit(1,27, 24, "right") == true) teleport(1, 29, 24);
            else if (hit(1,29, 24, "left") == true) teleport(1, 27, 24);
            else if (hit(1,33, 24, "right") == true) teleport(1, 35, 24);
            else if (hit(1,35, 24, "left") == true) teleport(1, 33, 24);

            else if (hit(1,15, 30, "right") == true) teleport(1, 17, 30);
            else if (hit(1,17, 30, "left") == true) teleport(1, 15, 30);
            else if (hit(1,21, 30, "right") == true) teleport(1, 23, 30);
            else if (hit(1,23, 30, "left") == true) teleport(1, 21, 30);
            else if (hit(1,27, 30, "right") == true) teleport(1, 29, 30);
            else if (hit(1,29, 30, "left") == true) teleport(1, 27, 30);
            else if (hit(1,33, 30, "right") == true) teleport(1, 35, 30);
            else if (hit(1,35, 30, "left") == true) teleport(1, 33, 30);

            else if (hit(1,15, 36, "right") == true) teleport(1, 17, 36);
            else if (hit(1,17, 36, "left") == true) teleport(1, 15, 36);
            else if (hit(1,21, 36, "right") == true) teleport(1, 23, 36);
            else if (hit(1,23, 36, "left") == true) teleport(1, 21, 36);
            else if (hit(1,27, 36, "right") == true) teleport(1, 29, 36);
            else if (hit(1,29, 36, "left") == true) teleport(1, 27, 36);
            else if (hit(1,33, 36, "right") == true) teleport(1, 35, 36);
            else if (hit(1,35, 36, "left") == true) teleport(1, 33, 36);

            else if (hit(1,13, 14, "down") == true) teleport(1, 13, 16);
            else if (hit(1,13, 16, "up") == true) teleport(1, 13, 14);
            else if (hit(1,13, 20, "down") == true) teleport(1, 13, 22);
            else if (hit(1,13, 22, "up") == true) teleport(1, 13, 20);
            else if (hit(1,13, 26, "down") == true) teleport(1, 13, 28);
            else if (hit(1,13, 28, "up") == true) teleport(1, 13, 26);
            else if (hit(1,13, 32, "down") == true) teleport(1, 13, 34);
            else if (hit(1,13, 34, "up") == true) teleport(1, 13, 32);

            else if (hit(1,19, 14, "down") == true) teleport(1, 19, 16);
            else if (hit(1,19, 16, "up") == true) teleport(1, 19, 14);
            else if (hit(1,19, 20, "down") == true) teleport(1, 19, 22);
            else if (hit(1,19, 22, "up") == true) teleport(1, 19, 20);
            else if (hit(1,19, 26, "down") == true) teleport(1, 19, 28);
            else if (hit(1,19, 28, "up") == true) teleport(1, 19, 26);
            else if (hit(1,19, 32, "down") == true) teleport(1, 19, 34);
            else if (hit(1,19, 34, "up") == true) teleport(1, 19, 32);

            else if (hit(1,25, 14, "down") == true) teleport(1, 25, 16);
            else if (hit(1,25, 16, "up") == true) teleport(1, 25, 14);
            else if (hit(1,25, 20, "down") == true) teleport(1, 25, 22);
            else if (hit(1,25, 22, "up") == true) teleport(1, 25, 20);
            else if (hit(1,25, 26, "down") == true) teleport(1, 25, 28);
            else if (hit(1,25, 28, "up") == true) teleport(1, 25, 26);
            else if (hit(1,25, 32, "down") == true) teleport(1, 25, 34);
            else if (hit(1,25, 34, "up") == true) teleport(1, 25, 32);

            else if (hit(1,31, 14, "down") == true) teleport(1, 31, 16);
            else if (hit(1,31, 16, "up") == true) teleport(1, 31, 14);
            else if (hit(1,31, 20, "down") == true) teleport(1, 31, 22);
            else if (hit(1,31, 22, "up") == true) teleport(1, 31, 20);
            else if (hit(1,31, 26, "down") == true) teleport(1, 31, 28);
            else if (hit(1,31, 28, "up") == true) teleport(1, 31, 26);
            else if (hit(1,31, 32, "down") == true) teleport(1, 31, 34);
            else if (hit(1,31, 34, "up") == true) teleport(1, 25, 32);

            else if (hit(1,37, 14, "down") == true) teleport(1, 37, 16);
            else if (hit(1,37, 16, "up") == true) teleport(1, 37, 14);
            else if (hit(1,37, 20, "down") == true) teleport(1, 37, 22);
            else if (hit(1,37, 22, "up") == true) teleport(1, 37, 20);
            else if (hit(1,37, 26, "down") == true) teleport(1, 37, 28);
            else if (hit(1,37, 28, "up") == true) teleport(1, 37, 26);
            else if (hit(1,37, 32, "down") == true) teleport(1, 37, 34);
            else if (hit(1,37, 34, "up") == true) teleport(1, 25, 32);
            //New end
            else if (hit(1,37, 37, "right") == true) {
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
            else if (hit(7,24, 20, "up") == true && gp.pingPong.poList.get(2).life > 0) {
                gp.gameState = gp.miniGamePokemon;
                gp.pingPong.reset();
            } else if (hit(7,24, 20, "up") == true && gp.pingPong.poList.get(2).life <= 0) {
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "My pingpongmon is all dead!";
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
