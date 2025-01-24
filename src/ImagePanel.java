import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ImagePanel extends JLabel {
    public ImageIcon icon = null;
    public Denomination denomination = null;
    public Integer count = 0;
    public JLabel label = null;
    ImagePanel(Denomination d) {
        this(d, 0, false);
    }

    ImagePanel (Denomination d, int count, boolean allowNewWindow)
    {
        // I created Image Panel for more features.
        denomination = d;
        this.count = count;

        // allowNew is tied to icon type, since ImageViewer should never be clickable
        String path = DenominationController.getImage(d, allowNewWindow ? "icon" : "image");
        icon = new ImageIcon(path);

        this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setIcon(icon);

        if (allowNewWindow)
        {
            // I make a new window to show the larger images.
            label = new JLabel("x" + count);
            label.setFont(this.label.getFont().deriveFont(16f)); // Set font size to 16
            label.setAlignmentX(Component.RIGHT_ALIGNMENT);
            label.setAlignmentY(Component.BOTTOM_ALIGNMENT); // Align to the bottom

            ModularLayout text = new ModularLayout( label );
            text.remove(2);
            this.add( text );


            this.add(label);

            // I also add a mouse Listener for click inputs.
            mouseDetector md = new mouseDetector();
            this.addMouseListener(md);
        }
    }

    class mouseDetector implements MouseListener {
        // this is where the mouse inputs are received.
        @Override
        public void mouseClicked(MouseEvent arg0) {
            System.out.println( "Mouse Clicked: " + denomination.displayName() );
            new ImageViewer(denomination);
        }

        @Override
        public void mouseEntered(MouseEvent arg0) { }

        @Override
        public void mouseExited(MouseEvent arg0) { }

        @Override
        public void mousePressed(MouseEvent arg0) { }

        @Override
        public void mouseReleased(MouseEvent arg0) { }

    }

}
