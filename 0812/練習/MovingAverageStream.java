import java.util.*;

public class MovingAverageStream {
    private int size;
    private Queue<Integer> window = new LinkedList<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private int minVal = Integer.MAX_VALUE;
    private int maxVal = Integer.MIN_VALUE;
    private double sum = 0;

    public MovingAverageStream(int size) {
        this.size = size;
    }

    public double next(int val) {
        window.add(val);
        sum += val;
        minVal = Math.min(minVal, val);
        maxVal = Math.max(maxVal, val);
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) maxHeap.add(val);
        else minHeap.add(val);
        balance();
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            if (removed == minVal || removed == maxVal) {
                minVal = Collections.min(window);
                maxVal = Collections.max(window);
            }
            maxHeap.remove(removed);
            minHeap.remove(removed);
            balance();
        }
        return sum / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double)maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public int getMin() { return minVal; }
    public int getMax() { return maxVal; }

    private void balance() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));
        System.out.println(ma.next(10));
        System.out.println(ma.next(3));
        System.out.println(ma.next(5));
        System.out.println(ma.getMedian());
        System.out.println(ma.getMin());
        System.out.println(ma.getMax());
    }
}
