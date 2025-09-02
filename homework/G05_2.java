// 檔名：G05-2.java
import java.awt.event.*;
import javax.swing.*;

public class G05_2 extends JFrame {
    private int startX, startY;
    private int startWidth, startHeight;

    public G05_2() {
        setTitle("G05-2 視窗縮放");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 滑鼠按下時記錄初始值
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getXOnScreen();
                startY = e.getYOnScreen();
                startWidth = getWidth();
                startHeight = getHeight();
            }
        });

        // 拖曳時計算差值並改變視窗大小
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getXOnScreen() - startX;
                int dy = e.getYOnScreen() - startY;
                setSize(startWidth + dx, startHeight + dy);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new G05_2();
    }
}
