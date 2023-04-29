package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;
import monster.MON_1;
import monster.MON_2;
import monster.MON_3;
import monster.MON_4;
import main.GamePanel;

public class Pingpongmon extends Entity {

    GamePanel gp;
    public ArrayList<Entity> poList = new ArrayList<>();
    public boolean monAttack = false;
    public boolean playerAttack = false;


    public Pingpongmon(GamePanel gp) {
        super(gp);
        this.gp = gp;
        poList.add(new MON_1(gp));
        poList.add(new MON_2(gp));
        poList.add(new MON_3(gp));
        poList.add(new MON_4(gp));
    }
    
    public BufferedImage getPo(int i) {
        switch(i) {
            case 0: return poList.get(0).poImage;
            case 1: return poList.get(1).poImage;
            case 2: return poList.get(2).poImage;
            case 3: return poList.get(3).poImage;
            default: return poList.get(0).poImage;
        }
    }

    public void attack1() {
        double random = Math.random(); // generate random number between 0 and 1

        if (poList.get(0).life > 0) {
            if (random < 0.7) { // 70% chance
                (poList.get(0)).life -= 5; // subtract 5 from target Pokemon's life
                playerAttack = false;
            } else {
                playerCheck();
            }
        } else if (poList.get(1).life > 0) {
            if (random < 0.7) { // 70% chance
                (poList.get(1)).life -= 5; // subtract 5 from target Pokemon's life
                playerAttack = false;
            } else {
                playerCheck();
            }
        } else if (poList.get(2).life > 0) {
            if (random < 0.7) { // 70% chance
                (poList.get(2)).life -= 5; // subtract 5 from target Pokemon's life
                playerAttack = false;
            } else {
                playerCheck();
            }
        }

    }

    public void attack2() {
        double random = Math.random(); // generate random number between 0 and 1
        if (poList.get(0).life > 0) {
            if (random < 0.9) { // 90% chance
                (poList.get(0)).life -= 3; // subtract 5 from target Pokemon's life
                playerAttack = false;
            } else {
                playerCheck();
            }
        } else if (poList.get(1).life > 0) {
            if (random < 0.9) { // 90% chance
                (poList.get(1)).life -= 3; // subtract 5 from target Pokemon's life
                playerAttack = false;
            } else {
                playerCheck();
            }
        } else if (poList.get(2).life > 0) {
            if (random < 0.9) { // 90% chance
                (poList.get(2)).life -= 3; // subtract 5 from target Pokemon's life
                playerAttack = false;
            } else {
                playerCheck();
            }
        }
    }

    public void attackPo() {
        double random = Math.random(); // generate random number between 0 and 1
        if (random < 0.7) { // 70% chance
            random = Math.random(); // generate random number between 0 and 1
            if (random < 0.9) { // 90% chance
                (poList.get(3)).life -= 5; // subtract 5 from target Pokemon's life
                monAttack = false;
            } else if (random < 0.4) { // 40% chance
                (poList.get(3)).life -= 10; // subtract 5 from target Pokemon's life
                monAttack = false;
            } else {
                (poList.get(3)).life -= 5; // subtract 5 from target Pokemon's life
                monAttack = false;
            }
        } else {
            monCheck();
        }
    }
    
    public void reset() {
        for (int i = 0; i < poList.size(); i++) {
            if(i == 3 ) {
                poList.get(i).life = 80;
            } else {
                poList.get(i).life = 20;
            }
        }
        monAttack = false;
        playerAttack = false;
    }

    public void monCheck() {
        monAttack = true;
    }

    public void playerCheck() {
        playerAttack = true;
    }

}
