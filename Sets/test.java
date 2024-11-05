import java.util.ArrayList;
import java.util.Iterator;


public class test {
    

    public static void main(String [] args) {

        
        UnsortedSet<Double> al2 = new UnsortedSet<>();
        al2.add(17.0);
         al2.add(10.0);
         al2.add(11.0);
      
        al2.add(14.0);
        al2.add(15.0);
        al2.add(16.0);
        
        al2.add(12.0);
        al2.add(13.0);
        al2.add(2.0);
        al2.add(20.0);
          
        SortedSet<Double> al = new SortedSet<>(al2);
        System.out.println(al);
        System.out.println(al.get(16.5, 0, al.size()));

        



    
    
    
    }
}

