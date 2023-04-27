package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed , enterPressed , spacePressed, shotKeyPressed;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        // Title State
        if(gp.gameState == gp.titleState){
            titleState(code);
        }
        // Play State
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        // Show dialoge
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        // mini game Maze
        else if(gp.gameState == gp.miniGameMaze){
            miniGameMaze(code);
        }
        // over state
        else if(gp.gameState == gp.gameOverState){
            gameOverState(code);
        }
        // option state
        else if(gp.gameState == gp.optionsState){
            optionState(code);
        }
        //dialoge popUp
        else if(gp.gameState == gp.dialoguePopup){
            popUp(code);
        }
        else if(gp.gameState == gp.miniGameBlackJack){
            blackJack(code);
        }
        else if (gp.gameState == gp.blackjackScore) {
            blackJackScore(code);
        }
        else if(gp.gameState == gp.miniGameCode){
            miniGameCode(code);
        }
    }

    public void dialogueState (int code) {
        if(code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.tmpState;
        }
    }

    public void optionState(int code) {
        if(code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 3;
            }
            gp.playSE(1);
        }
        if(code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 3) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(1);
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0){
                if(code == KeyEvent.VK_LEFT){
                    if(gp.sound.volumeScale > 0){
                        gp.sound.volumeScale--;
                        gp.sound.checkVolume();
                        gp.playSE(1);
                    }
                }
                if(code == KeyEvent.VK_RIGHT){
                    if(gp.sound.volumeScale < 5){
                        gp.sound.volumeScale++;
                        gp.sound.checkVolume();
                        gp.playSE(1);
                    }
                }
            }
            if(gp.ui.commandNum == 1){
                //for SE
                if(code == KeyEvent.VK_LEFT){
                    if(gp.se.volumeScale > 0){
                        gp.se.volumeScale--;
                        gp.se.checkVolume();
                        gp.playSE(1);
                    }
                }
                if(code == KeyEvent.VK_RIGHT){
                    if(gp.se.volumeScale < 5){
                        gp.se.volumeScale++;
                        gp.se.checkVolume();
                        gp.playSE(1);
                    }
                }
            }
            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
            if(gp.ui.commandNum == 3){
                gp.gameState = gp.tmpState;
            }
        }
    }

    public void miniGameMaze(int code) {
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
            gp.tmpState = gp.miniGameMaze;
        }
    }

    public void miniGameCode(int code){
        if(code == KeyEvent.VK_LEFT){
            gp.ui.commandNum--;
            gp.playSE(1);
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
            gp.tmpState = gp.miniGameCode;
        }
        if(code == KeyEvent.VK_RIGHT){
            gp.ui.commandNum++;
            gp.playSE(1);
        }
        if(code == KeyEvent.VK_UP){
            gp.ui.subCommandNum--;
            gp.playSE(1);
        }
        if(code == KeyEvent.VK_DOWN){
            gp.ui.subCommandNum++;
            gp.playSE(1);
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.indexCodeGame <= 9){
                if(gp.ui.commandNum == 0) {
                    gp.code.addMove(0, gp.ui.indexCodeGame, true);
                }
                if(gp.ui.commandNum == 1) {
                    gp.code.addMove(1, gp.ui.indexCodeGame, true);
                }
                if(gp.ui.commandNum == 2) {
                    gp.code.addMove(2, gp.ui.indexCodeGame, true);
                }
                if(gp.ui.commandNum == 3) {
                    gp.code.addMove(3, gp.ui.indexCodeGame, true);
                }
                if(gp.ui.commandNum == 4) {
                    if(gp.ui.subCommandNum == 0){
                        // gp.code.print();
                        gp.code.movePlayer();
                        gp.code.resetMove();
                        gp.ui.indexCodeGame = 0;
                    }
                    else {
                        if(gp.ui.indexCodeGame > 0){
                            gp.ui.indexCodeGame--;
                            gp.code.clearMove(gp.ui.indexCodeGame);
                            System.out.println("clear");
                        }
                    }
                }
                else gp.ui.indexCodeGame++;
            }
            else {
                if(gp.ui.commandNum == 4) {
                    if(gp.ui.subCommandNum == 0){
                        // gp.code.print();
                        gp.code.movePlayer();
                        gp.code.resetMove();
                        gp.ui.indexCodeGame = 0;
                    }
                    else {
                        if(gp.ui.indexCodeGame > 0){
                            gp.ui.indexCodeGame--;
                            gp.code.clearMove(gp.ui.indexCodeGame);
                            System.out.println("clear");
                        }
                    }
                }
            }
        }
    }

    public void gameOverState(int code) {
        if(code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            gp.playSE(1);
        }
        if(code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            gp.playSE(1);
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0){
                //before mini game state
                gp.gameState = gp.playState;
                gp.retry(24,24);
            }
            if(gp.ui.commandNum == 1){
                gp.player.life = gp.player.maxLife;
                gp.gameState = gp.titleState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }

    }

    public void titleState(int code) {
        if(code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
            gp.playSE(1);
        }
        if(code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(1);
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                enterPressed = false;
            }
            if(gp.ui.commandNum == 1){
                //load
            }
            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
        }
    }

    public void playState (int code){
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if(code == KeyEvent.VK_M){
            gp.gameState = gp.miniGameMaze;
            gp.tmpState = gp.miniGameMaze;
            gp.retry(1,1);
        }
        if(code == KeyEvent.VK_C){
            gp.tmpState = gp.miniGameCode;
            gp.gameState = gp.miniGameCode;
            gp.player.setDefaultPositions(21,18);
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
            gp.tmpState = gp.playState;
        }
        if(code == KeyEvent.VK_SHIFT) {
            if(gp.player.direction == "up") {
                gp.player.worldY = gp.player.worldY - (gp.tileSize)/2;
            }
            if(gp.player.direction == "down") {
                gp.player.worldY = gp.player.worldY + (gp.tileSize)/2;
            }
            if(gp.player.direction == "left") {
                gp.player.worldX = gp.player.worldX - (gp.tileSize)/2;
            }
            if(gp.player.direction == "right") {
                gp.player.worldX = gp.player.worldX + (gp.tileSize)/2;
            }
        }
    }


    public void popUp(int code){
        if(code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }


    public void blackJackScore(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.playSE(1);
            if (gp.player.life == 0) {
                
            } else if (gp.blackJack.getElfLife() == 0) {
                gp.gameState = gp.playState;
                gp.gameState = gp.dialoguePopup;
                gp.ui.currentDialogue = " Oh no I'm dead! *dead elf sound* ";
            } else {
                gp.gameState = gp.miniGameBlackJack;
                gp.blackJack.reset();
                gp.blackJack.addBotCard();
            }
        }
    }

    public void blackJack(int code) {
        int maxCommandNum = 0;
        switch(gp.ui.bjState) {
            case 0: maxCommandNum = 1; break;
        }
        if(code == KeyEvent.VK_RIGHT){
            gp.ui.commandNum++;
            gp.playSE(1);
            if(gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_LEFT){
            gp.ui.commandNum--;
            gp.playSE(1);
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0 && gp.blackJack.userList.size() <= 5) {
                gp.blackJack.addUserCard();
                gp.blackJack.addBotCard();
            }
            if(gp.ui.commandNum == 1 && (gp.blackJack.playerScore() >= 15 || gp.blackJack.botScore() >= 20)) {
                if(gp.blackJack.botScore() < 17)
                {
                    while (gp.blackJack.botScore() < 17) {
                        gp.blackJack.addBotCard();
                    }
                    gp.playSE(4);
                } else {
                    gp.blackJack.end();
                }
            }
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
    }
    
}
