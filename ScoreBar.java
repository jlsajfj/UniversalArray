import greenfoot.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

/**
 * Scorebar with code pawned from mr cohen
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBar extends Actor
{
    /**Mr. Cohen stuff*/
    // Declare Objects
    private GreenfootImage scoreBoard;
    private Color background;
    private Color foreground;
    private Font textFont;
    private String text;

    // Declare Variables:
    private int width;
    /**thats it*/

    private String textFormat;
    private WeirdArray array;
    private ArrayList<String> slices;

    public ScoreBar(int width){
        array = new WeirdArray();
        this.width=width;
        if(width!=0){
            scoreBoard = new GreenfootImage(width, 30);
            scoreBoard.setColor(background);
            scoreBoard.fill();
            setImage (scoreBoard);
            scoreBoard.setFont(textFont);
        }
        background = new Color(175, 20, 23);
        foreground = new Color(255, 255, 255);
        textFont = new Font("Courier", Font.BOLD, 24);
    }

    public ScoreBar(int width, String format){
        this(width);
        textFormat=format;
    }
    
    public ScoreBar(String format){
        this(0,format);
    }

    public ScoreBar(int width, String format, WeirdArray arr){
        this(width,format);
        array=arr;
    }

    public void addedToWorld(World world){
        System.out.println(textFormat);
        if(width==0){
            width=world.getWidth();
            scoreBoard = new GreenfootImage(width, 30);
            scoreBoard.setColor(background);
            scoreBoard.fill();
            setImage (scoreBoard);
            scoreBoard.setFont(textFont);
        }
        Pattern pattern = Pattern.compile("%[^0-9](.*?)%");
        Matcher match = pattern.matcher(textFormat);
        int ind=0;
        while(match.find()){
            // System.out.println(match.group());
            //System.out.println(textFormat.charAt(match.start()+1+2*ind));
            textFormat=textFormat.substring(0,match.start()+1+2*ind)+ind+":"+textFormat.substring(match.start()+1+2*ind);
            ind++;
        }
        if(array.size()==0)
            array = new WeirdArray(ind);
        System.out.println(textFormat);
    }

    public <E> void update(int pos, E element){
        array.set(pos,element);
    }

    public void updateText(){
        text="";
        Pattern pattern = Pattern.compile("%[0-9]+(.*?)%");
        Matcher match = pattern.matcher(textFormat);
        int prev=0,ind=0;
        while(match.find()){
            text+=textFormat.substring(prev,match.start());
            if(array.get(ind)==null){
                text+="NULL";
            }
            else{
                Matcher match2 = Pattern.compile(":[isdb]").matcher(textFormat);
                match2.find(match.start());
                //System.out.println(textFormat.charAt(match2.start()+1));
                Matcher mat,mat2;
                int leng;
                switch(textFormat.charAt(match2.start()+1)){
                    case 'i':
                    //System.out.println(ind+" INT");
                    int inte = (Integer)array.get(ind);
                    mat = Pattern.compile(":[0-9]+%").matcher(textFormat);
                    mat.find(match2.start());
                    leng = Integer.parseInt(textFormat.substring(mat.start()+1,mat.end()-1));
                    //System.out.println(Integer.parseInt(textFormat.substring(mat.start()+1,mat.end()-1)));
                    text+=stringLengthLimiter(zeroAdder(inte,leng),leng);
                    break;

                    case 's':
                    //System.out.println(ind+" STRING");
                    String str = (String)array.get(ind);
                    text+=str;
                    break;

                    case 'd':
                    //System.out.println(ind+" DOUBLE");
                    mat = Pattern.compile(":[0-9]+\\.").matcher(textFormat);
                    mat.find(match2.start());
                    leng = Integer.parseInt(textFormat.substring(mat.start()+1,mat.end()-1));
                    mat2 = Pattern.compile("\\.[0-9]+%").matcher(textFormat);
                    mat2.find(mat.start());
                    int dec = Integer.parseInt(textFormat.substring(mat2.start()+1,mat2.end()-1));
                    double temp = (Double) array.get(ind)*Math.pow(10,dec-1)+.5;
                    temp=(int)temp;
                    temp=temp/Math.pow(10,dec-1);
                    text+=doubleConvert(temp,dec,leng);
                    break;

                    case 'b':
                    //System.out.println(ind+" BOOL");
                    boolean bool = (Boolean)array.get(ind); 
                    if(bool){
                        mat=Pattern.compile(":\\w+,").matcher(textFormat);
                        mat.find(match2.start());
                        text+=textFormat.substring(mat.start()+1,mat.end()-1);
                    }
                    else{
                        mat=Pattern.compile(",\\w+%").matcher(textFormat);
                        mat.find(match2.start());
                        text+=textFormat.substring(mat.start()+1,mat.end()-1);
                    }
                    break;
                }
            }
            prev=match.end();
            ind++;
        }
        //System.out.println(text);
        /**CODE PAWNED*/
        // Refill the background with background color
        scoreBoard.setColor(background);
        scoreBoard.fill();

        // Write text over the solid background
        scoreBoard.setColor(foreground);  
        // Smart piece of code that centers text
        int centeredY = (width/2) - ((text.length() * 14)/2);
        // Draw the text onto the image
        scoreBoard.drawString(text, centeredY, 22);
        /**CODE PAWNED END*/
    }

    public static String doubleConvert(double val, int dec, int leng){
        String temp = Double.toString(val);
        Matcher mat = Pattern.compile("\\.").matcher(temp);
        mat.find();
        //System.out.println(temp);
        //System.out.println(temp.length()-mat.end());
        while(temp.length()-mat.end()<dec)
            temp+="0";
        //System.out.println(temp);
        while(temp.length()<=leng){
            temp="0"+temp;
        }
        temp=temp.substring(0,leng+1);
        //System.out.println(temp);
        return temp;
    }

    public static String stringLengthLimiter(String value, int digits){
        return value.substring(0,digits);
    }

    /**
     * Method that aids in the appearance of the scoreboard by generating
     * Strings that fill in zeros before the score. For example:
     * 
     * 27 ===> to 5 digits ===> 00027
     * 
     * @param   value   integer value to use for score output
     * @param   digits   number of zeros desired in the return String
     * @return  String  built score, ready for display
     * @author Mr. Cohen
     */
    public static String zeroAdder (int value, int digits)
    {
        // Figure out how many digits the number is
        int numDigits = digitCounter(value);

        // If not extra digits are needed
        if (numDigits >= digits)
            return Integer.toString(value);

        else // Build the number with zeroes for extra place values:
        {
            String zeroes = "";
            for (int i = 0; i < (digits - numDigits); i++)
            {
                zeroes += "0";
            }
            return (zeroes + value);
        }
    }

    /**
     * Useful private method that counts the digit in any integer.
     * 
     * @param number    The number whose digits you want to count
     * @return  int     The number of digits in the given number
     */
    private static int digitCounter (int number)
    {
        if (number < 10) {
            return 1;
        }
        int count = 0;
        while (number > 0) {
            number /= 10;
            count++;
        }
        return count;
    }
}
