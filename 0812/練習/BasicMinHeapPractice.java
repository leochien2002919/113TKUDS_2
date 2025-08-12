import java.util.*;

public class BasicMinHeapPractice {
    private List<Integer> heap = new ArrayList<>();

    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    public int extractMin() {
        if (heap.isEmpty()) throw new NoSuchElementException();
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    public int getMin() {
        if (heap.isEmpty()) throw new NoSuchElementException();
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) < heap.get(parent)) {
                Collections.swap(heap, index, parent);
                index = parent;
            } else break;
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;
            if (left < size && heap.get(left) < heap.get(smallest)) smallest = left;
            if (right < size && heap.get(right) < heap.get(smallest)) smallest = right;
            if (smallest != index) {
                Collections.swap(heap, index, smallest);
                index = smallest;
            } else break;
        }
    }

    public static void main(String[] args) {
        BasicMinHeapPractice heap = new BasicMinHeapPractice();
        int[] nums = {15, 10, 20, 8, 25, 5};
        for (int n : nums) heap.insert(n);
        while (!heap.isEmpty()) {
            System.out.print(heap.extractMin() + " ");
        }
    }
}
