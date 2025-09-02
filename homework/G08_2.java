import java.awt.*;
import javax.swing.*;

public class G08_2 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        g2.drawLine(0, height / 2, width, height / 2); 
        g2.drawLine(width / 2, 0, width / 2, height);  

        g2.setColor(Color.BLUE);
        int prevX = 0, prevY = 0;
        boolean firstPoint = true;

        for (int px = 0; px < width; px++) {
            double x = (px - width / 2.0) / 40.0; 
            double y = x * x * x - x;
            int py = (int) (height / 2 - y * 40);

            if (!firstPoint) {
                g2.drawLine(prevX, prevY, px, py);
            }
            prevX = px;
            prevY = py;
            firstPoint = false;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("G08-2: y = x^3 - x");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new G08_2());
        frame.setVisible(true);
    }
}
