package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("เกมโก่วๆ");
        window.setUndecorated(false);

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        gamePanel.setupGame();
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
