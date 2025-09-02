import javax.swing.*;

public class G03_1 {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {

        JOptionPane.showConfirmDialog(null, "這是 ERROR_ICON", "Confirm Test", 
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

        JOptionPane.showConfirmDialog(null, "這是 INFORMATION_ICON", "Confirm Test", 
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showConfirmDialog(null, "這是 WARNING_ICON", "Confirm Test", 
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        JOptionPane.showConfirmDialog(null, "這是 QUESTION_ICON", "Confirm Test", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}
