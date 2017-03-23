import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.*;
/**
 * A Generic Button to display text that is clickable. 
 * 
 * This should be added to, and controlled by, a world.
 * 
 * @author Jordan Cohen
 * @version v0.1.5
 */
public class TextInput extends Actor
{
    // Declare variables
    private GreenfootImage myImage;
    private GreenfootImage myAltImage;
    private String buttonText;
    private int textSize;
    public boolean active;
    public TextInput (String text)
    {
        this(text, 20);
    }

    public TextInput (String text, int textSize)
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
            if(active){
                buttonText="";
            }
        }
        if(active){
            String temp = Greenfoot.getKey();
            if(temp!=null){
                if(temp.length()==1){
                    buttonText+=temp;
                }
                else if(temp.equals("space")){
                    buttonText+=" ";
                }
                else if(temp.equals("backspace")){
                    if(buttonText.length()!=0)
                        buttonText=buttonText.substring(0,buttonText.length()-1);
                }
                updateMe(buttonText);
            }
            setImage(myAltImage);
        }
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
        int width1 = Math.max(tempTextImage.getWidth()+7,9);
        int width2 = Math.max(tempTextImage.getWidth()+8,10);
        myImage = new GreenfootImage (width2, tempTextImage.getHeight() + 8);
        myImage.setColor (Color.WHITE);
        myImage.fill();
        myImage.drawImage (tempTextImage, 4, 4);
        myImage.setColor (Color.BLACK);
        myImage.drawRect (0,0,width1, tempTextImage.getHeight() + 7);
        setImage(myImage);

        tempTextImage = new GreenfootImage (text, textSize, Color.WHITE, Color.RED);
        myAltImage = new GreenfootImage(width2, tempTextImage.getHeight() + 8);
        myAltImage.setColor (Color.WHITE);
        myAltImage.fill();
        myAltImage.drawImage (tempTextImage, 4, 4);

        myAltImage.setColor (Color.BLACK);
        myAltImage.drawRect (0,0,width1, tempTextImage.getHeight() + 7);
    }

}
