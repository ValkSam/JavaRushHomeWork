package myTests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valk on 19.10.15.
 */
public class lev8Les8Tsk5 {
    public static void main(String[] arg){
        Map<String, String> map = new HashMap<String, String>(){{
            put("Ivanov0", "Ivan");
            put("Ivanov1", "Petr");
            put("Ivanov2", "Ivan");
            put("Ivanov3", "Sidor");
            put("Ivanov4", "Ivan");
            put("Ivanov5", "Kolya");
            put("Ivanov6", "Monya");
            put("Ivanov7", "Ivan");
            put("Ivanov8", "Leva");
            put("Ivanov9", "Ivan");
        }};


    }
    public static void removeWithDuplicatedName(Map<String, String> map)
    {
        String[] arr = map.values().toArray(new String[map.size()]);
        Arrays.sort(arr);
        boolean d = false;
        String currStr = "";
        for (String str : arr) {
            if (! str.equals(currStr)){
                currStr = str;
                d = false;
            }
            else if (!d) {
                while (map.values().remove(str)){};
                d = true;
            }
        }
    }
}
