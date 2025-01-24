import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    public static Register register;
    private JLabel outputLabel;
    private JTextField inputField;
    public PursePanel changePanel;
    public RegisterPanel() {
        register = new Register();
        outputLabel = new JLabel();
        inputField = new JTextField();
        changePanel = new PursePanel();

        // Vertical Layout: makes things stack instead of be next to each other
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBackground(Color.white);
        inputField.setBackground(Color.lightGray);
        inputField.setForeground(Color.black);
        // Need to grab inputs from the input box
        inputField.addActionListener(new InputListener() {});
        inputField.setHorizontalAlignment(SwingConstants.CENTER);
        inputField.setMaximumSize(new Dimension(300, 30));
        inputField.setPreferredSize(new Dimension(300, 30));

        outputLabel.setBackground(Color.white);
        outputLabel.setForeground(Color.black);
        outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outputLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        this.setPreferredSize(new Dimension(500, 500));
        // inputField.setPreferredSize(new Dimension(300, 30));

        // Modular Layout was made as a way to add Glue spacers more DRY
        this.add( new ModularLayout(inputField) );
        this.add( new ModularLayout(outputLabel) );
        this.add( new ModularLayout(changePanel) );

        // changePanel.setBackground(Color.white);
        outputLabel.setText(" ");


    }

    // this grabs the inputs from the box
    private class InputListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //System.out.println(e.getActionCommand());
            String str = e.getActionCommand();
            // System.out.println(str);
            try {
                Purse cash = Register.makeChange(Double.parseDouble(str));
                str = cash.toString();
                // Just trim the '< ' and ' >' off of the purse string.
                outputLabel.setText( str.substring(2+7, str.length()-1) );
                // changePanel.supplyPurse(cash);
                changePanel.draw(cash);
                changePanel.repaint();
            }
            catch (NumberFormatException skullEmoji) {
                outputLabel.setText( "Please input a number!" );
            }


        }
    }
}
