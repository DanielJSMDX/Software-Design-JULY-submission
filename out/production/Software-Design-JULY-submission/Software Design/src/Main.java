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
        for (int l=0;l<all_parts.size();l++) {
            for (int p=0;p<all_parts.get(l).size()-1; p++) {
                if(all_parts.get(l).get(p).compareToIgnoreCase(all_parts.get(l).get(p+1)) >0){
                    String temp1 = all_parts.get(l).get(p);
                    all_parts.get(l).get(p).replace(all_parts.get(l).get(p), all_parts.get(l).get(p+1));
                    all_parts.get(l).get(p).replace(all_parts.get(l).get(p+1), temp1);



                }
            }
        }
        System.out.println("all_parts now is: " + all_parts); //just to make sure the for loop worked properly.
        combiner(all_parts);
    }

    private static void combiner(List<List<String>> all_parts) {// this will start combining the different parts together to start the official merge sort.
        List<List<String>> merged_parts = new ArrayList<>();

        int list_count = all_parts.size()/2; //to find the amount of lists required for later

        for (int L = 0; L<list_count; L++) { // creates the different parts for all_parts, to fill in later
            List<String> part = new ArrayList<>();
            merged_parts.add(part);

        }
        int p1 = 0;// part 1 of the sorting; acts as the lower bound for each for loop of i.
        int p2 = p1+1;
        for (int i=0;i<merged_parts.size(); i++) {//goes through the different lists inside merged_parts for merging.
            System.out.println(all_parts.get(p1));
            System.out.println(all_parts.get(p2));
            System.out.println("p1 = " + p1);
            System.out.println("p2 = " + p2);

            System.out.println("i = " + i);

            for(int p3=p1;p3<=p2;p3++) {//goes through the "all_parts" list and adds its contents via the inner for loop to the corresponding merged_parts list.
                System.out.println("p3 = " + p3);

                for(int p4=0;p4 <all_parts.get(p3).size();p4++) {//after adding contents from "all_parts" to "merged_parts", removes from "all_parts".
                    System.out.println("p4 = " + p4);

                    merged_parts.get(i).add((all_parts.get(p3).get(p4)));

                }


            }
            p1 = p1+2;
            p2 = p1+1;
        }


        System.out.println("merged_parts: " + merged_parts);
        organiser2(merged_parts);

    }
    private static void organiser2(List<List<String>> all_parts) { // goes through each part and makes sure they are organised.
        for (int l = 0; l < all_parts.size(); l++) {
            for (int p = 0; p < all_parts.get(l).size() - 1; p++) {
                if (all_parts.get(l).get(p).compareToIgnoreCase(all_parts.get(l).get(p + 1)) > 0) {
                    String temp1 = all_parts.get(l).get(p);
                    Collections.swap(all_parts.get(l),p, p+1);
                    System.out.println("all_parts now is: " + all_parts);


                }
            }
        }
        System.out.println("all_parts now is: " + all_parts); //just to make sure the for loop worked properly.
    }
}
//for(int p2=0;p2<=all_parts.get(p).size(); p2++) {
  //      merged_parts.get(i).add(String.valueOf(all_parts.get(p).get(p2)));

    //    }