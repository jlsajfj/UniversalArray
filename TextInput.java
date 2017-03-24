import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.*;
/**
 * Generic text input to greenfoot based off my "toggle button" code (based off Jordan Cohen's standard button code).
 * 
 * @author Joey Ma
 * @version March 2017
 */
public class TextInput extends Actor
{
    // Declare variables
    private GreenfootImage myImage;
    private GreenfootImage myAltImage;
    private String buttonText;
    private int textSize;
    public boolean active;
    private String a;

    public TextInput (int textSize)
    {
        active=false;
        // Assign value to my internal String
        buttonText = "";
        this.textSize = textSize;
        // Draw a button with centered text:

        updateMe();
    }

    public String getText(){
        return buttonText;
    }

    public TextInput(){
        this(20);
    }

    public void act ()
    {
        if (world.clicked(this))
        {
            active=!active;
            if(active){
                buttonText="";
                updateMe();
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
                else if(temp.equals("enter")){
                    active=!active;
                    System.out.println(buttonText);
                }
                updateMe();
            }
        }
        if(active){
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
        GreenfootImage tempTextImage = new GreenfootImage (text, textSize, Color.WHITE, Color.BLUE);
        int width = Math.max(tempTextImage.getWidth()+7,100);
        myImage = new GreenfootImage (width, tempTextImage.getHeight() + 8);
        myImage.setColor (Color.BLUE);
        myImage.fill();
        myImage.drawImage (tempTextImage, 4, 4);
        myImage.setColor (Color.BLACK);
        myImage.drawRect (0,0,width, tempTextImage.getHeight() + 7);
        GreenfootImage finImage = new GreenfootImage(width*2, tempTextImage.getHeight() + 8);
        finImage.drawImage(myImage,width,0);
        myImage=new GreenfootImage(finImage);
        setImage(myImage);

        tempTextImage = new GreenfootImage (text, textSize, Color.BLUE, Color.WHITE);
        myAltImage = new GreenfootImage(width, tempTextImage.getHeight() + 8);
        myAltImage.setColor (Color.BLUE);
        myAltImage.fill();
        myAltImage.drawImage (tempTextImage, 4, 4);

        myAltImage.setColor (Color.BLACK);
        myAltImage.drawRect (0,0,width, tempTextImage.getHeight() + 7);
        finImage = new GreenfootImage(width*2, tempTextImage.getHeight() + 8);
        finImage.drawImage(myAltImage,width,0);
        myAltImage = new GreenfootImage(finImage);
    }

    /**
     * Update current TextButton text
     */
    public void updateMe ()
    {
        updateMe(buttonText);
    }
}
