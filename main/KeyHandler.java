package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed , enterPressed , spacePressed;
    
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
        else if(gp.gameState == gp.optionsState){
            optionState(code);
        }
        //dialoge popUp
        else if(gp.gameState == gp.dialoguePopup){
            if(code == KeyEvent.VK_ENTER) {
                // upPressed = true;
                gp.gameState = gp.playState;
            }
            // if(code == KeyEvent.VK_A) {
            //     leftPressed = true;
            //     gp.gameState = gp.playState;
            // }
            // if(code == KeyEvent.VK_S) {
            //     downPressed = true;
            //     gp.gameState = gp.playState;
            // }
            // if(code == KeyEvent.VK_D) {
            //     rightPressed = true;
            //     gp.gameState = gp.playState;
            // }
        }

    }

    public void titleState(int code) {
        if(code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            // gp.playSE(1);
        }
        if(code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            // gp.playSE(1);
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
    
    public void playState(int code) {
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
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
        }
        // door
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.miniGameMaze;
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
        }
        if(code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0){
                if(code == KeyEvent.VK_A){
                    if(gp.sound.volumeScale > 0){
                        gp.sound.volumeScale--;
                        gp.sound.checkVolume();
                        //gp.playSE();
                    }
                }
                if(code == KeyEvent.VK_D){
                    if(gp.sound.volumeScale < 5){
                        gp.sound.volumeScale++;
                        gp.sound.checkVolume();
                        //gp.playSE();
                    }
                }
            }
            if(gp.ui.commandNum == 1){
                //for SE
                // if(code == KeyEvent.VK_A){
                    // if(gp.ui.subState == 0 && gp.se.volumeScale > 0){
                    //     gp.se.volumeScale--;
                    //     gp.se.checkVolume();
                    //     //gp.splaySE();
                    // }
                // }
                // if(code == KeyEvent.VK_D){
                    // if(gp.ui.subState == 0 && gp.se.volumeScale < 5){
                    //     gp.se.volumeScale++;
                    //     gp.se.checkVolume();
                    //     //gp.splaySE();
                    // }
                // }
            }
            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
            if(gp.ui.commandNum == 3){
                gp.gameState = gp.playState;
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
    }

    public void gameOverState(int code) {
        if(code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
        }
        if(code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0){
                //before mini game state
                gp.gameState = gp.tmpState;
            }
            if(gp.ui.commandNum == 1){
                gp.gameState = gp.playState;
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
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
        // if(code == KeyEvent.VK_SPACE){
        //     spacePressed = false;
        // }

    }

    
}
