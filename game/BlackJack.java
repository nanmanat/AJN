package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class BlackJack extends Entity {
    private GamePanel gp;

    private ArrayList<Integer> deckList = new ArrayList<Integer>();
    public static ArrayList<Integer> botList = new ArrayList<Integer>();
    public ArrayList<Integer> userList = new ArrayList<Integer>();
    private int botScore;
    private int userScore;
    public static boolean turn = true;
    public boolean bot = true;
    public boolean player = true;

    public BlackJack(GamePanel gp) {
        super(gp);
        this.gp = gp;
        System.out.println("TEST");
        if (turn == true) {
            for (int i = 1; i <= 12; i++) {
                deckList.add(i);
            }
            turn = false;
        }
        setCard();
    } 

    public void setCard() {
        card0 = setup("/res/objects/card0", gp.tileSize, gp.tileSize);
        card1 = setup("/res/objects/card1", gp.tileSize, gp.tileSize);
        card2 = setup("/res/objects/card2", gp.tileSize, gp.tileSize);
        card3 = setup("/res/objects/card3", gp.tileSize, gp.tileSize);
        card4 = setup("/res/objects/card4", gp.tileSize, gp.tileSize);
        card5 = setup("/res/objects/card5", gp.tileSize, gp.tileSize);
        card6 = setup("/res/objects/card6", gp.tileSize, gp.tileSize);
        card7 = setup("/res/objects/card7", gp.tileSize, gp.tileSize);
        card8 = setup("/res/objects/card8", gp.tileSize, gp.tileSize);
        card9 = setup("/res/objects/card9", gp.tileSize, gp.tileSize);
        card10 = setup("/res/objects/card10", gp.tileSize, gp.tileSize);
        card11 = setup("/res/objects/cardJ", gp.tileSize, gp.tileSize);
        card12 = setup("/res/objects/cardQ", gp.tileSize, gp.tileSize);
        card13 = setup("/res/objects/cardK", gp.tileSize, gp.tileSize);
    }

    public BufferedImage getCard(int i) {
        switch(i) {
            case 0: return card0;
            case 1: return card1;
            case 2: return card2;
            case 3: return card3;
            case 4: return card4;
            case 5: return card5;
            case 6: return card6;
            case 7: return card7;
            case 8: return card8;
            case 9: return card9;
            case 10: return card10;
            case 11: return card11;
            case 12: return card12;
            case 13: return card13;
            default: return card0;
        }
    }
    
    public boolean botCheck() {
        botScore = 0;
        for (int i = 0; i < game.BlackJack.botList.size(); i++) {
            botScore += game.BlackJack.botList.get(i);
        }
        if (botScore >= 18) {
            return false;
        }
        return true;
    }

    public int botScore() {
        botScore = 0;
        for (int i = 0; i < game.BlackJack.botList.size(); i++) {
            botScore += game.BlackJack.botList.get(i);
        } 
        return botScore;
    }
    
    public int playerScore() {
        userScore = 0;
        for (int i = 0; i < gp.blackJack.userList.size(); i++) {
            userScore += gp.blackJack.userList.get(i);
        } 
        return userScore;
    }
    
    public int getWinner() {
        int botScore = botScore();
        int userScore = playerScore();
    
        if (botScore > 21 && userScore > 21) {
            // Both scores are over 21, nobody wins
            if (botScore < userScore) {
                return 0;
            } else {
                return 1;
            }
        } else if (botScore > 21) {
            return 1; // Player wins
        } else if (userScore > 21) {
            return 0; // Bot wins
        } else if (botScore == userScore) {
            return 2;
        } else {
            if (botScore > userScore) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    
    public void getDeck() {
        System.out.println(deckList.toString());
    }

    public int returnWinner() {
        System.out.println("botscore "+ botScore());
        System.out.println("playerscore "+ playerScore());
        if (getWinner() == 0) {
            System.out.println("Bot Wins");
            return 0;
        } else if (getWinner() == 1){
            System.out.println("Player wins!");
            return 1;
        } else {
            System.out.println("Drawn!");
        }
        return 2;
    }

    public void addBotCard() {
        if (botCheck() == true) {
            int index = randCard();
            botList.add(deckList.get(index));
            deckList.remove(index);
        } else {
            bot = false;
        }
    }    
    
    public void addUserCard() {
        int index = randCard();
        userList.add(deckList.get(index));
        userScore = 0;
        for (int i = 0; i < gp.blackJack.userList.size(); i++) {
            userScore += gp.blackJack.userList.get(i);
        }
        deckList.remove(index);
        if (bot == false && player == false) {
            return;
        }
    }

    public int randCard() {
        Random rand = new Random();
        int tmp = 0;
        while (tmp == 0) {
            tmp = rand.nextInt(deckList.size());
        }
        return tmp;
    }    

    public void reset() {
        deckList.clear();
        userList.clear();
        botList.clear();
        turn = true;
        bot = true;
        player = true;
        for (int i = 1; i <= 12; i++) {
            deckList.add(i);
        }
    }
}
