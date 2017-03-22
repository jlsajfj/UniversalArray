import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Universal array is a single array for all your array needs.<br />
 * Need a string and an int in one array? no problem.<br />
 * Bool and double? easy.<br />
 * With Universal array, all types can be saved in one spot.<br />
 * Whats the use for this I hear you say? I have 0 clue.
 * 
 * @author Joey Ma 
 * @version March 2017
 */
public class UniversalArray  
{
    private ArrayList<Integer> ints;
    private ArrayList<String> strings;
    private ArrayList<Boolean> bools;
    private ArrayList<Double> doubles;
    /**
     * Regex string, in format of # then a number indicating index in array then a char indicating the class type (integer,string,boolean,double) then an @ followed by the index the the corresponding arraylist
     */
    private String loc;
    private int elements;
    /**
     * Create a generic universal array with 0 elements.<br />
     * Initializes all the internal ArrayLists and creates the regex string to search.<br />
     */
    public UniversalArray(){
        ints = new ArrayList<Integer>();
        strings = new ArrayList<String>();
        bools = new ArrayList<Boolean>();
        doubles = new ArrayList<Double>();
        loc="";
        elements=0;
    }

    /**
     * Add an element to the Array<br />
     * Generics was used so that instead of added a new overloaded function when a new class is to be added,<br />
     * just add another if statement to the end.
     * @params e the element to be added (any type)
     */
    public <E> void add(E e){
        loc+="#"+elements;
        if(e instanceof Integer){
            loc+="i"+"@"+ints.size();
            ints.add((Integer)e);
        }
        else if(e instanceof String){
            loc+="s"+"@"+strings.size();
            strings.add((String)e);
        }
        else if(e instanceof Boolean){
            loc+="b"+"@"+bools.size();
            bools.add((Boolean)e);
        }
        else if(e instanceof Double){
            loc+="d"+"@"+doubles.size();
            doubles.add((Double)e);
        }
        elements++;
    }

    /**
     * Just a testing class that outputs the current regex string.
     */
    public void test(){
        System.out.println(loc);
    }

    /**
     * Getter method to get the element at index i regardless of class
     * @param i index of element to be retrieved
     * @return Object a generic object representing whatever was started with
     */
    public <E> E get(int i){
        Pattern pattern = Pattern.compile("#"+i+"[^0-9]");
        Matcher match = pattern.matcher(loc);
        if(match.find()){
            System.out.println(match.end());
            System.out.println(loc.charAt(match.end()-1));
            Pattern nxt = Pattern.compile("@[0-9]+#");
            Matcher found = nxt.matcher(loc);
            found.find(match.end());
            System.out.println(loc.substring(found.start()+1,found.end()-1));
            //System.out.println(strings.get(Integer.parseInt(loc.substring(found.start()+1,found.end()-1))));
            switch(loc.charAt(match.end()-1)){
                case 'i':
                return (E)ints.get(Integer.parseInt(loc.substring(found.start()+1,found.end()-1)));
                case 's':
                return (E) strings.get(Integer.parseInt(loc.substring(found.start()+1,found.end()-1)));
                case 'd':
                return (E) doubles.get(Integer.parseInt(loc.substring(found.start()+1,found.end()-1)));
                case 'b':
                return (E) bools.get(Integer.parseInt(loc.substring(found.start()+1,found.end()-1)));
            }
        }
        return (E) "Failed";
    }
    
    /**
     * A generic type caster from stack overflow
     * http://stackoverflow.com/questions/14524751/cast-object-to-generic-type-for-returning
     * @param o object to be cast
     * @param clazz the class to be casted to
     * @return T the casted object
     */
    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }
}
