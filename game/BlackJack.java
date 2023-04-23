package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import main.GamePanel;

public class BlackJack {
    private GamePanel gp;

    private ArrayList<Integer> deckList = new ArrayList<Integer>();
    private ArrayList<Integer> botList = new ArrayList<Integer>();
    private ArrayList<Integer> userList = new ArrayList<Integer>();
    private int index;
    private int botScore = 0;
    private int userScore = 0;

    public BlackJack() {
        for (int i = 1; i <= 12; i++) {
            deckList.add(i);
        }
    } 
    
    public void addBotCard() {
        int index = randCard();
        botList.add(deckList.get(index));
        botScore += deckList.get(index);
        deckList.remove(index);
    }
    
    public void addUserCard() {
        int index = randCard();
        userList.add(deckList.get(index));
        userScore += deckList.get(index);
        deckList.remove(index);
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
        for (int i = 1; i <= 12; i++) {
            deckList.add(i);
        }
    }

    public void start() {
        int health = 3;
        boolean start = true;
        boolean bot = true;
        boolean user = true;
        Scanner scan = new Scanner(System.in);
        String text = "";
        BlackJack game = new BlackJack();
        
        for (int i = 0; start; i++) {
            if (i == 0) {
                game.addBotCard();
                System.out.println(game.getBotCard());
                System.out.println(game.getBotScore());
                game.addUserCard();
                System.out.println(game.getUserCard());
                System.out.println(game.getuserScore());
            }

            if (bot) {
                if (game.botScore >= 19) {
                    System.out.println("Stay.");
                    bot = false;
                } else {
                    System.out.println("Give me another.");
                    game.addBotCard();
                    game.showStat();
                }
            }

            if (user) {
                System.out.print("-" );
                text = scan.next();
                if (text.equals("y")) {
                    game.addUserCard();
                    game.showStat();
                } else {
                    user = false;
                }
            }

            if (user == false && bot == false) {
                System.out.println("The winner is");
                System.out.println("Hoffman: " + game.getBotScore());
                System.out.println("You: " + game.getuserScore());

                // Check which number is closer to 21 and not greater than 21
                if (game.getBotScore() <= 21 && (game.getBotScore() > game.getuserScore() || game.getuserScore() > 21) || (game.getuserScore() <= 21 && game.getuserScore() > game.getBotScore())) {
                    if (game.getBotScore() <= 21 && (game.getBotScore() > game.getuserScore() || game.getuserScore() > 21)) {
                        System.out.println("Hoffman");
                    } else {
                        System.out.println("You");
                    }
                } else if (game.getBotScore() > 21) {
                    System.out.println("You");
                }
                else if (game.getuserScore() > 21) {
                    System.out.println("Hoffman");
                }
                else {
                    System.out.println("Draw");
                }

                reset();
                bot = true;
                user = true;
                health = health-1;
                if (health == 0) {
                    break;
                }
            }

        }

    }
}
