import java.util.*;

class Task {
    String name;
    int priority;
    long time;

    Task(String name, int priority, long time) {
        this.name = name;
        this.priority = priority;
        this.time = time;
    }
}

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> {
        if (b.priority != a.priority) return b.priority - a.priority;
        return Long.compare(a.time, b.time);
    });
    private long counter = 0;

    public void addTask(String name, int priority) {
        pq.add(new Task(name, priority, counter++));
    }

    public String executeNext() {
        return pq.poll().name;
    }

    public String peek() {
        return pq.peek().name;
    }

    public void changePriority(String name, int newPriority) {
        List<Task> temp = new ArrayList<>();
        while (!pq.isEmpty()) {
            Task t = pq.poll();
            if (t.name.equals(name)) t.priority = newPriority;
            temp.add(t);
        }
        pq.addAll(temp);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap q = new PriorityQueueWithHeap();
        q.addTask("備份", 1);
        q.addTask("緊急修復", 5);
        q.addTask("更新", 3);
        System.out.println(q.executeNext());
        System.out.println(q.executeNext());
        System.out.println(q.executeNext());
    }
}
