package collection;

import java.util.HashMap;

public class HashMapLearn {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>(4);
        for (int i = 0; i < 100; i++) {
            map.put("key" + i % 3, i);
        }
    }
}
