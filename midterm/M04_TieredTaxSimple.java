import java.io.*;

/*
 * Time Complexity: O(n)
 * 對每一筆收入依段距計算稅額，最多檢查 4 個區間 → O(1)，
 * 共 n 筆輸入 → O(n)。
 * Space Complexity: O(1)
 */

public class M04_TieredTaxSimple {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        long totalTax = 0;

        for (int i = 0; i < n; i++) {
            long income = Long.parseLong(br.readLine().trim());
            long tax = calcTax(income);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        long avg = totalTax / n;
        System.out.println("Average: " + avg);
    }

    private static long calcTax(long income) {
        long tax = 0;

        if (income <= 120000) {
            tax += income * 5 / 100;
        } else if (income <= 500000) {
            tax += 120000 * 5 / 100;
            tax += (income - 120000) * 12 / 100;
        } else if (income <= 1000000) {
            tax += 120000 * 5 / 100;
            tax += (500000 - 120000) * 12 / 100;
            tax += (income - 500000) * 20 / 100;
        } else {
            tax += 120000 * 5 / 100;
            tax += (500000 - 120000) * 12 / 100;
            tax += (1000000 - 500000) * 20 / 100;
            tax += (income - 1000000) * 30 / 100;
        }
        return tax;
    }
}
