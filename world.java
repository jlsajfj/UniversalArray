import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class world here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class world extends World
{
    ScoreBar score;
    /**
     * Constructor for objects of class world.
     * 
     */
    public world()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 400, 1);
        score = new ScoreBar(0,"TEST: %i:4% AND %s% PLUS %d:5.2% WITH %i:2% INT %b:Hi,BYE%");
        addObject(score,getWidth()/2,15);
        score.update(1,"HI");
        score.update(0,5);
        score.update(1,"CHICKEN");
        score.update(3,7);
        score.update(2,4.7);
        score.update(4,true);
        score.update(4,false);
        f=5;
        d=4.7;
        active=false;
        prepare();
    }
    button b0,b1,b2,b3,b4,b5;
    ToggleButton tb;
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        b0 = new button("UP");
        addObject(b0, 150, 103);
        b1 = new button("DOWN");
        addObject(b1, 150, 133);
        b2 = new button("FIRST INT");
        addObject(b2, 150, 73);
        tb = new ToggleButton("FIRST BOOL");
        addObject(tb, 897, 50);

        b3 = new button("UP");
        addObject(b3, 480, 103);
        b4 = new button("DOWN");
        addObject(b4, 480, 133);
        b5 = new button("FIRST DOUBLE");
        addObject(b5, 480, 73);
        score.updateText();
        TextInput textinput = new TextInput("");
        addObject(textinput,694,216);
    }
    int f;
    double d;
    boolean active;
    public void act(){
        if(Greenfoot.mouseClicked(b0)){
            f++;
            score.update(0,f);
        }
        if(Greenfoot.mouseClicked(b1)){
            f--;
            f=Math.max(0,f);
            score.update(0,f);
        }
        if(Greenfoot.mouseClicked(b3)){
            d+=0.1;
            score.update(2,d);
        }
        if(Greenfoot.mouseClicked(b4)){
            d-=0.1;
            d=Math.max(0,d);
            score.update(2,d);
        }
        active=tb.active;
        score.update(4,active);
        score.updateText();
    }
}
