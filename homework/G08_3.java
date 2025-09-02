import java.awt.*;
import javax.swing.*;

public class G08_3 extends JPanel {
    private static final int SCALE = 40;
    private static final int R = 5;
    private static final int r = 3;
    private static final int d = 5;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        int cx = width / 2;
        int cy = height / 2;

        g2.drawLine(0, cy, width, cy);
        g2.drawLine(cx, 0, cx, height);

        g2.setColor(Color.MAGENTA);

        int prevX = 0, prevY = 0;
        boolean first = true;

        for (double t = 0; t <= Math.PI * 40; t += 0.01) {
            double x = (R - r) * Math.cos(t) + d * Math.cos(((double)R / r - 1) * t);
            double y = (R - r) * Math.sin(t) - d * Math.sin(((double)R / r - 1) * t);

            int px = cx + (int)(x * SCALE);
            int py = cy - (int)(y * SCALE);

            if (!first) {
                g2.drawLine(prevX, prevY, px, py);
            }
            prevX = px;
            prevY = py;
            first = false;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("G08-3: 內擺線 (Hypotrochoid)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(new G08_3());
        frame.setVisible(true);
    }
}
