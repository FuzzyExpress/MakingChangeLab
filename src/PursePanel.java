import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PursePanel extends JPanel {
    public Purse purse;

    public void supplyPurse(Purse p)
    {
        purse = p;
    }

    public void draw( Purse p )
    {
        // Main Drawing method
        purse = p;
        ModularLayout layout = new ModularLayout(true);
        this.removeAll();

        // I don't remember what this was for, but it's here for a reason
        try { purse.cash = purse.getCash(); }
        catch (Exception e) { System.out.println(e); }

        this.add(layout);

        int rowCounter = 0;
        int rowWidth   = 0;
        ModularLayout row = null;

        // the GridLayout was giving me a hard time,
        //  so I made my own with modular layouts
        for (Map.Entry<Denomination, Integer> entry : purse.getCash().entrySet())
        {
            ImagePanel image = new ImagePanel(entry.getKey(), entry.getValue(), true);
            // I couldn't get getWidth() types to work so I simulated it instead
            rowWidth += image.denomination.amt() < .99 ? 64 : 128;

            if (rowWidth >= 400)
            {
                rowCounter  = 0;
                rowWidth    = image.denomination.amt() < .99 ? 64 : 128;
            }

            if (rowCounter == 0)
            {
                row = new ModularLayout();
                layout.insert(row);
                row.insert( image );
            }
            else
            {
                row.insert( image );
            }

            rowCounter++;
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // Nothing to do here
        // The Painter is not meant to be modified.
        // Just throws millions of errors if modified.
        // View draw() instead.
    }
}
