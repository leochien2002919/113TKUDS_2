public class MatrixCalculator {

    public static int[][] add(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                result[i][j] = a[i][j] + b[i][j];
        return result;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < b.length; k++)
                    result[i][j] += a[i][k] * b[k][j];
        return result;
    }

    public static int[][] transpose(int[][] a) {
        int[][] result = new int[a[0].length][a.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                result[j][i] = a[i][j];
        return result;
    }

    public static void findMaxMin(int[][] a) {
        int max = a[0][0], min = a[0][0];
        for (int[] row : a)
            for (int value : row) {
                if (value > max) max = value;
                if (value < min) min = value;
            }
        System.out.println("最大值: " + max + "，最小值: " + min);
    }
}
