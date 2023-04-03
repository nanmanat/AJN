package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    private GamePanel gp;
    private Graphics2D g2;
    private Font arial_40 , agencyFB;
    public int subState = 0;
    public int commandNum = 0;
    
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 25);
        agencyFB = new Font("Calibri" , Font.PLAIN , 25);
        gp.keyH.enterPressed = false;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(agencyFB);
        // g2.drawImage(settingImage, gp.tileSize/2 , gp.tileSize/2, gp.tileSize , gp.tileSize , null);
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }

    }

    public void drawOptionsScreen(){
        //sub window
        int frameX = gp.tileSize*4;
        int frameY = gp.tileSize;
        drawSubWindow(frameX ,frameY ,gp.tileSize*8,gp.tileSize*10);

        switch (subState) {
            case 0: drawOptionsTop(frameX , frameY); break;
        
            default:
                break;
        }
    }

    public void drawOptionsTop(int frameX , int frameY){
        //setting
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        String text = "Setting";
        int x = getXforCenteredText(text);
        int y = (int)(gp.tileSize*2.5);
        g2.drawString(text, x, y);

        //Full Screen
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        text = "Full Screen";
        x = frameX + gp.tileSize;
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum<=0){
            g2.drawString(">", x-gp.tileSize/2 , y);
            commandNum = 0;
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreenOn == false){
                    gp.fullScreenOn = true;
                }
                else gp.fullScreenOn = false;
            }
        }

        //music volume
        text = "Music";
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum==1) g2.drawString(">", x-gp.tileSize/2 , y);

        //SE volume
        text = "Sound Effect";
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum==2) g2.drawString(">", x-gp.tileSize/2 , y);

        //exit
        text = "Exit";
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum==3) g2.drawString(">", x-gp.tileSize/2 , y);

        //back
        text = "back";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum>=4) {
            g2.drawString(">", x-gp.tileSize/2 , y);
            commandNum = 4;
        }

        //full screen check box
        x = frameX + gp.tileSize*5;
        y = frameY + gp.tileSize*3;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(x, y, 24, 24);
        if(gp.fullScreenOn == true){
            g2.fillRect(x, y, 24, 24);
        }

        //music volume
        x = frameX + gp.tileSize*5;
        y += gp.tileSize;
        g2.drawRect(x, y, 120, 24);

        //SE volume
        x = frameX + gp.tileSize*5;
        y += gp.tileSize;
        g2.drawRect(x, y, 120, 24);

    }

    public void drawTitleScreen(){
        //background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //title
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "Game Gou Gou";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*2;
        //shadow text
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(text, x+5, y+5);
        //main text
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        //player image
        x = (gp.screenWidth/2) - ((gp.tileSize*2)/2);
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y , gp.tileSize*2 , gp.tileSize*2 , null);
        //new game
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        text = "New Game";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum<=0){
            g2.drawString(">", x-gp.tileSize  , y);
            commandNum = 0;
        }
        //load game
        text = "Load Game";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum==1) g2.drawString(">", x-gp.tileSize  , y);
        //Quit
        text = "Quit";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum>=2) {
            g2.drawString(">", x-gp.tileSize  , y);
            commandNum = 2;
        }

    }   

    public void drawSubWindow(int x , int y , int width , int height){
        Color c = new Color(0,0,0,190);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = Color.white;
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawDialogueScreen(){
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2; 
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize * 5;
        drawSubWindow(x, y, width, height);
    }

    public int getXforCenteredText(String t){
        int length = (int) g2.getFontMetrics().getStringBounds(t, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
