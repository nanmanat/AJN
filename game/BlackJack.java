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
        int index = randCard();
        botList.add(deckList.get(index));
        deckList.remove(index);
    }
    
    public void addUserCard() {
        int index = randCard();
        userList.add(deckList.get(index));
        deckList.remove(index);
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
        return tmp;
    }    

    public static void main(String[] args) {
        BlackJack bj = new BlackJack();
        for (int j = 0; j < 12; j++) {
            bj.addBotCard();
            System.out.println(bj.getBotCard());
            bj.addUserCard();
            System.out.println(bj.getUserCard());
            System.out.println();
        }
    }
}
