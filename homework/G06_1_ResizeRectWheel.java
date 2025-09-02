import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class G06_1_ResizeRectWheel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private Rectangle rect = new Rectangle(200, 150, 200, 140);
    private Point dragOffset = null; 
    private final int MIN_W = 40, MIN_H = 40, MAX_W = 2000, MAX_H = 2000;

    public G06_1_ResizeRectWheel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(900, 600));
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(240, 248, 255));
        g2.fill(rect);
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(new Color(30, 144, 255));
        g2.draw(rect);

        int cx = rect.x + rect.width / 2;
        int cy = rect.y + rect.height / 2;
        g2.setStroke(new BasicStroke(1f));
        g2.setColor(new Color(80, 80, 80));
        g2.drawLine(cx, rect.y, cx, rect.y + rect.height);
        g2.drawLine(rect.x, cy, rect.x + rect.width, cy);

        g2.setFont(new Font("Consolas", Font.PLAIN, 14));
        g2.setColor(new Color(50, 50, 50));
        String info = String.format("Rect x=%d y=%d  w=%d h=%d  (拖曳移動｜滾輪縮放｜Ctrl/Shift 改變縮放步伐)",
                rect.x, rect.y, rect.width, rect.height);
        g2.drawString(info, 10, 20);

        g2.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (rect.contains(e.getPoint())) {
            dragOffset = new Point(e.getX() - rect.x, e.getY() - rect.y);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragOffset != null) {
            rect.x = e.getX() - dragOffset.x;
            rect.y = e.getY() - dragOffset.y;
            clampRectToPanel();
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragOffset = null;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rot = e.getWheelRotation(); 
        double step = 10.0;
        if (e.isControlDown()) step = 30.0;
        else if (e.isShiftDown()) step = 3.0;

        int dw = (int) Math.round(step * -rot);
        int dh = (int) Math.round(step * -rot * (rect.height / Math.max(1.0, rect.width)));

        Point p = e.getPoint();
        double sx = (p.x - rect.x) / (double) Math.max(1, rect.width);
        double sy = (p.y - rect.y) / (double) Math.max(1, rect.height);

        int newW = Math.max(MIN_W, Math.min(MAX_W, rect.width + dw));
        int newH = Math.max(MIN_H, Math.min(MAX_H, rect.height + dh));

        int dx = (int) Math.round((rect.width - newW) * sx);
        int dy = (int) Math.round((rect.height - newH) * sy);

        rect.setBounds(rect.x + dx, rect.y + dy, newW, newH);
        clampRectToPanel();
        repaint();
    }

    private void clampRectToPanel() {
        int maxX = getWidth() - rect.width;
        int maxY = getHeight() - rect.height;
        rect.x = Math.max(0, Math.min(rect.x, maxX));
        rect.y = Math.max(0, Math.min(rect.y, maxY));
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("G06_1 ResizeRectWheel - Listener Only");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setContentPane(new G06_1_ResizeRectWheel());
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
