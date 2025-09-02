import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class G06_1_Draw6 extends JPanel implements MouseListener, MouseMotionListener {
    static class StrokePath {
        final ArrayList<Point> points = new ArrayList<>();
        final float width;
        public StrokePath(float width) { this.width = width; }
    }

    private final java.util.List<StrokePath> paths = new ArrayList<>();
    private StrokePath current = null;

    public G06_1_Draw6() {
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(900, 650));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.BLACK);
        for (StrokePath p : paths) {
            g2.setStroke(new BasicStroke(p.width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (int i = 1; i < p.points.size(); i++) {
                Point a = p.points.get(i - 1);
                Point b = p.points.get(i);
                g2.drawLine(a.x, a.y, b.x, b.y);
            }
        }

        g2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        g2.setColor(new Color(60, 60, 60));
        g2.drawString("左鍵拖曳：畫線｜中鍵或 Shift+左鍵：粗筆｜右鍵：清空", 10, 20);

        g2.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            paths.clear();
            repaint();
            return;
        }
        float width = 3f;
        if (SwingUtilities.isMiddleMouseButton(e) || e.isShiftDown()) width = 8f;
        current = new StrokePath(width);
        current.points.add(e.getPoint());
        paths.add(current);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (current != null) {
            current.points.add(e.getPoint());
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        current = null;
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("G06_1 Draw6 - Listener Only");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setContentPane(new G06_1_Draw6());
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
