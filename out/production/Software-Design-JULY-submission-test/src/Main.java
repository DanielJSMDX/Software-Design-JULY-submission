import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        System.out.println("running MAIN");
        ArrayList<String> m_list = new ArrayList<>(Arrays.asList("d", "a", "b", "c", "e", "f", "h", "g")); //starting list
        splitter(m_list);

    }

    private static void splitter(ArrayList<String> x) {
        System.out.println("running SPLITTER");
        System.out.println("Starting List: " + x);
        int list_count = x.size() / 2; //to find the amount of lists required for later

        List<List<String>> all_parts = new ArrayList<>(); //A list that can hold other lists.
        for (int L = 0; L < list_count; L++) { // creates the different parts for all_parts, to fill in later
            List<String> part = new ArrayList<>();
            all_parts.add(part);
        }
        int pos = 0;
        for (int i = 0; i < x.size(); i++) {// adds all the elements from the starting set into the corresponding sublist
            if (all_parts.get(pos).size() >= 2) {//moves on to next sublist when two elements are in the currently viewed sublist.
                pos = pos + 1;
            }
            all_parts.get(pos).add(x.get(i)); // adds currently viewed element from the main list into the correct part sublist.
        }
        System.out.println("current arrangement: " + all_parts);
        organiser(all_parts);


    }

    private static void organiser(List<List<String>> all_parts) { // goes through each part and makes sure they are organised.
        System.out.println("running ORGANISER");
        System.out.println("parts: " + all_parts);
        System.out.println("part size: " + all_parts.size());
        List<List<Integer>> organised_count_scores = new ArrayList<>();
        int list_count = all_parts.size(); //to find the amount of lists required for later
        for (int L = 0; L < list_count; L++) { // creates the different parts for all_parts, to fill in later
            List<Integer> part = new ArrayList<>();
            organised_count_scores.add(part);
        }
        System.out.println(organised_count_scores);
        int organised_count = 0;

        for (int l = 0; l < all_parts.size(); l++) {
            for (int p = 0; p < all_parts.get(l).size() - 1;) {
                if (all_parts.get(l).get(p).compareToIgnoreCase(all_parts.get(l).get(p + 1)) < 0) {
                    organised_count = organised_count + 1;
                    organised_count_scores.set(l, Collections.singletonList(organised_count));
                    p=p+1;
                } else if (all_parts.get(l).get(p).compareToIgnoreCase(all_parts.get(l).get(p + 1)) > 0) {
                    String temp1 = all_parts.get(l).get(p);
                    Collections.swap(all_parts.get(l), p, p + 1);


                }


            }
        }
        System.out.println(organised_count_scores);

        System.out.println("all_parts now is: " + all_parts); //just to make sure the for loop worked properly.
        System.out.println("organised_count " + organised_count);

        if (organised_count == all_parts.size() && all_parts.size() > 1) {
            System.out.println("sending to combiner");
            combiner(all_parts);
        } else if (organised_count <= all_parts.size()) {
            organiser(all_parts);
            System.out.println("organised count not equal to total sublists, running organiser again");

        }

    }

    private static void combiner(List<List<String>> all_parts) {// this will start combining the different parts together to start the official merge sort.
        System.out.println("running COMBINER");
        List<List<String>> merged_parts = new ArrayList<>();
        int list_count = all_parts.size() / 2; //to find the amount of lists required for later
        for (int L = 0; L < list_count; L++) { // creates the different parts for all_parts, to fill in later
            List<String> part = new ArrayList<>();
            merged_parts.add(part);
        }
        for (int l1=0; l1 <all_parts.size();l1++) {

            for (int mp=0;mp<merged_parts.size();) {

                if(l1 >= all_parts.size()) {
                    break;
                }else {
                    for (int p2 = 0; p2 < all_parts.get(l1).size(); p2++) {
                        merged_parts.get(mp).add(all_parts.get(l1).get(p2));
                    }

                    l1 = l1 + 1;
                    if (l1 == all_parts.size() / list_count) {
                        mp = mp + 1;

                    }
                }

            }
        }

        System.out.println("merged_parts: " + merged_parts);
        organiser(merged_parts);
    }
}
//for(int p2=0;p2<=all_parts.get(p).size(); p2++) {
//      merged_parts.get(i).add(String.valueOf(all_parts.get(p).get(p2)));

//    }