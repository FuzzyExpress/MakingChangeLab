import javax.swing.*;
import java.awt.*;

public class MakingChange
{
    public static void main(String[] args) {
        // Main Window
        // Nothing Special
        JFrame frame = new JFrame("Making Change");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RegisterPanel panel = new RegisterPanel();

        Dimension windowSize = new Dimension(500, 400);
        panel.setPreferredSize(windowSize);

        // We can however set window location
        frame.setLocation(
            (Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (int) (windowSize.getWidth() / 2),
            (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - (int) (windowSize.getHeight() / 2)
        );


        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
