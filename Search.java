import java.util.ArrayList;
import java.util.HashMap;

public class Search {
	HashMap<Integer, Double> FinalWeight;
	public static String[] text_tweets;
	public static String[] user_id;
	public static String[] fromUser;
	public static String[] source_tweets;
   public static String[] des_user;

   public ArrayList<String> getTweets(ArrayList<Integer> indexList){
        ArrayList<String> tweets = new ArrayList<>();
        for(int index: indexList){
            tweets.add(text_tweets[index]);
        }
    return tweets;
    }
     /**
     *
     * @param LNG Search Language
     * @param DTE Search Date
     * @param USRNM Search through partial username
     */
	public Search(String LNG, String DTE, String USRNM) {
      FinalWeight = new HashMap<Integer, Double>();
      ArrayList<Integer> lng_list;
      ArrayList<Integer> dte_list;
      ArrayList<Integer> lng_dte;
      
     
      Reader read;
      read = new Reader("Twitter_Dataset.csv"); // Load dataset
      text_tweets = read.text;                  
      user_id = read.id; 
      fromUser= read.fromUser;
      source_tweets= read.source;
      des_user = read.desiredUserName;
      String[] req_user = read.req_username;
      String[] language = read.LanguageCode;
      String[] date = read.createdAt;

      
      InvertedIndex lng;
      lng = new InvertedIndex(req_user);


      String lng_query = LNG;
      System.out.println(lng.termList);
      lng_list= lng.docLists.get(lng.termList.indexOf(USRNM));

      DateWeight dte;
      String dte_input = DTE;
      //String dte_input = query[1];
      dte = new DateWeight(date,dte_input,lng_list);
      
      UserWeight des;
      String usrnm_input = USRNM.toLowerCase();
      //String usrnm_input = query[2];
      des = new UserWeight(des_user,usrnm_input);    
      
      UserWeight req;                          
      String req_usrnm_input = USRNM.toLowerCase();
      //String req_usrnm_input = query[3];
      req = new UserWeight(req_user,req_usrnm_input);
      
      double dteweight;
      double desweight;
      double req_desweight;

      for (int i : lng_list) {
         if(dte.dt.get(i)==null)
            dteweight = 0;
         else
            dteweight = dte.dt.get(i);
         if(des.user_map.get(i)==null)
            desweight = 0;
         else
            desweight = des.user_map.get(i);
         if(req.user_map.get(i)==null)
            req_desweight = 0;
         else
            req_desweight = req.user_map.get(i);
         double score = Math.round(((dteweight*.15)+(desweight*0.5) + (req_desweight*0.2))*100*100.0)/100.0; // Score calculation formula to get top results based on score
         FinalWeight.put(i,score);
      }
        ArrayList<String> tweets;
        System.out.println(lng);
        tweets = getTweets(lng_list);
        System.out.println(tweets);
   }
}

class Doc {
   int docId;
   double tw; 
   
   public Doc(int did, double weight) {
      docId = did;
      tw = weight;
   }
   
   public String toString() {
      String docIdString = docId + ": " + tw;
      return docIdString;
   }
}