package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import object.OBJ_Heart;

public class UI {
    private GamePanel gp;
    private Graphics2D g2;
    private Font agencyFB;
    private BufferedImage heart_full, heart_half, heart_blank;
    public int subState = 0;
    public int bjState = 0;
    public int commandNum = 0;
    public String text;
    public boolean showDialog = false;
    public String currentDialogue;
    public int indexCodeGame = 0;
    public boolean error = false;
    
    public UI(GamePanel gp) {
        this.gp = gp;
        agencyFB = new Font("Calibri" , Font.PLAIN , 25);
        gp.keyH.enterPressed = false;

        //CREATE HUB OBJ
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(agencyFB);
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        else if(gp.gameState == gp.optionsState){
            gp.tmpState = gp.playState;
            drawOptionsScreen();
        }
        else if(gp.gameState == gp.miniGameMaze){
            drawPlayerLife();
            if(gp.player.life <= 0){
                gp.tmpState = gp.miniGameMaze;
                gp.gameState = gp.gameOverState;
            }
        }
        else if(gp.gameState == gp.playState){
            gp.tmpState = gp.playState;
            drawPlayerLife();
        }
        else if(gp.gameState == gp.miniGameBlackJack){
            drawBlackJack();
            drawPlayerLife();
        }
        else if(gp.gameState == gp.blackjackScore){
            drawBlackJackScore();
            drawPlayerLife();
        }
        else if(gp.gameState == gp.miniGameCode){
            drawDialogueCode();
        }
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        if(gp.gameState == gp.dialoguePopup){
            drawPlayerLife();
            drawDialogueScreen(currentDialogue);
        }
    }

    public void drawPlayerLife() {

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        //DRAW MAX LIFE
        while (i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        //DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i<gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawOptionsScreen(){
        //sub window
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        drawSubWindow(frameX ,frameY ,gp.tileSize*8,gp.tileSize*10);
        drawOptionsTop(frameX , frameY);
    }

    public void drawOptionsTop(int frameX , int frameY){
        //setting
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        String text = "Setting";
        int x = getXforCenteredText(text);
        int y = (int)(gp.tileSize*2.5);
        g2.drawString(text, x, y);

        //music volume
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        text = "Music";
        x = frameX + gp.tileSize;
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum<=0){
            g2.drawString(">", x-gp.tileSize/2 , y);
        }

        //SE volume
        text = "Sound Effect";
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum==1) g2.drawString(">", x-gp.tileSize/2 , y);

