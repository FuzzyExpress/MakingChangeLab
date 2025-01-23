import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketOption;

public class RegisterPanel extends JPanel {
    public static Register register;
    private JLabel outputPanel;
    private JPanel inputPanel;
    private JTextField inputField;
    public PursePanel changePanel;
    public RegisterPanel() {
        register = new Register();
        outputPanel = new JLabel();
        inputPanel = new JPanel();
        inputField = new JTextField();
        changePanel = new PursePanel();

        // Vertical Layout: makes things stack instead of be next to each other
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBackground(Color.white);
        inputField.setBackground(Color.lightGray);
        inputField.setForeground(Color.black);
        inputField.addActionListener(new InputListener() {});
        inputField.setHorizontalAlignment(SwingConstants.CENTER);
        inputField.setMaximumSize(new Dimension(300, 30));
        inputField.setPreferredSize(new Dimension(300, 30));


        outputPanel.setBackground(Color.white);
        outputPanel.setForeground(Color.black);
        outputPanel.setHorizontalAlignment(SwingConstants.CENTER);
        outputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        this.setPreferredSize(new Dimension(500, 500));
        // inputField.setPreferredSize(new Dimension(300, 30));

        // make the input panel centred
        this.add(inputPanel);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.add(Box.createHorizontalGlue());
        inputPanel.add(inputField);
        inputPanel.add(Box.createHorizontalGlue());

        this.add(inputPanel);
        this.add(outputPanel);
        this.add(changePanel);
    }

    private class InputListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //System.out.println(e.getActionCommand());
            String str = e.getActionCommand();
            System.out.println(str);
            try {
                Purse cash = Register.makeChange(Double.parseDouble(str));
                str = cash.toString();
                outputPanel.setText( str.substring(2+7, str.length()-3) );
                changePanel.purse = cash;
                changePanel.repaint();
            }
            catch (NumberFormatException skullEmoji) {
                outputPanel.setText( "Please input a number! 🤔" );
            }


        }
    }
}
