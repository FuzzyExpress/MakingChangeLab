import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class ImageViewer extends JFrame {
    public ImageViewer(Denomination denomination)
    {
        // Click on an image icon, and it will open up this
        // Show off my beautiful $$$$$
        super("Viewing " + denomination.displayName() + "");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImagePanel image = new ImagePanel(denomination);
        this.add( new ModularLayout( image ) );

        setPreferredSize( new Dimension( image.icon.getIconWidth(), image.icon.getIconHeight() ) );

        this.pack();

        Random rand = new Random();
        int x = rand.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width  - this.getWidth());
        int y = rand.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight());
        this.setLocation(x, y);

        setVisible(true);
        this.setLocation(x, y);
    }
}
