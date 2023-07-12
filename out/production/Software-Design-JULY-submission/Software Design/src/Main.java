import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;




public class Main {
    public static void main(String[] args) {
        ArrayList<String> m_list = new ArrayList<>(Arrays.asList("Dopey", "Doc",
                "Sleepy", "Happy", "Bashful", "Sneezy", "Grumpy")); //starting list
        splitter(m_list);

    }

    private static void splitter(ArrayList<String> x) {
        int list_count = Math.ceilDiv(x.size(), 2); //to find the amount of lists required for later

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

        List<List<Integer>> organised_count_scores = new ArrayList<>();
        int list_count = all_parts.size(); //to find the amount of lists required for later
        for (int L = 0; L < list_count; L++) { // creates the different parts for all_parts, to fill in later
            List<Integer> part = new ArrayList<>();
            organised_count_scores.add(part); // makes a list of lists for later
        }
        int organised_count = 0;//to add into the sublists of "organised_count_scores"

        for (int l = 0; l < all_parts.size();l++) {//the main for loop goes through and clarifies that the values in organised_count_score are correct to the groups.
            if(all_parts.get(l).size() == 1) {
                organised_count_scores.set(l, Collections.singletonList(organised_count));

            }else{
                for (int p = 0; p < all_parts.get(l).size() - 1;) {
                    if (all_parts.get(l).get(p).compareToIgnoreCase(all_parts.get(l).get(p + 1)) < 0 ) {
                        organised_count = organised_count + 1;
                        organised_count_scores.set(l, Collections.singletonList(organised_count));
                        p=p+1;


                    } else if (all_parts.get(l).get(p).compareToIgnoreCase(all_parts.get(l).get(p + 1)) > 0) {
                        Collections.swap(all_parts.get(l), p, p + 1);
                        p=0;
                        l=0;
                        organised_count = 0;



                    }

                }
            }

            organised_count = 0;// resets value for the next run through of the main for loop

        }

        int verified_score = 0;
        while(verified_score <all_parts.size()) {// this is a validation process to compare the overall scores gathered above to how many verifications should have taken place
                                                 // I'm not entirely sure why, but this IS needed even though it does a similar process to the above segment
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
        if (verified_score == all_parts.size() && all_parts.size()>1) {//sends it to the combiner if there's still two groups/sublists to compare
            combiner(all_parts,comparer);
        } else if(verified_score == all_parts.size() && all_parts.size() == 1) {// prints the remaining sublist with all the content (the format of the "all_parts" has the list sitll contained inside another list.)
            System.out.println("Starting set was: " + comparer);
            System.out.println("Final organised sed is: " + all_parts.get(0));
        }else{
            organiser(all_parts, comparer); //just incase something changed at the end that may have been missed.
        }


    }

    private static void combiner(List<List<String>> all_parts, ArrayList<String> comparer_for_organiser) {// this will start combining the different parts together to start the official merge sort.
        List<List<String>> merged_parts = new ArrayList<>();
        int list_count = all_parts.size() / 2; //to find the amount of lists required for later
        for (int L = 0; L < list_count; L++) { // creates the different parts for all_parts, to fill in later
            List<String> part = new ArrayList<>();
            merged_parts.add(part);
        }
        for (int l1=0; l1 <all_parts.size();l1++) {// the overall function of this for loop is to combine any sublist with it's corresponding neighbour to replicate adding the pairs back together as you work back up in merge sort.

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

//    }s    //    }