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

        List<List<String>> all_parts = new ArrayList<>(); //A list that can hold other lists.
        for (int L = 0; L<list_count; L++) { // creates the different parts for all_parts, to fill in later
            List<String> part = new ArrayList<>();
            all_parts.add(part);
        }
        int pos = 0;
        for (int i = 0; i < x.size(); i++) {// adds all the elements from the starting set into the corresponding sublist
            if(all_parts.get(pos).size() >=2) {//moves on to next sublist when two elements are in the currently viewed sublist.
                pos = pos +1;
            }
                all_parts.get(pos).add(x.get(i)); // adds currently viewed element from the main list into the correct part sublist.
        }
        System.out.println("current arrangement: " + all_parts);
        organiser(all_parts);


    }

    private static void organiser(List<List<String>> all_parts) { // goes through each part and makes sure they are organised.
        for (int p=0;p<all_parts.size(); p++) {
            if (all_parts.get(p).get(0).compareTo(all_parts.get(p).get(1)) > 0) { //if current part is not in order, this takes out the first element
                String moved_element = all_parts.get(p).get(0);                   // and puts it at the end
                all_parts.get(p).remove(0);
                all_parts.get(p).add(moved_element);
                p = p - 1;
            }
        }
        System.out.println(all_parts); //just to make sure the for loop worked properly.
    }


}