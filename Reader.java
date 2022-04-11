

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Reader {
    public static final int ID_NUM = 0;
    public static final int FROM_USER_NUM = 1;
    public static final int FROM_USER_ID_NUM = 2;
    public static final int DESIRED_USER_ID_NUM = 3;
    public static final int LANGUAGE_CODE_NUM = 4;
    public static final int SOURCE_NUM = 5;
    public static final int TEXT_NUM = 6;
    public static final int CREATED_AT_NUM = 7;
    public static final int REQUIRED_USER_NUM = 8;
    String[] text;
    String[] id;
    String[] fromUser;
    String[] source;
    String[] LanguageCode;
    String[] createdAt;
    String[] desiredUserName;
    String[] req_username;

    public Reader (String fileName){
        try{
            Path pathToFile = Paths.get(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(pathToFile)));
            reader.readLine();
            String line;
            String[] attributes;
            int i = 0;
            while (reader.readLine()!=null)
                i++;
            reader = new BufferedReader(new FileReader(String.valueOf(pathToFile)));
            text = new String[i];
            id = new String[i];
            fromUser = new String[i];
            source = new String[i];
            LanguageCode = new String[i];
            createdAt = new String[i];
            desiredUserName = new String[i];
            req_username = new String[i];
            i = 0;
            reader.readLine();
            while ((line=reader.readLine())!=null){
                attributes = line.split(",");
                text[i] = attributes[TEXT_NUM];
                id[i] = attributes[ID_NUM];
                fromUser[i] = attributes[FROM_USER_ID_NUM];
                source[i] = attributes[SOURCE_NUM];
                LanguageCode[i] = attributes[LANGUAGE_CODE_NUM];
                createdAt[i] = attributes[CREATED_AT_NUM];
                desiredUserName[i] = attributes[DESIRED_USER_ID_NUM].toLowerCase();
                req_username[i] = attributes[REQUIRED_USER_NUM].toLowerCase();
                i++;
            }
        }

        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("Testing");
    }
}
