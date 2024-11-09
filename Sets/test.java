import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

public class test {
    

    public static void main(String[] args) {

        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

        UnsortedSet<Double> al2 = new UnsortedSet<>();
        al2.add(17.0);
        al2.add(19.0);
          
        SortedSet<Double> al = new SortedSet<>(al2);
        System.out.println(al);

        System.out.println("*********************");
        al.clear();
        System.out.println(al);
        al.add(10.0);
        al.add(9.0);
        al.add(9.0);
        System.out.println(al);

        



    
    
    
    }
}

