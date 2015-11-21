package myTests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;


public class Solution
{
    public static void main(String[] args)throws Exception
    {

        SortedMap<String, Charset> charsetsMap = Charset.availableCharsets();
        System.out.println("Charsets available: "+charsetsMap.size());
        for(String name : charsetsMap.keySet()){
            Charset charset = charsetsMap.get(name);
            StringBuffer sb = new StringBuffer();
            sb.append(name);
            sb.append(" (");
            for(Iterator<String> aliases = charset.aliases().iterator();aliases.hasNext();){
                sb.append(aliases.next());
                if (aliases.hasNext())
                    sb.append(",");
            }
            sb.append(")");
            System.out.println(sb.toString());
        }
    }


}
