import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class G07_2 extends EzPanel {

    ArrayList<CircleObj> allObj = new ArrayList<>();
    CircleObj draggedObj = null;
    int offsetX, offsetY;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(CircleObj obj : allObj) {
            obj.paintTo(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int i = allObj.size()-1; i >= 0; i--) {
            CircleObj obj = allObj.get(i);
            if(obj.isIn(e.getX(), e.getY())) {
                draggedObj = obj;
                offsetX = e.getX() - obj.x;
                offsetY = e.getY() - obj.y;
                // 移到最上層
                allObj.remove(obj);
                allObj.add(obj);
                repaint();
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        draggedObj = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(draggedObj != null) {
            draggedObj.x = e.getX() - offsetX;
            draggedObj.y = e.getY() - offsetY;
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            CircleObj p = new CircleObj(e.getX(), e.getY(), 20);
            allObj.add(p);
            repaint();
        } else if(e.getButton() == MouseEvent.BUTTON3) {
            allObj.clear();
            repaint();
        }
    }

    public static void main(String[] args) {
        JFrame mf = new JFrame("G07_2 - 拖曳浮上層");
        G07_2 panel = new G07_2();
        mf.setContentPane(panel);
        mf.setBounds(100,50,400,300);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
}
