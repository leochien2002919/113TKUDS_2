import javax.swing.*;

public class G03_2 {
    public static void main(String[] args) {
        // Yes / No / Cancel
        int result = JOptionPane.showConfirmDialog(null, 
                "要不要存檔？", 
                "檔案操作", 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.WARNING_MESSAGE);

        System.out.println("選擇結果：" + result);

        Object[] options = {"繼續", "放棄", "幫助"};
        int choice = JOptionPane.showOptionDialog(null, 
                "選擇下一步動作", 
                "自訂選項", 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, options, options[0]);

        System.out.println("選擇結果：" + choice);
    }
}
