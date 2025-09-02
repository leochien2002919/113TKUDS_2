// 檔名：G05-1.java
import javax.swing.*;
import java.awt.event.*;

public class G05_1 extends JFrame {
    private int step = 20; // 每次移動距離

    public G05_1() {
        setTitle("G05-1 視窗移動");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 初始置中

        // 監聽鍵盤事件
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int x = getX();
                int y = getY();

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        setLocation(x, y - step);
                        break;
                    case KeyEvent.VK_DOWN:
                        setLocation(x, y + step);
                        break;
                    case KeyEvent.VK_LEFT:
                        setLocation(x - step, y);
                        break;
                    case KeyEvent.VK_RIGHT:
                        setLocation(x + step, y);
                        break;
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new G05_1();
    }
}
