package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed , enterPressed;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    public void playState (int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //title
        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
            }
            if(code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    //music
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }

        //game
        if(gp.gameState == gp.playState){
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
        }

        //option
        if(gp.gameState == gp.optionsState){
            if(code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
            }
            if(code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0){
                    enterPressed = true;
                }
                if(gp.ui.commandNum == 1){
                    //music
                }
                if(gp.ui.commandNum == 2){
                    //SE
                }
                if(gp.ui.commandNum == 3){
                    System.exit(0);
                }
                if(gp.ui.commandNum == 4){
                    gp.gameState = gp.playState;
                }
            }
        }

        //dialogue
        if(gp.gameState == gp.dialogueState){
            // if(code == KeyEvent.VK_SPACE){

            // }
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

    }

    
}
