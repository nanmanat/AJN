package game;


import java.awt.Graphics2D;

import main.GamePanel;

public class Room {
    public GamePanel gp;
    public boolean[][] room = new boolean[4][4];

    public Room(GamePanel gp) {
        this.gp = gp;
    }

    public void checkRoom(Graphics2D g2) {
        if(gp.player.checkPlayer() == 1)   gp.tileM.draw(g2 ,0 ,0 , 10, 10);
        else if(gp.player.checkPlayer() == 2)  gp.tileM.draw(g2 ,10 ,0 , 20, 10);
        else if(gp.player.checkPlayer() == 5)  gp.tileM.draw(g2 ,0 ,10 , 10, 20);
        else if(gp.player.checkPlayer() == 6)  gp.tileM.draw(g2 ,10 ,10 , 20, 20);
    }

}
