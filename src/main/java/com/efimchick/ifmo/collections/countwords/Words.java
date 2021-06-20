package com.efimchick.ifmo.collections.countwords;


import java.util.*;
import java.util.Map.Entry;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> map = new HashMap<>();

        List<String> lowerCaseLines = new ArrayList<>();
        for(String line:lines){
            lowerCaseLines.add(line.toLowerCase());
        }
        for (String line:lowerCaseLines){
            for(String word:line.split("[^a-zA-Zа-яА-Я]+")){
                if(map.containsKey(word)){
                    map.put(word, map.get(word) + 1);
                } else if(word.length() > 3){
                    map.put(word, 1);
                }
                map.remove("");
            }
        }
        return printMap((sortByComparator(map, false)));
    }


    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order){

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }


    public static String printMap(Map<String, Integer> map){
        StringBuilder str = new StringBuilder();
        for (Entry<String, Integer> entry : map.entrySet()){
            String b = entry.getKey() + " - " + entry.getValue() + "\n";
            if(entry.getValue() >= 10){
                str.append(b);
            }
        }
        return str.toString().trim();
    }
}
