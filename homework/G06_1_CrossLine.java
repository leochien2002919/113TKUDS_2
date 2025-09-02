import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class G06_1_CrossLine extends JPanel implements MouseMotionListener {
    private int mouseX = -1, mouseY = -1;

    public G06_1_CrossLine() {
        setBackground(Color.WHITE);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mouseX >= 0 && mouseY >= 0) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setStroke(new BasicStroke(1f));
            g2.setColor(new Color(60, 60, 60));
            g2.drawLine(mouseX, 0, mouseX, getHeight());
            g2.drawLine(0, mouseY, getWidth(), mouseY);

            g2.setColor(new Color(200, 0, 0));
            g2.fillOval(mouseX - 3, mouseY - 3, 6, 6);

            g2.setFont(new Font("Consolas", Font.PLAIN, 14));
            String text = String.format("(%d, %d)", mouseX, mouseY);
            g2.setColor(new Color(30, 30, 30));
            g2.drawString(text, 8, 18);

            g2.dispose();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("G06_1 CrossLine - Listener Only");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setContentPane(new G06_1_CrossLine());
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
