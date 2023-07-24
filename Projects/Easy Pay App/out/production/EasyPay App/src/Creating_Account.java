import javax.swing.*;

public class Creating_Account {
    JFrame frame=new JFrame();
    private JLabel creatingNewAccountLabel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton BACKButton;
    private JButton NEXTButton;

    public Creating_Account()
    {
        creatingNewAccountLabel=new JLabel("Creating New Account");
        textField1=new JTextField();
        textField2=new JTextField();
        textField3=new JTextField();
        textField4=new JTextField();
        textField5=new JTextField();
        BACKButton=new JButton();
        NEXTButton=new JButton();
        passwordField1=new JPasswordField();
        passwordField2=new JPasswordField();
    }

    public void createUIComponents() {
        // TODO: place custom component creation code here
        frame.setSize(1500,1000);
        frame.setLayout(null);
        frame.setVisible(true);
        textField1.setBounds(700,50,200,50);
        textField2.setBounds(400,100,200,50);
        frame.add(textField1);
        frame.add(textField2);
    }
}
