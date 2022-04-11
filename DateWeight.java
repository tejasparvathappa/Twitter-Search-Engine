

import java.util.*;

public class DateWeight{
   HashMap<Integer, Double> dt;

   public DateWeight(String[] date, String dtfind, ArrayList<Integer> list){
      dt = new HashMap<Integer, Double>();
      
      for(int x = 0 ; x < date.length ; x++){
         if(list.contains(x)){
            String[] tokens = date[x].split(";");
            if(Arrays.asList(tokens).contains(dtfind)){
               dt.put(x, 1.0);
            }
           else if(Arrays.asList(tokens).contains("Any"))
               dt.put(x, 1.0);
            else{
               dt.put(x, 0.0);
            }
         }
      }
   }
}