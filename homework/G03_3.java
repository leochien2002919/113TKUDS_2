import javax.swing.*;

public class G03_3 {
    public static void main(String[] args) {

        String name = JOptionPane.showInputDialog(null, "請輸入你的名字：", "輸入視窗", JOptionPane.QUESTION_MESSAGE);

        if (name != null && !name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "哈囉，" + name + "！歡迎使用本程式！", "歡迎訊息", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "沒有輸入名字！", "錯誤", JOptionPane.ERROR_MESSAGE);
        }

        String input = JOptionPane.showInputDialog(null, "請輸入一個數字：", "數字輸入", JOptionPane.PLAIN_MESSAGE);
        try {
            int num = Integer.parseInt(input);
            JOptionPane.showMessageDialog(null, "你輸入的數字平方 = " + (num * num));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "輸入格式錯誤！", "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }
}
