import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Literally just a text display box
 * 
 * @author Joey Ma
 * @version March 2017
 */
public class button extends Actor
{
    private GreenfootImage scoreBoard;
    private Color background;
    private Color foreground;
    public button(String a){
        scoreBoard = new GreenfootImage(300, 30);
        background = new Color(175, 20, 23);
        foreground = new Color(255, 255, 255);
        setImage(scoreBoard);
        /**CODE PAWNED*/
        // Refill the background with background color
        scoreBoard.setColor(background);
        scoreBoard.fill();

        // Write text over the solid background
        scoreBoard.setColor(foreground);  
        // Smart piece of code that centers text
        int centeredY = (300/2) - ((a.length() * 14)/2);
        // Draw the text onto the image
        scoreBoard.drawString(a, centeredY, 15);
        /**CODE PAWNED END*/
    }
}
