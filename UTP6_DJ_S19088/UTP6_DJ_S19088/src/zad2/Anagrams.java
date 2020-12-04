/**
 *
 *  @author Dudek Jakub S19088
 *
 */

package zad2;


import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.*;

public class Anagrams {
    private HashMap<String,List<String>> anagrams;
    Anagrams(String path){
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            List<String> allWords = new ArrayList<String >();
            for (String s:allLines) {
                String[] temp = s.split("\\s");
                for (String word: temp) {
                    allWords.add(word);
                }
            }
            anagrams = new HashMap<String,List<String>>();


            for (int i = 0; i < allWords.size() ; i++) {
                List<String>temp = new ArrayList<String>();
                char[] arr = allWords.get(i).toCharArray();
                Arrays.sort(arr);
                for (int j =0 ; j <allWords.size() ; j++) {
                    char[] arr2 = allWords.get(j).toCharArray();
                    Arrays.sort(arr2);
                        if(Arrays.equals(arr,arr2))
                            temp.add(allWords.get(j));


                }

                    String key = new String(arr);

                    if(!anagrams.containsKey(key))
                        anagrams.put(key, temp);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

   /* private boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        else {

            char[] arr1 = s1.toLowerCase().toCharArray();
            char[] arr2 = s2.toLowerCase().toCharArray();


            Arrays.sort(arr1);
            Arrays.sort(arr2);


            return (Arrays.equals(arr1, arr2));
        }
    }*/
    public List<List<String>> getSortedByAnQty () {
        List<List<String>> aList = new ArrayList<List<String>>(anagrams.values());
        Collections.sort(aList,(s1,s2)-> s2.size()- s1.size());
        return aList;
    }

    public String getAnagramsFor(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(word).append(": ");
        char[] key = word.toCharArray();
        Arrays.sort(key);
        String sKey = new String(key);
        if(anagrams.containsKey(sKey)){
        List<String> list = new ArrayList<>(anagrams.get(sKey));
        list.remove(word);
        stringBuilder.append(list);
        }else
            stringBuilder.append("null");
        return stringBuilder.toString();

    }
}
