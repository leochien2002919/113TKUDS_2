import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class CircleObj implements AbsObj {
    int x, y, r;
    boolean fill = false;

    public CircleObj(int x, int y, int r) {
        this.x = x; this.y = y; this.r = r;
    }

    public void setFill(boolean bF) { this.fill = bF; }

    @Override
    public void paintTo(Graphics gr) {
        if (!fill) {
            gr.setColor(Color.BLACK);
            gr.drawOval(x - r, y - r, r * 2, r * 2);
        } else {
            gr.setColor(Color.WHITE);
            gr.fillOval(x - r, y - r, r * 2, r * 2);
        }
    }

    @Override
    public boolean isIn(int atX, int atY) {
        int dx = atX - x, dy = atY - y;
        int rSq = dx * dx + dy * dy;
        if (fill) return rSq < r * r;
        else {
            int r_s1 = r - 1, r_a1 = r + 1;
            return (r_s1 * r_s1 <= rSq) && (rSq <= r_a1 * r_a1);
        }
    }

    @Override
    public void move(int dx, int dy) { x += dx; y += dy; }
}

interface AbsObj {
    void paintTo(Graphics gr);
    boolean isIn(int atX, int atY);
    void move(int dx, int dy);
}

abstract class EzPanel extends JComponent implements MouseListener, MouseMotionListener {
    public EzPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    @Override protected void paintComponent(Graphics g) { super.paintComponent(g); }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}

public class G07_1 extends EzPanel {

    ArrayList<CircleObj> allObj = new ArrayList<>();
    boolean mouseIn = false;

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        if(mouseIn) gr.drawRect(0,0,getWidth()-1,getHeight()-1);
        for(CircleObj obj: allObj) obj.paintTo(gr);
    }

    @Override
    public void mouseEntered(MouseEvent e) { mouseIn = true; repaint(); }
    @Override
    public void mouseExited(MouseEvent e) { mouseIn = false; repaint(); }
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            CircleObj p = new CircleObj(e.getX(), e.getY(), 20);
            allObj.add(p);
            repaint();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            CircleObj p = new CircleObj(e.getX(), e.getY(), 30);
            allObj.add(p);
            repaint();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            CircleObj p = new CircleObj(e.getX(), e.getY(), 10);
            p.setFill(true);
            allObj.add(p);
            repaint();
        } else if(e.getButton() == MouseEvent.BUTTON3) {
            allObj.clear();
            repaint();
        }
    }

    public static void main(String[] args) {
        JFrame mf = new JFrame("G07_1 / G07_4");
        G07_1 panel = new G07_1();
        mf.setContentPane(panel);
        mf.setBounds(100,50,400,300);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
}
