import java.util.*;
/**
 * Weird array is a single array for all your array needs.<br />
 * Need a string and an int in one array? no problem.<br />
 * Bool and double? easy.<br />
 * With Universal array, all types can be saved in one spot.<br />
 * Whats the use for this I hear you say? I have 0 clue.
 * 
 * @author Joey Ma
 * @version March 2017
 */
public class WeirdArray  
{
    private ArrayList<Class<?>> classes;
    private ArrayList<Object> objs;
    /**
     * Constructor for objects of class WierdArray
     */
    public WeirdArray()
    {
        objs=new ArrayList<Object>();
        classes=new ArrayList<Class<?>>();
    }
    public <E> void add(E e){
        objs.add(e);
        classes.add(e.getClass());
    }
    public <E> E get(int i){
        return (E)classes.get(i).cast(objs.get(i));
    }
}
