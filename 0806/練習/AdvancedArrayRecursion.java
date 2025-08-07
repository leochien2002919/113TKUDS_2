import java.util.*;

public class AdvancedArrayRecursion {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++)
            if (arr[j] < pivot) swap(arr, ++i, j);
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }

    public static int[] mergeSortedArrays(int[] a, int[] b, int i, int j) {
        if (i >= a.length) return Arrays.copyOfRange(b, j, b.length);
        if (j >= b.length) return Arrays.copyOfRange(a, i, a.length);

        if (a[i] < b[j]) {
            int[] rest = mergeSortedArrays(a, b, i + 1, j);
            return prepend(a[i], rest);
        } else {
            int[] rest = mergeSortedArrays(a, b, i, j + 1);
            return prepend(b[j], rest);
        }
    }

    private static int[] prepend(int val, int[] arr) {
        int[] res = new int[arr.length + 1];
        res[0] = val;
        System.arraycopy(arr, 0, res, 1, arr.length);
        return res;
    }

    public static int kthSmallest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int low, int high, int k) {
        int pi = partition(arr, low, high);
        if (pi == k) return arr[pi];
        else if (pi > k) return quickSelect(arr, low, pi - 1, k);
        else return quickSelect(arr, pi + 1, high, k);
    }

    public static boolean hasSubsetSum(int[] arr, int idx, int target) {
        if (target == 0) return true;
        if (idx >= arr.length) return false;
        return hasSubsetSum(arr, idx + 1, target - arr[idx]) || hasSubsetSum(arr, idx + 1, target);
    }
}
