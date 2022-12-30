package collection;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapLearn {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("sss4", "112");
        map.put("sss2", "112");
        map.put("sss3", "112");
        print(map);
        map.get("sss2");
        print(map);
    }

    public static void print(Map<String, String> map) {
        System.out.println("----------");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
