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
        organiser(all_parts, x);


    }

    private static void organiser(List<List<String>> all_parts, ArrayList<String> comparer) { // goes through each part and makes sure they are organised.
        System.out.println("running organiser");

        List<List<Integer>> organised_count_scores = new ArrayList<>();
        int list_count = all_parts.size(); //to find the amount of lists required for later
        for (int L = 0; L < list_count; L++) { // creates the different parts for all_parts, to fill in later
            List<Integer> part = new ArrayList<>();
            organised_count_scores.add(part);
        }
        int organised_count = 0;

        for (int l = 0; l < all_parts.size(); l++) {

            for (int p = 0; p < all_parts.get(l).size() - 1;) {
                if (all_parts.get(l).get(p).compareToIgnoreCase(all_parts.get(l).get(p + 1)) < 0) {
                    organised_count = organised_count + 1;
                    organised_count_scores.set(l, Collections.singletonList(organised_count));
                    p=p+1;

                }
                else if (all_parts.get(l).get(p).compareToIgnoreCase(all_parts.get(l).get(p + 1)) > 0) {
                    String temp1 = all_parts.get(l).get(p);
                    Collections.swap(all_parts.get(l), p, p + 1);
                    p=0;
                    organised_count = 0;



                }

            }
            organised_count = 0;

        }

        int verified_score = 0;
        while(verified_score <all_parts.size()) {
            for (int o=0;o<organised_count_scores.size();) {


                if (organised_count_scores.get(o).get(0) == all_parts.get(o).size()-1) {

                    verified_score = verified_score + 1;
                    o=o+1;
                }else{

                    verified_score = 0;
                    o=0;
                }
            }
        }
        if (verified_score == all_parts.size() && all_parts.size()>1) {
            combiner(all_parts,comparer);
        } else if(verified_score == all_parts.size() && all_parts.size() == 1) {
            System.out.println(all_parts.get(0));
        }else{
            organiser(all_parts, comparer);
        }


    }

    private static void combiner(List<List<String>> all_parts, ArrayList<String> comparer_for_organiser) {// this will start combining the different parts together to start the official merge sort.
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

        organiser(merged_parts,comparer_for_organiser);
    }
}
//for(int p2=0;p2<=all_parts.get(p).size(); p2++) {
//      merged_parts.get(i).add(String.valueOf(all_parts.get(p).get(p2)));

//    }s