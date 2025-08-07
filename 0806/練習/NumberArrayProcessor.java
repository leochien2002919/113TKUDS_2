import java.util.*;

public class NumberArrayProcessor {

    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) set.add(num);
        return set.stream().mapToInt(i -> i).toArray();
    }

    public static int[] mergeSorted(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length)
            result[k++] = (a[i] < b[j]) ? a[i++] : b[j++];
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        return result;
    }

    public static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr)
            map.put(num, map.getOrDefault(num, 0) + 1);
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void splitEqual(int[] arr) {
        int total = Arrays.stream(arr).sum(), half = total / 2, sum = 0;
        List<Integer> part1 = new ArrayList<>();
        for (int num : arr) {
            if (sum + num <= half) {
                part1.add(num);
                sum += num;
            }
        }
        List<Integer> part2 = new ArrayList<>();
        for (int num : arr) if (!part1.remove((Integer) num)) part2.add(num);
        System.out.println("子陣列1: " + part1 + "\n子陣列2: " + part2);
    }
}
