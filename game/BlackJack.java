package game;

import java.util.ArrayList;
import java.util.Random;

public class BlackJack {
    private ArrayList<Integer> deckList = new ArrayList<Integer>();
    private ArrayList<Integer> botList = new ArrayList<Integer>();
    private ArrayList<Integer> userList = new ArrayList<Integer>();
    private int index;

    public BlackJack() {
        for (int i = 1; i <= 12; i++) {
            deckList.add(i);
        }
    } 
    
    public void addBotCard() {
        botList.add(deckList.get(randCard()));
    }
    
    public void addUserCard() {
        userList.add(deckList.get(randCard()));
    }
    

    public String getBotCard() {
        return botList.toString();
    }

    public String getUserCard() {
        return userList.toString();
    }

    public String getDeckCard() {
        return deckList.toString();
    }

    public int randCard() {
        Random rand = new Random();
        int tmp = rand.nextInt(deckList.size());
        index = deckList.get(tmp);
        deckList.remove(tmp);
        return index;
    }    

    public static void main(String[] args) {
        BlackJack bj = new BlackJack();
        for (int j = 0; j < 1; j++) {
            System.out.println(bj.getDeckCard());
            bj.addBotCard();
            System.out.println(bj.getBotCard());
            System.out.println(bj.getDeckCard());
            System.out.println();
        }
    }
}
