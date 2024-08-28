import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MyPanel panel = new MyPanel();
        panel.setLocation(0, 0);
        frame.add(panel);
        frame.pack();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }


    private static class MyPanel extends JPanel {
        public MyPanel() {
            setPreferredSize(new Dimension(800, 600));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            Graphics2D g2d = (Graphics2D) g;
            ImageIcon imageIcon = new ImageIcon("src/main/resources/images/map-assets/1.png");
            Image image = imageIcon.getImage();
            g2d.drawImage(image, 0, 0, 24, 24, 0, 0, 24, 24, this);

        }
    }

}
