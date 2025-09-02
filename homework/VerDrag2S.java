import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class VerSlider extends EzPanel {
    int min=0, max=100;
    int value=50;     
    int sliderH=20;   
    boolean dragging=false;

    public VerSlider(int min, int max, int value) {
        this.min=min; this.max=max; this.value=value;
        this.setPreferredSize(new Dimension(50, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w=getWidth();
        int h=getHeight();

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(w/4, 0, w/2, h);

        int yPos = (int)((1.0 - (value - min)/(double)(max-min)) * (h-sliderH));

        g.setColor(Color.BLUE);
        g.fillRect(0, yPos, w, sliderH);
        g.setColor(Color.BLACK);
        g.drawRect(0, yPos, w-1, sliderH-1);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int y = e.getY();
        int w = getWidth();
        int h = getHeight();
        int yPos = (int)((1.0 - (value - min)/(double)(max-min)) * (h-sliderH));
        if(y >= yPos && y <= yPos + sliderH) {
            dragging = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(dragging) {
            int y = e.getY();
            int h = getHeight();
            int newValue = min + (int)((1.0 - (y - sliderH/2)/(double)(h-sliderH)) * (max-min));
            if(newValue < min) newValue = min;
            if(newValue > max) newValue = max;
            value = newValue;
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging=false;
    }

    public int getValue() { return value; }
}

public class VerDrag2S {
    public static void main(String[] args) {
        JFrame frame = new JFrame("VerSlider Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        VerSlider slider = new VerSlider(0, 100, 50);
        frame.setContentPane(slider);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Timer(50, e -> {
            frame.setTitle("VerSlider Value: " + slider.getValue());
        }).start();
    }
}
