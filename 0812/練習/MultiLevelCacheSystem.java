import java.util.*;

class CacheEntry {
    int key;
    String value;
    int freq;
    long time;

    CacheEntry(int key, String value, long time) {
        this.key = key;
        this.value = value;
        this.freq = 1;
        this.time = time;
    }
}

public class MultiLevelCacheSystem {
    private Map<Integer, CacheEntry> L1 = new HashMap<>();
    private Map<Integer, CacheEntry> L2 = new HashMap<>();
    private Map<Integer, CacheEntry> L3 = new HashMap<>();
    private int cap1 = 2, cap2 = 5, cap3 = 10;
    private long counter = 0;

    private void promote(Map<Integer, CacheEntry> from, Map<Integer, CacheEntry> to, int cap) {
        if (to.size() >= cap) {
            CacheEntry evict = Collections.min(to.values(), Comparator.comparingInt(e -> e.freq));
            to.remove(evict.key);
        }
        CacheEntry entry = from.values().iterator().next();
        from.remove(entry.key);
        to.put(entry.key, entry);
    }

    public void put(int key, String value) {
        CacheEntry entry = new CacheEntry(key, value, counter++);
        if (L1.size() < cap1) L1.put(key, entry);
        else if (L2.size() < cap2) L2.put(key, entry);
        else if (L3.size() < cap3) L3.put(key, entry);
    }

    public String get(int key) {
        if (L1.containsKey(key)) {
            L1.get(key).freq++;
            return L1.get(key).value;
        } else if (L2.containsKey(key)) {
            L2.get(key).freq++;
            promote(L2, L1, cap1);
            return L1.get(key).value;
        } else if (L3.containsKey(key)) {
            L3.get(key).freq++;
            promote(L3, L2, cap2);
            return L2.get(key).value;
        }
        return null;
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A"); cache.put(2, "B"); cache.put(3, "C");
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.put(4, "D"); cache.put(5, "E"); cache.put(6, "F");
    }
}
