import java.io.*;

public class M02_YouBikeNextArrival {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[] times = new int[n];

        for (int i = 0; i < n; i++) {
            times[i] = toMinutes(br.readLine().trim());
        }

        int query = toMinutes(br.readLine().trim());

        int idx = firstGreater(times, query);

        if (idx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHmm(times[idx]));
        }
    }

    private static int toMinutes(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    private static String toHHmm(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    private static int firstGreater(int[] arr, int target) {
        int l = 0, r = arr.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] > target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
