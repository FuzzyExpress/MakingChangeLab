import javax.swing.*;
import java.awt.*;

public class ModularLayout extends JPanel {
    // Modular Layout is meant to behave like Qt QML RowLayout and ColumnLayout
    // https://doc.qt.io/qt-6/qml-qtquick-layouts-rowlayout.html#details
    // I combined both into one because I can.

    ModularLayout()
    {
        this(false);
    }

    ModularLayout(boolean vertical)
    {
        this(new JPanel(), vertical);
        this.remove(1);
    }

    ModularLayout(Component c)
    {
        this(c, false);
    }

    ModularLayout(Component c, boolean vertical)
    {
        // I love that they named the spacer "Glue"
        // So funny
        this.setLayout(new BoxLayout(this,  vertical ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS));
        this.add(vertical ? Box.createVerticalGlue() : Box.createHorizontalGlue());
        this.add(c);
        this.add(vertical ? Box.createVerticalGlue() : Box.createHorizontalGlue());
    }

    // Needed to make it so I could add stuff before the last Glue easy.
    public void insert(Component c)
    {
        int pos = this.getComponentCount() - 1;
        this.add(c, pos);
    }
}