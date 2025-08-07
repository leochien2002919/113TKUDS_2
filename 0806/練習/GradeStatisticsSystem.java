public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int sum = 0, max = scores[0], min = scores[0];
        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }
        double avg = (double) sum / scores.length;

        int[] gradeCount = new int[5]; // A~F
        int aboveAvg = 0;

        for (int score : scores) {
            if (score >= 90) gradeCount[0]++;
            else if (score >= 80) gradeCount[1]++;
            else if (score >= 70) gradeCount[2]++;
            else if (score >= 60) gradeCount[3]++;
            else gradeCount[4]++;
            if (score > avg) aboveAvg++;
        }

        System.out.println("平均分數: " + avg);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("A: " + gradeCount[0] + ", B: " + gradeCount[1] + ", C: " + gradeCount[2] +
                           ", D: " + gradeCount[3] + ", F: " + gradeCount[4]);
        System.out.println("高於平均的人數: " + aboveAvg);
    }
}
