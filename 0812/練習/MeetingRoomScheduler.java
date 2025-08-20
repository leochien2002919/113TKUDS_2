import java.util.*;

public class MeetingRoomScheduler {
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        endTimes.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= endTimes.peek()) {
                endTimes.poll();
            }
            endTimes.add(intervals[i][1]);
        }
        return endTimes.size();
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.println(minMeetingRooms(new int[][]{{9,10},{4,9},{4,17}}));
        System.out.println(minMeetingRooms(new int[][]{{1,5},{8,9},{8,9}}));
    }
}