        //exit
        text = "Exit";
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum==2) g2.drawString(">", x-gp.tileSize/2 , y);

        //back
        text = "Back";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum>=3) {
            g2.drawString(">", x-gp.tileSize/2 , y);
            commandNum = 3;
        }

        //music volume
        x = frameX + gp.tileSize*5;
        y = frameY + gp.tileSize*3;
        g2.drawRect(x, y, 120, 24);
        int volumeWidth = 24 * gp.sound.volumeScale;
        g2.fillRect(x, y, volumeWidth, 24);

        //SE volume
        x = frameX + gp.tileSize*5;
        y += gp.tileSize;
        g2.drawRect(x, y, 120, 24);
        // int volumeSEWidth = 24 * gp.se.volumeScale;
        // g2.fillRect(x, y, volumeSEWidth, 24);

    }

    public void drawDialogueCode(){
        int x = gp.tileSize *2;
        int y = gp.tileSize * 6; 
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize * 5;
        drawSubWindow(x, y, width, height);

        y += gp.tileSize*2;
        x += gp.tileSize;
        g2.drawImage(gp.code.up1, x, y , gp.tileSize*2 , gp.tileSize*2 , null);
        if(commandNum <= 0) {
            g2.drawRect(x, y,gp.tileSize*2 , gp.tileSize*2 );
            if(indexCodeGame>=1){
                int y2 = y - 20;
                int x2 = x + (gp.tileSize / 2) + 15;
                text = String.format("%d",indexCodeGame);
                g2.drawString(text, x2, y2);
            }
            commandNum = 0;
        }

        x += gp.tileSize*3;
        g2.drawImage(gp.code.down1, x, y , gp.tileSize*2 , gp.tileSize*2 , null);
        if(commandNum == 1) {
            g2.drawRect(x, y,gp.tileSize*2 , gp.tileSize*2);
            if(indexCodeGame>=1){
                int y2 = y - 20;
                int x2 = x + (gp.tileSize / 2) + 15;
                text = String.format("%d",indexCodeGame);
                g2.drawString(text, x2, y2);
            }
        }

        x += gp.tileSize*3;
        g2.drawImage(gp.code.left1, x, y , gp.tileSize*2 , gp.tileSize*2 , null);
        if(commandNum == 2) {
            g2.drawRect(x, y,gp.tileSize*2 , gp.tileSize*2);
            if(indexCodeGame>=1){
                int y2 = y - 20;
                int x2 = x + (gp.tileSize / 2) + 15;
                text = String.format("%d",indexCodeGame);
                g2.drawString(text, x2, y2);
            }
        }

        x += gp.tileSize*3;
        g2.drawImage(gp.code.right1, x, y , gp.tileSize*2 , gp.tileSize*2 , null);
        if(commandNum == 3) {
            g2.drawRect(x, y,gp.tileSize*2 , gp.tileSize*2);
            if(indexCodeGame>=1){
                int y2 = y - 20;
                int x2 = x + (gp.tileSize / 2) + 15;
                text = String.format("%d",indexCodeGame);
                g2.drawString(text, x2, y2);
            }
        }

        x += gp.tileSize*3;
        g2.drawImage(gp.code.enter, x, y , gp.tileSize*2 , gp.tileSize*2 , null);
        if(indexCodeGame>9){
            int y2 = y - 20;
            int x2 = x + (gp.tileSize / 2) + 8;
            g2.drawString("full", x2, y2);
        }
        if(error){
            int y2 = y - 20;
            int x2 = x + (gp.tileSize / 2);
            g2.drawString("put here", x2, y2);
        }
        if(commandNum >= 4) {
            g2.drawRect(x, y,gp.tileSize*2 , gp.tileSize*2);
            commandNum = 4;
        }
    }

    public void drawBlackJack(){

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        //SUB WINDOW
        int frameX = gp.tileSize*2;
        int frameY = gp.tileSize;
        int framWidth = gp.tileSize*16;
        int framHeight = gp.tileSize*10;
        Color background = new Color(11, 69, 26);
        drawSubWindow(frameX, frameY, framWidth, framHeight, background);

        switch(bjState) {
            case 0: blackjack_top(frameX, frameY); break;
            case 1: break;
        }
    }

    public void blackjack_top(int frameX, int frameY) {
        // BlackJack game = new BlackJack();
        int textX, textY;
        //TITLE
        String text = "BLACKJACK";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        Color gold = new Color(255,179,26);
        g2.setColor(gold);
        g2.drawString(text, textX, textY);
        //bot cards
        int x = (gp.screenWidth/6) - ((gp.tileSize*2) + 30);
        int y = gp.tileSize*3;
        for (int i = 0; i < game.BlackJack.botList.size(); i++) {
            x += ((gp.tileSize*2) + 20);
            g2.drawImage(gp.player.down1, x, y , gp.tileSize*2 , gp.tileSize*2 , null);
        }
        //bot score
        g2.setColor(Color.white);
        textY = frameY + gp.tileSize;
        textX = frameX + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(18F));
        int sum = 0;
        for (int i = 0; i < game.BlackJack.botList.size(); i++) {
            sum += game.BlackJack.botList.get(i);
        }
        if(gp.game.player == false && gp.game.bot == false) {
            g2.drawString(String.format("Hoffman: %s/21", sum), textX, textY);
        } else {
            g2.drawString(String.format("Hoffman: ??+%s/21", sum-game.BlackJack.botList.get(0)), textX, textY);
        }  
        //player score
        g2.setColor(Color.white);
        textY = frameY + gp.tileSize;
        textX = (getXforCenteredText(text)*2) - gp.tileSize*3;
        g2.setFont(g2.getFont().deriveFont(18F));
        int playerSum = 0;
        for (int i = 0; i < game.BlackJack.userList.size(); i++) {
            playerSum += game.BlackJack.userList.get(i);
        }
        g2.drawString(String.format("You: %s/21", playerSum), textX, textY);
        //player cards
        x = (gp.screenWidth/6) - ((gp.tileSize*2) + 30);
        y += gp.tileSize*4;
        for (int i = 0; i < game.BlackJack.userList.size(); i++) {
            x += ((gp.tileSize*2) + 20);
            g2.drawImage(gp.player.down1, x, y , gp.tileSize*2 , gp.tileSize*2 , null);
        }
        //hit
        g2.setFont(g2.getFont().deriveFont(25F));
        g2.setColor(Color.white);
        textY += gp.tileSize*8;
        textX = frameX + gp.tileSize*2;
        g2.drawString("Hit me.", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
        }
        //stay
        textX = frameX + gp.tileSize*9;
        g2.drawString("I'm gonna stay.", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-25, textY);
        }
    }

    public void drawBlackJackScore(){

        //sub window
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        drawSubWindow(frameX ,frameY ,gp.tileSize*8,gp.tileSize*10);

        switch (subState) {
            case 0: drawBlackJackScoreTop(frameX , frameY); break;
            case 1: break;
            default: break;
        }   
    }

    public void drawBlackJackScoreTop(int frameX ,int frameY){
        //setting
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        String text = "RESULT";
        int x = getXforCenteredText(text);
        int y = (int)(gp.tileSize*2.5);
        g2.drawString(text, x, y);
        //HoffmanScore
        text = "Hoffman: ";
        g2.setFont(g2.getFont().deriveFont(25F));
        y += gp.tileSize*2;
        int sum = 0;
        for (int i = 0; i < game.BlackJack.botList.size(); i++) {
            sum += game.BlackJack.botList.get(i);
        }
        g2.drawString(text+sum, x, y);
        //PlayerScore
        text = "You: ";
        g2.setFont(g2.getFont().deriveFont(25F));
        y += gp.tileSize;
        int sum1 = 0;
        for (int i = 0; i < game.BlackJack.userList.size(); i++) {
            sum1 += game.BlackJack.userList.get(i);
        }
        g2.drawString(text+sum1, x, y);
        //Winner 
        y += gp.tileSize;
        switch(gp.game.returnWinner()) {
            case 0: g2.drawString("Hoffman!", x, y); break;
            case 1: g2.drawString("You!", x, y); break;
            case 2: g2.drawString("Drawn!", x, y); break;
        }
        //back
        text = "Back";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum>=0) {
            g2.drawString(">", x-gp.tileSize/2 , y);
            commandNum = 0;
        }
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
        text = "Start";
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

    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);


        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        // shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4 ;
        g2.drawString(text, x, y);
        //main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(40f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum <= 0){
            g2.drawString(">", x-40, y);
            commandNum = 0;
        }

        // Back to the ข่วง
        text = "Back";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum >=1){
            g2.drawString(">", x-40, y);
            commandNum = 1;
        }
    }

    public void drawSubWindow(int x , int y , int width , int height, Color color) {
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        color = Color.white;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawSubWindow(int x , int y , int width , int height) {
        Color c = new Color(0,0,0,190);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = Color.white;
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawDialogueScreen(String text){
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 6; 
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize * 5;
        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN , 32F));
        g2.drawString(text, x, y);
    }

    // public void playerDialogue(String text){
    //     int x = gp.tileSize * 3;
    //     int y = gp.tileSize / 2;
    //     y += gp.tileSize;
    //     g2.setFont(g2.getFont().deriveFont(Font.PLAIN , 32F));
    //     g2.drawString(text, x, y);
    // }

    public int getXforCenteredText(String t){
        int length = (int) g2.getFontMetrics().getStringBounds(t, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

}
