import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class G04_1 extends JPanel {
    // 狀態
    private int cx = 300; // 圖形中心 x
    private int cy = 200; // 圖形中心 y
    private int rOuter = 100; // 藍色外圓半徑
    private int rInner = 70;  // 白色內圓半徑
    private int sqSize = 80;  // 黃色方塊邊長

    private boolean dragging = false;
    private Target target = Target.NONE;
    private int lastX, lastY;

    private enum Target { NONE, RING, SQUARE }

    public G04_1() {
        setBackground(new Color(150, 255, 180)); // 綠色背景

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (hitSquare(x, y)) {
                    target = Target.SQUARE;
                } else if (hitRing(x, y)) {
                    target = Target.RING;
                } else {
                    target = Target.NONE;
                }
                if (target != Target.NONE) {
                    dragging = true;
                    lastX = x;
                    lastY = y;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
                target = Target.NONE;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    int dx = e.getX() - lastX;
                    int dy = e.getY() - lastY;
                    cx += dx;
                    cy += dy;
                    lastX = e.getX();
                    lastY = e.getY();
                    repaint();
                }
            }
        });
    }

    private boolean hitRing(int x, int y) {
        int dx = x - cx;
        int dy = y - cy;
        int d2 = dx * dx + dy * dy;
        return d2 <= rOuter * rOuter && d2 >= rInner * rInner;
    }

    private boolean hitSquare(int x, int y) {
        int half = sqSize / 2;
        return x >= cx - half && x <= cx + half &&
               y >= cy - half && y <= cy + half;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 抗鋸齒
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // 藍色外圓
        g2.setColor(new Color(0, 60, 210));
        g2.fillOval(cx - rOuter, cy - rOuter, 2 * rOuter, 2 * rOuter);

        // 白色內圓
        g2.setColor(Color.WHITE);
        g2.fillOval(cx - rInner, cy - rInner, 2 * rInner, 2 * rInner);

        // 黃色方塊
        g2.setColor(new Color(255, 230, 40));
        int half = sqSize / 2;
        g2.fillRect(cx - half, cy - half, sqSize, sqSize);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("G04_1 Drag Ring & Square");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(640, 480);
        f.add(new G04_1());
        f.setVisible(true);
    }
}

//cd "C:\Users\Leo\OneDrive\���\GitHub\Orderingsystem\orderingsystem\113TKUDS_2"
//javac G04_1.java
//java G04_1
