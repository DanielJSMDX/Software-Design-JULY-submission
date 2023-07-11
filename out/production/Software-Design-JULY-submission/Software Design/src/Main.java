import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args){
        ArrayList<String> m_list = new ArrayList<>(Arrays. asList("a", "d", "b", "c", "e", "f", "h", "g")); //starting list
        splitter(m_list);

    }
    private static void splitter(ArrayList<String> x) {
        System.out.println("Starting List: " + x);
        int list_count = x.size()/2; //to find the amount of lists required for later
        System.out.println(list_count);

        List<List<String>> all_parts = new ArrayList<>(); //A list that can hold other lists.
        for (int L = 0; L<list_count; L++) { // creates the different parts for all_parts, to fill in later
            List<String> part = new ArrayList<>();
            all_parts.add(part);
        }
        System.out.println(all_parts);

    }



}