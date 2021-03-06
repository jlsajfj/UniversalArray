import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * A button that can toggle true and false, based off of the standard button by Jordan Cohen
 * 
 * @author Joey Ma
 * @version March 2017
 */
public class ToggleButton extends Actor
{

    // Declare variables
    private GreenfootImage myImage;
    private GreenfootImage myAltImage;
    private String buttonText;
    private int textSize;
    public boolean active;
    /**
     * Construct a TextButton given only a String
     * @param String    String to be displayed
     */
    public ToggleButton (String text)
    {
        this(text, 20);
    }
    
    /**
     * Construct a TextButton given a String and a text size
     * @param String    String to be displayed
     */
    public ToggleButton (String text, int textSize)
    {
        active=false;
        // Assign value to my internal String
        buttonText = text;
        this.textSize = textSize;
        // Draw a button with centered text:
        
        updateMe (text);
    }

    public void act ()
    {
        if (Greenfoot.mousePressed(this))
        {
            active=!active;
        }
        if(active)
            setImage (myAltImage);
        else
        {
            setImage (myImage);
        }
    }
    
    /**
     * Update current TextButton text
     */
    public void updateMe (String text)
    {
        buttonText = text;
        GreenfootImage tempTextImage = new GreenfootImage (text, textSize, Color.RED, Color.WHITE);
        myImage = new GreenfootImage (tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        myImage.setColor (Color.WHITE);
        myImage.fill();
        myImage.drawImage (tempTextImage, 4, 4);

        myImage.setColor (Color.BLACK);
        myImage.drawRect (0,0,tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
        setImage(myImage);
        
        tempTextImage = new GreenfootImage (text, textSize, Color.WHITE, Color.RED);
        myAltImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        myAltImage.setColor (Color.WHITE);
        myAltImage.fill();
        myAltImage.drawImage (tempTextImage, 4, 4);

        myAltImage.setColor (Color.BLACK);
        myAltImage.drawRect (0,0,tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
    }
}
