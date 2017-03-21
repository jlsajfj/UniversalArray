import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Write a description of class UniversalArray here.
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
    private String loc;
    private int elements;
    /**
     * Create a generic universal array with 0 elements
     */
    public UniversalArray(){
        ints = new ArrayList<Integer>();
        strings = new ArrayList<String>();
        bools = new ArrayList<Boolean>();
        doubles = new ArrayList<Double>();
        loc="";
        elements=0;
    }
    
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
    
    public void test(){
        System.out.println(loc);
    }
    
    public <E> E get(int i){
        Pattern pattern = Pattern.compile("#"+i+"[^0-9]");
        Matcher match = pattern.matcher(loc);
        if(match.find()){
            System.out.println(match.end());
            System.out.println(loc.charAt(match.end()+1));
            Pattern nxt = Pattern.compile("@[0-9]+#");
            Matcher found = nxt.matcher(loc);
            found.find(match.end());
            System.out.println(loc.substring(found.start()+1,found.end()-1));
            System.out.println(strings.get(Integer.parseInt(loc.substring(found.start()+1,found.end()-1))));
            switch(loc.charAt(match.end())){
                case 'i':
                return (E) ints.get(Integer.parseInt(loc.substring(found.start()+1,found.end()-1)));
                case 's':
                return (E) strings.get(Integer.parseInt(loc.substring(found.start()+1,found.end()-1)));
                case 'd':
                return (E) doubles.get(Integer.parseInt(loc.substring(found.start()+1,found.end()-1)));
                case 'b':
                return (E) bools.get(Integer.parseInt(loc.substring(found.start()+1,found.end()-1)));
            }
        }
        return null;
    }
}
