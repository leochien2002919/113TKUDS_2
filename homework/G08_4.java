import java.awt.*;
import javax.swing.*;

public class G08_4 extends JPanel {
    private static final int SCALE = 100; 

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

        g2.setColor(Color.RED);

        int prevX = 0, prevY = 0;
        boolean first = true;

        for (double theta = 0; theta <= 2 * Math.PI; theta += 0.01) {
            double r = 2 * (1 - Math.sin(theta));
            double x = r * Math.cos(theta);
            double y = r * Math.sin(theta);

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
        JFrame frame = new JFrame("G08-4: 心臟線 (Cardioid)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(new G08_4());
        frame.setVisible(true);
    }
}
