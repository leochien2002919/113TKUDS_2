import java.util.*;

class ArrayEntry {
    int value, arrayIndex, elementIndex;
    ArrayEntry(int v, int ai, int ei) {
        value = v;
        arrayIndex = ai;
        elementIndex = ei;
    }
}

public class MergeKSortedArrays {
    public static List<Integer> merge(int[][] arrays) {
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.add(new ArrayEntry(arrays[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            ArrayEntry e = minHeap.poll();
            result.add(e.value);
            if (e.elementIndex + 1 < arrays[e.arrayIndex].length) {
                minHeap.add(new ArrayEntry(
                        arrays[e.arrayIndex][e.elementIndex + 1],
                        e.arrayIndex,
                        e.elementIndex + 1
                ));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(merge(new int[][]{{1,4,5},{1,3,4},{2,6}}));
        System.out.println(merge(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        System.out.println(merge(new int[][]{{1},{0}}));
    }
}
