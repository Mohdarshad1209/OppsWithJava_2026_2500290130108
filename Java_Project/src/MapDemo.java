import java.util.*;
class MapDemo {
    public static void main(String[] args) {
        Map<Integer,Integer> hm = new HashMap<>();
        hm.put(10,96);
        hm.put(9,95);
        hm.put(7,97);
        hm.put(1,56);
        hm.put(2,76);

        for (Map.Entry<Integer, Integer> en : hm.entrySet()) {
            System.out.println("Key = "+en.getKey()+" "+"Value = "+en.getValue() );
        }
    }
}