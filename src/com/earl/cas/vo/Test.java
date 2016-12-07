package com.earl.cas.vo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class Test {
 
    public static void main(String[] args) {
 
        List<Map<String, Integer>> data = new ArrayList<Map<String, Integer>>();
 
        init(data);
 
        System.out.println("排序前:");
        System.out.println(data);
        sort(data);
        System.out.println("排序后:");
        System.out.println(data);
    }
 
    private static void sort(List<Map<String, Integer>> data) {
 
        Collections.sort(data, new Comparator<Map>() {
 
            public int compare(Map o1, Map o2) {
 
                Integer a = (Integer) o1.get("PRECOUNTOUT");
                Integer b = (Integer) o2.get("PRECOUNTOUT");
 
                // 升序
                return a.compareTo(b);
 
                // 降序
                // return b.compareTo(a);
            }
        });
    }
 
    private static void init(List<Map<String, Integer>> data) {
 
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("COUNTTICKET", 1);
        map.put("PRECOUNTOUT", 2);
        data.add(map);
 
        map = new HashMap<String, Integer>();
        map.put("COUNTTICKET", 6);
        map.put("PRECOUNTOUT", 7);
        data.add(map);
 
        map = new HashMap<String, Integer>();
        map.put("COUNTTICKET", 8);
        map.put("PRECOUNTOUT", 5);
        data.add(map);
 
        map = new HashMap<String, Integer>();
        map.put("COUNTTICKET", 2);
        map.put("PRECOUNTOUT", 3);
        data.add(map);
    }
}
