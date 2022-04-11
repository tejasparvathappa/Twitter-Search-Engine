import java.util.*;

public class UserWeight {
   String[] myDocs;
   ArrayList<String> termList;
   ArrayList<ArrayList<Doc>> docLists;
   ArrayList<List<String>> userlist;
   ArrayList<String> req_termList;
   double[] docLengthVec;
   HashMap<Integer, Double> user_map;
   double[] queryLengthVec;
   
    /**
     *
     * @param docs search through docs to match query
     * @param query input query ranked based on weight
     */
   public UserWeight(String[] docs,String query) {
      myDocs = docs;
      termList = new ArrayList<String>();
      docLists = new ArrayList<ArrayList<Doc>>();
      userlist = new ArrayList<List<String>>();
      ArrayList<Doc> docList;
      
      int N = myDocs.length;
      docLengthVec = new double[N];
      queryLengthVec = new double[N];
      
      for(int i = 0; i < myDocs.length; i++) {
         String[] tokens = myDocs[i].split(";");
         String token;
         
         String[] query1 = query.split(",");
         List<String> token1 = Arrays.asList(tokens);
         userlist.add(token1);

         for(int j = 0; j < tokens.length; j++) {
            token = tokens[j];
            
            if(!termList.contains(token)) {
               termList.add(token);
               docList = new ArrayList<Doc>();
               Doc doc = new Doc(i, 1); 
               docList.add(doc); 
               docLists.add(docList);
            }
            else {
               int index = termList.indexOf(token);
               docList = docLists.get(index);
               boolean match = false; 
               for(Doc doc : docList) {
                  if(doc.docId == i) { 
                     doc.tw++;
                     match = true;
                     break;
                  }
               }
               
               if(!match) {
                  Doc doc = new Doc(i, 1);
                  docList.add(doc);
               }
            }
         }
      } 
      
      for(int i = 0; i < termList.size(); i++) {
         docList = docLists.get(i);
         int df = docList.size(); 
         Doc doc;
         
         for(int j = 0; j < docList.size(); j++) {
            doc = docList.get(j);
            double tfidf = (1 + Math.log(doc.tw)) * Math.log(N / (df * 1.0));
            docLengthVec[doc.docId] += Math.pow(tfidf, 2);
            doc.tw = tfidf;
            docList.set(j, doc);
         }
      }
      
      for(int i = 0; i < N; i++) {
         docLengthVec[i] = Math.sqrt(docLengthVec[i]);
      }
      
      String[] query1 = query.split(",");
      rankSearch(query1);
   }
   
  /**
     * 
     * @param query to run through a rank search
     */
   public void rankSearch(String[] query) {
      user_map = new HashMap<Integer, Double>();
      ArrayList<Doc> docList;
      int N = myDocs.length;
      List<String> a = new ArrayList<String>();
      
      for(String term : query) {
         int index = termList.indexOf(term);
         if(index < 0)
             continue;
         
         docList = docLists.get(index);
         double w_t = Math.log((myDocs.length * 1.0) / docList.size()); 
         Doc doc1;
         for (int i = 0; i < docList.size(); i++){
            doc1 = docList.get(i);
            queryLengthVec[doc1.docId] += Math.pow(w_t,2);
         }
         Doc doc;
         
         for(int j = 0; j < docList.size(); j++) {
            doc = docList.get(j);
            
            double score = w_t * doc.tw; 
            if(!user_map.containsKey(doc.docId)) { 
               user_map.put(doc.docId, score); 
            }
            else {
               score += user_map.get(doc.docId); 
               user_map.put(doc.docId, score);
            }
            
         } 
      } 
      
      for(int k = 0; k < N; k++) 
         queryLengthVec[k] = Math.sqrt(queryLengthVec[k]);
      Iterator<Integer> k = user_map.keySet().iterator();
      while (k.hasNext()){
         Integer key = k.next();
         double score = user_map.get(key);
         score = score/(queryLengthVec[key]*docLengthVec[key]);
         user_map.put(key, score);
      }
      
   }
   
   public String toString() {
      String matrixString = new String();
      ArrayList<Doc> docList;
      
      for(int i = 0; i < termList.size(); i++){
         matrixString += String.format("%-15s", termList.get(i));
         docList = docLists.get(i);
         
         for(int j = 0; j < docList.size(); j++)
            matrixString+= docList.get(j) + "\t";
         
         matrixString += "\n";
      }
      
      return matrixString;
   }
}

