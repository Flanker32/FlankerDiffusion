package edu.nju.software;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2016/11/30.
 */
public class IOHelper {
    public static List<String> readFileByLine(String name){
        List<String> result = new ArrayList<String>();

        File file = new File(name);
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while(br.ready()){
                result.add(br.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}