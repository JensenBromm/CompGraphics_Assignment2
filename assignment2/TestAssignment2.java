/**
 *
 * @author Hong Zhang
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestAssignment2 extends JPanel {
    Shape[] shapes = new Shape[2];
    int shapeIndex = 0;
    
    public TestAssignment2() {
        shapes[0] = Assignment2.createYingYang();
        shapes[1] = Assignment2.createSpirograph();
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.white);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ev) {
                shapeIndex ^= 1; // toggle 0/1
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(200, 200);
        g2.fill(shapes[shapeIndex]);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new TestAssignment2();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
