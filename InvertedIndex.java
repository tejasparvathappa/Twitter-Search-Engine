

import java.util.*;

public class InvertedIndex {
   String[] myDocs; 
   ArrayList<String> termList; 
   ArrayList<ArrayList<Integer>> docLists; 
   
   public InvertedIndex(String[] docs) {
      myDocs = docs; 
      termList = new ArrayList<String>();
      docLists = new ArrayList<ArrayList<Integer>>();
      ArrayList<Integer> docList; 
      
      for(int i = 0; i < myDocs.length; i++) {
         String[] words = myDocs[i].split(";");
         
         for(String word : words) {
            if(!termList.contains(word)) { 
               termList.add(word); 
               docList = new ArrayList<Integer>(); 
               docList.add(i);
               docLists.add(docList); 
            }
            else { 
               int index = termList.indexOf(word); 
               docList = docLists.get(index);
               
               if(!docList.contains(i)) { 
                  docList.add(i); 
                  docLists.set(index, docList); 
               }
            }
         }
      }
   }
   
   
    /**
     *
     * @param a1 array list to compare values
     * @param a2 array list to compare from a1
     * @return
     */
   public ArrayList<Integer> merge(ArrayList<Integer> a1, ArrayList<Integer> a2) {
      ArrayList<Integer> mergedList = new ArrayList<Integer>();
      
      
      int p1 = 0, p2 = 0;
      while(p1 < a1.size() && p2 < a2.size()) {
         if(a1.get(p1).intValue() == a2.get(p2).intValue()) { 
            mergedList.add(a1.get(p1));
            p1++;
            p2++;
         }
         else if(a1.get(p1).intValue() < a2.get(p2).intValue()) p1++;
         else p2++;
      }
      return mergedList;
   }
   
   public String toString() {
      String outputString = new String();
      for(int i=0;i<termList.size();i++) {
         outputString += String.format("%-15s", termList.get(i));
         ArrayList<Integer> docList = docLists.get(i);
         for(int j = 0; j < docList.size(); j++) {
            outputString += docList.get(j) + "\t";
         }
         
         outputString += "\n";
      }
      
      return outputString;
   }
}