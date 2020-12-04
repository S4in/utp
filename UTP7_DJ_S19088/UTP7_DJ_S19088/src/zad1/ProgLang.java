package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang<K,V> {
        private LinkedHashMap<String, Collection<String>> langsMap, progsMap;
    ProgLang(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        langsMap = new LinkedHashMap<String, Collection<String>>();
        progsMap = new LinkedHashMap<String, Collection<String>>();
        for (String line:lines ) {
            String[] arr = line.split("\\t");

            LinkedHashSet<String> programmers = new LinkedHashSet<String>();
            String language = arr[0];
            String programmer;
            for (int i = 1; i < arr.length ; i++) {
                 programmer = arr[i];

                if (progsMap.containsKey(programmer)){
                    Set<String> values = (Set<String>) progsMap.get(programmer);

                    values.add(language);
                    progsMap.replace(programmer,values);
                }else{
                    progsMap.put(programmer,new LinkedHashSet<String>(Collections.singleton(language)));
                }

                programmers.add(programmer);
            }


            langsMap.put(arr[0], programmers);
        }

    }

    public LinkedHashMap<String, Collection<String>> getLangsMap() {
        return  langsMap;
    }

    public LinkedHashMap<String, Collection<String>> getProgsMap() {
        return progsMap;
    }
    public Map<K,V> sorted(Map<K,V> map, Comparator<? super Map.Entry<K,V>> comparator){
        Map<K,V> result = new LinkedHashMap<K,V>();

        map.entrySet().stream().sorted(comparator)
                .forEach(kvEntry -> result.put(kvEntry.getKey(), kvEntry.getValue()));//Collectors.toMap nie dziala i nie umiem :(


        return result;
    }

    public Map<String,Collection<String>> getLangsMapSortedByNumOfProgs() {
        return (Map<String, Collection<String>>) sorted((Map<K, V>) langsMap,(entry, entry1)->{

            int res = ((Set<String>)entry1.getValue()).size() - ((Set<String>)entry.getValue()).size();
            if(res == 0)
                res = ((String)entry.getKey()).compareTo((String)entry1.getKey());
            return res;
        });
    }

    public Map<String, Collection<String>> getProgsMapSortedByNumOfLangs() {
        return (Map<String, Collection<String>>) sorted((Map<K, V>) progsMap,(entry, entry1)->{
            int res = ((Set<String>)entry1.getValue()).size() - ((Set<String>)entry.getValue()).size();
            if(res == 0)
                res = ((String)entry.getKey()).compareTo((String)entry1.getKey());
            return res;
        });
    }
    public Map<String,Collection<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return (Map<String, Collection<String>>) filtered((Map<K, V>) progsMap, kvEntry -> ((Set<String>)kvEntry.getValue()).size()>n);
    }
    public Map<K,V> filtered(Map<K,V> map, Predicate<? super Map.Entry<K,V>> predicate){
        Map<K,V> result = new LinkedHashMap<K,V>();

        map.entrySet().stream().filter(predicate)
                .forEach(kvEntry -> result.put(kvEntry.getKey(), kvEntry.getValue()));//Collectors.toMap nie dziala i nie umiem :(


        return result;
    }

}
