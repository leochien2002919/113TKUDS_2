import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class G07_3 extends EzPanel {

    ArrayList<CircleObj> allObj = new ArrayList<>();
    CircleObj hoverObj = null;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(CircleObj obj : allObj) obj.paintTo(g);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        hoverObj = null;
        for(int i = allObj.size()-1; i >= 0; i--) {
            CircleObj obj = allObj.get(i);
            if(obj.isIn(e.getX(), e.getY())) {
                hoverObj = obj;
                break;
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(hoverObj != null) {
            int notches = e.getWheelRotation();
            hoverObj.r -= notches * 2;
            if(hoverObj.r < 5) hoverObj.r = 5; 
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
        JFrame mf = new JFrame("G07_3 - 滾輪改大小");
        G07_3 panel = new G07_3();
        mf.setContentPane(panel);
        mf.setBounds(100,50,400,300);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
}
