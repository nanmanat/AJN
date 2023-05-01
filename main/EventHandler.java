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
                gp.stopMusic();
                gp.playMusic(6);
                teleport(5, 24, 34);
            }
            else if (hit(5,24, 34, "down") == true) {
                gp.stopMusic();
                gp.playMusic(0);
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
                gp.ui.currentDialogue = "Hoffman: Come! join me at the table.";
                canTouchEvent = false;
            }
            //Millgame
            else if (hit(0,22, 28, "up") == true) {
                gp.stopMusic();
                gp.playMusic(8);
                teleport(4, 24, 39);
            }
            else if (hit(4,24, 39, "down") == true) {
                gp.stopMusic();
                gp.playMusic(0);
                teleport(0, 22, 28);
            }
            else if (hit(4,24, 36, "up") == true && gp.player.gateScore < 3) {
                gp.player.keyH.upPressed = false;
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "Albert Tripledore: A=F, B=T, C=T";
                canTouchEvent = false;
            }
            //Newgame
            else if (hit(0,26, 28, "up") == true) {
                gp.stopMusic();
                gp.playMusic(11);
                teleport(1, 11, 10);
            }
            else if (hit(1,13, 11, "up") == true) {
                gp.player.keyH.upPressed = false;
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "Alberto Andrey De Anghub: It's a maze.";
                canTouchEvent = false;
            }
            
            else if (hit(1,10, 24, "left") == true) teleport(1, 39, 12);
            else if (hit(1,39, 18, "right") == true) teleport(1, 10, 36);
            else if (hit(1,39, 24, "right") == true) teleport(1, 10, 30);
            else if (hit(1,39, 30, "right") == true) teleport(1, 10, 18);

            else if (hit(1,13, 38, "down") == true) teleport(1, 37, 9);
            else if (hit(1,19, 38, "down") == true) teleport(1, 31, 9);
            else if (hit(1,31, 38, "down") == true) teleport(1, 25, 9);
            else if (hit(1,19, 9, "up") == true) teleport(1, 25, 38);
            
            // check tile new map
            int playerX = ( gp.player.worldX + gp.player.solidArea.x ) / gp.tileSize;
            int playerY = ( gp.player.worldY + gp.player.solidArea.y ) / gp.tileSize;
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][playerX][playerY];
            if(tileNum == 102) spike(gp.playState);
            else if(tileNum == 104 || tileNum == 106) gp.player.life = gp.player.maxLife;

            //New end
            if (hit(1,37, 38, "down") == true) {
                gp.stopMusic();
                gp.playMusic(0);
                teleport(0, 26, 28);
            }
            //PuayStatue
            else if (hit(0,24, 21, "up") == true) {
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "PuayTheStatue: ...";
            }
            //Mookgame
            else if(hit(0,28,28,"up") == true) {
                gp.stopMusic();
                gp.playMusic(10);
                teleport(2, 23, 25);
            } 
            else if(hit(2,24,23,"up") == true && gp.code.endPoint != true) {
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "Hortense Hoo-Ha: Here is the key bye.";
            }
            else if(hit(2,27,24,"right") == true) {
                teleport(8, 19, 25);
                gp.keyH.rightPressed = false;
                gp.player.worldX = 912;
                gp.player.worldY = 1200-10;
                gp.player.direction = "right";    

                if(gp.code.endGame == true){
                    if(hit(2,27,24,"any") == true) {
                        gp.stopMusic();
                        gp.playMusic(0);
                        teleport(0,28,28);
                        gp.player.direction = "down";
                    }
                }
                else {
                    if(hit(2,27,24,"right") == true) {
                        teleport(8, 19, 25);
                        gp.player.worldX = 912;
                        gp.player.worldY = 1200-10;
                        gp.player.direction = "right";
                    }
                }
            } 
            
            //Pokemon
            if (hit(0,24, 28, "up") == true) {
                gp.stopMusic();
                gp.playMusic(7);
                teleport(7, 24, 24);
            }
            else if (hit(7,24, 24, "down") == true) {
                gp.stopMusic();
                gp.playMusic(0);
                teleport(0, 24, 28);
            }
            else if (hit(7,24, 16, "up") == true && gp.pingPong.poList.get(2).life > 0) {
                gp.keyH.upPressed = false;
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "TinkerDingle: You can't defeat me weakling!";
                canTouchEvent = false;
            }
            else if (hit(7,24, 15, "up") == true && gp.pingPong.poList.get(2).life > 0) {
                gp.gameState = gp.miniGamePokemon;
                gp.pingPong.reset();
            } else if (hit(7,24, 15, "up") == true && gp.pingPong.poList.get(2).life <= 0) {
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = "My pingpongmon is all dead!";
            }
            //AJN
            else if(hit(0,24,24, "up") == true && gp.player.hasKey >= 5){
                gp.stopMusic();
                gp.playMusic(9);
                teleport(3, 25, 25);
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
        if (gp.player.invincible == false){
            gp.player.invincible = true;
            gp.player.life -= 1;
        }
    }

    public void checkMove(){
        if(hit(8,31,24,"right") == true) {
            teleport(9, 20, 19);
            gp.player.direction = "down";
        }
        if(hit(9,30,27,"right") == true) {
            teleport(10, 18, 17);
            gp.player.direction = "right";
        }
        if(hit(10,31,27,"right") == true) {
            teleport(0, 28, 28);
            gp.code.endGame = true;
            gp.player.direction = "down";
        }
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
