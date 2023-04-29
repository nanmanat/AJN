package environment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Lightning {

    GamePanel gp;
    BufferedImage darknessFilter;

    public Lightning (GamePanel gp , int circleSize){
        //create a buffered image 
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2 = (Graphics2D) darknessFilter.createGraphics();


        //create a screen size rectangle area
        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));

        //get the center x and y of the light circle
        int centerX = gp.player.screenX + (gp.tileSize)/2;
        int centerY = gp.player.screenY + (gp.tileSize)/2;

        //get the top left x and y of the light circle
        double x = centerX - (circleSize/2);
        double y = centerY - (circleSize/2);

        //create a light circle shape
        Shape circlShape = new Ellipse2D.Double(x,y,circleSize,circleSize);

        //create a light circle area
        Area lightArea = new Area(circlShape);

        //Subtract the light circle from the screen rectangle
        screenArea.subtract(lightArea);

        //create a gradation effect within the light circle
        Color color[] = new Color[5];
        float fraction[] = new float[5];

        color[0] = new Color(0, 0, 0, 0F);
        color[1] = new Color(0, 0, 0, 0.25F);
        color[2] = new Color(0, 0, 0, 0.5F);
        color[3] = new Color(0, 0, 0, 0.75F);
        color[4] = new Color(0, 0, 0, 0.98F);

        fraction[0] = 0f;
        fraction[1] = 0.25f;
        fraction[2] = 0.5f;
        fraction[3] = 0.75f;
        fraction[4] = 1f;
        
        //create a gradation paint setting for the light circle
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2) , fraction, color);

        //set the gradient data on g2
        g2.setPaint(gPaint);

        //Draw the light circle
        g2.fill(lightArea);
    
        //Draw the screen rectangle without the light circle area
        g2.fill(screenArea);

        g2.dispose();
    }

    public void draw(Graphics2D g2){
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}