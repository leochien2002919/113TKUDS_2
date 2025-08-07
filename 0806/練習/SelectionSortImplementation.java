public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int compares = 0, swaps = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                compares++;
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            if (i != minIdx) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                swaps++;
            }
            System.out.print("第 " + (i + 1) + " 輪: ");
            printArray(arr);
        }

        System.out.println("比較次數: " + compares);
        System.out.println("交換次數: " + swaps);
    }

    static void printArray(int[] arr) {
        for (int v : arr) System.out.print(v + " ");
        System.out.println();
    }
}
