package game;

import java.util.ArrayList;
import java.util.Random;

public class BlackJack {

    private ArrayList<Integer> deckList = new ArrayList<Integer>();
    public static ArrayList<Integer> botList = new ArrayList<Integer>();
    public static ArrayList<Integer> userList = new ArrayList<Integer>();
    private int botScore;
    private int userScore;
    public static boolean turn = true;
    public boolean bot = true;
    public boolean player = true;

    public BlackJack() {
        for (int i = 1; i <= 12; i++) {
            deckList.add(i);
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
        for (int i = 0; i < game.BlackJack.userList.size(); i++) {
            userScore += game.BlackJack.userList.get(i);
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
        for (int i = 0; i < game.BlackJack.userList.size(); i++) {
            userScore += game.BlackJack.userList.get(i);
        }
        deckList.remove(index);
        if (bot == false && player == false) {
            return;
        }
    }    
    
    public int getBotScore() {
        return botScore;
    }

    public int getuserScore() {
        return userScore;
    }

    public ArrayList<Integer> getBotCard() {
        return botList;
    }

    public ArrayList<Integer> getUserCard() {
        return userList;
    }

    public String getDeckCard() {
        return deckList.toString();
    }

    public int randCard() {
        Random rand = new Random();
        int tmp = rand.nextInt(deckList.size());
        return tmp;
    }    

    public void showStat() {
        System.out.println(getBotCard());
        System.out.println(getBotScore());
        System.out.println(getUserCard());
        System.out.println(getuserScore());
    }

    public void reset() {
        deckList.clear();
        userList.clear();
        botList.clear();
        turn = true;
        bot = true;
        player = true;
        for (int i = 0; i < 12; i++) {
            deckList.add(i);
        }
    }
}
