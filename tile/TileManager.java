package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[128];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {

        setup(0, "Piskel-4", false);
        setup(1, "Piskel-3", false);
        setup(2, "Piskel-29", true);
        setup(3, "Piskel-41", false);
        setup(4, "Piskel-13", true);
        setup(5, "Piskel-20", false);
        setup(6, "Piskel-19", false);
        setup(7, "Piskel-16", false);
        setup(8, "Piskel-2", false);
        setup(9, "Piskel-23", false);
        setup(10, "Piskel-24", false);
        setup(11, "Piskel-18", false);
        setup(12, "Piskel-15", false);
        setup(13, "Piskel-22", false);
        setup(14, "Piskel-14", false);
        setup(15, "Piskel-21", false);
        setup(16, "Piskel-17", false);
        setup(17, "Piskel-33", false);
        setup(18, "Piskel-8", false);
        setup(19, "Piskel-37", false);
        setup(20, "Piskel-36", false);
        setup(21, "Piskel-38", false);
        setup(22, "Piskel-39", false);
        setup(23, "Piskel-10", false);
        setup(24, "Piskel-42", false);
        setup(25, "Piskel-7", true);
        setup(26, "Piskel-6", true);
        setup(27, "Piskel-5", true);
        setup(28, "Piskel-25", true);
        setup(29, "Piskel-28", true);
        setup(30, "Piskel-26", true);
        setup(31, "Piskel-27", true);
        setup(32, "Piskel-32", false);
        setup(33, "Piskel-34", false);
        setup(34, "Piskel-35", false);
        setup(35, "Piskel-40", false);
        setup(36, "Piskel-30", true);
        setup(37, "Piskel-31", false);
        setup(38, "Piskel-39", false);
        setup(39, "Piskel-40", false);
        setup(40, "Piskel-41", false);
        setup(41, "Piskel-42", false);
        setup(42, "0", true);
        setup(43, "1", true);
        setup(44, "2", false);
        setup(45, "3", false);
        setup(46, "4", false);
        setup(47, "5", false);
        setup(48, "6", false);
        setup(49, "7", false);
        setup(50, "8", false);
        setup(51, "9", false);
        setup(52, "10", false);
        setup(53, "11", false);
        setup(54, "12", false);
        setup(55, "13", false);
        setup(56, "14", false);
        setup(57, "15", false);
        setup(58, "16", false);
        setup(59, "17", false);
        setup(60, "18", false);
        setup(61, "19", false);
        setup(62, "20", false);
        setup(63, "21", false);
        setup(64, "casinoBackSloth", true);
        setup(65, "casinoDoor", false);
        setup(66, "casinoDown", true);
        setup(67, "casinoRight", true);
        setup(68, "casinoLeft", true);
        setup(69, "casinoUp", true);
        setup(70, "casinoLeftDesk", true);
        setup(71, "casinoMiddleDesk", true);
        setup(72, "casinoTile", false);
        setup(73, "Piskel-2", true);
        setup(74, "door1", false);
        setup(75, "door6", false);
        setup(76, "door3", false);
        setup(77, "door4", false);
        setup(78, "door5", false);
        setup(79, "casinoRoullete", true);
        setup(80, "casinoRedTiles", true);
        setup(81, "casinoSloth", true);
        setup(82, "casinoDeskRight", true);


    }
    public void setup(int index, String imagePath, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new FileInputStream("res/tiles/" + imagePath + ".png"));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(IOException e) {
            e.printStackTrace();
        }
    } 

    public void loadMap(String filePath, int map) {
        try{

            InputStream is = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = reader.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }

            worldCol++;

            if (worldCol  == gp.maxWorldCol) {
                worldCol  = 0;
                worldRow ++;
            }
        }
    }

    public void loadMap(){
        loadMap("res/maps/map001.txt",0);
        loadMap("res/maps/mapMaze.txt",1);
        loadMap("res/maps/mapPlayerStory.txt",2);
        loadMap("res/maps/mapAJN.txt",3);
        loadMap("res/maps/mapMill.txt",4);
        loadMap("res/maps/mapCasino.txt",5);
        // if(gp.gameState == gp.playState || gp.tmpState == gp.playState){
        //     // loadMap("res/maps/map001.txt");
        //     loadMap("res/maps/map001.txt",0);
        // }
        // else if(gp.gameState == gp.miniGameMaze){
        //     loadMap("res/maps/mapMaze.txt",1);
        // }
        // else if(gp.gameState  == gp.miniGameCode){
        //     loadMap("res/maps/mapPlayerStory.txt",2);
        // }
        // else if(gp.gameState == gp.miniGamePuzzle){
        //     loadMap("res/maps/mapAJN.txt",3);
        // }
        // else if(gp.gameState == gp.miniGamePokemon){
        //     loadMap("res/maps/map001.txt",4);
        // }
        // else if(gp.gameState == gp.miniGameBlackJack){
        //     loadMap("res/maps/mapCasino.txt",5);
        // }

    }
}
