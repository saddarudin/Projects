import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPage {
    JFrame frame;
    JPanel panel;
    JLabel l1,l2,l3;
    static JTextField t1;
    JPasswordField passwordField;
    JCheckBox checkBox;
    JButton b1,b2;
    public LoginPage() {
        frame=new JFrame("EasyPay Application");
        panel=new JPanel(null);
        l1=new JLabel("LOGIN PAGE");
        l2=new JLabel("Enter your mobile number: ");
        l3=new JLabel("Enter password: ");
        passwordField=new JPasswordField();
        t1=new JTextField();
        b1=new JButton("Back");
        b2=new JButton("Login");
        checkBox=new JCheckBox("Show Password");
    }
    public void setComponents() {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width,screenSize.height);
        panel.setSize(screenSize.width,screenSize.height);
        panel.setBackground(Color.cyan);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Serif",Font.BOLD,50));
        l1.setBounds(500,50,600,50);
        l2.setForeground(Color.blue);
        l2.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l2.setBounds(300,200,300,30);
        l3.setForeground(Color.blue);
        l3.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l3.setBounds(300,300,300,30);
        t1.setBounds(550,200,200,30);
        passwordField.setBounds(550,300,200,30);
        b1.setFont(new Font("Serif",Font.BOLD,20));
        b1.setBackground(Color.RED);
        b1.setForeground(Color.white);
        b1.setBounds(550,400,100,30);
        b2.setFont(new Font("Serif",Font.BOLD,20));
        b2.setBackground(Color.GREEN);
        b2.setForeground(Color.white);
        b2.setBounds(700,400,100,30);
        checkBox.setBounds(800,300,100,30);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(t1);
        panel.add(passwordField);
        panel.add(b1);
        panel.add(b2);
        panel.add(checkBox);
        frame.add(panel);
        panel.setVisible(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerform() {
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBox.isSelected())
                    passwordField.setEchoChar((char)0);
                else
                    passwordField.setEchoChar('*');
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user=new User();
                user.setComponents();
                user.actionPerform();
                frame.dispose();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number1=t1.getText();
                String password1=new String(passwordField.getPassword());
                try{
                    int i=0,a=0;
                    Statement s=EasyPay.con.createStatement();
                    String query="select number from user_information where number ='"+number1+"'";
                    ResultSet set=s.executeQuery(query);
                    while(set.next())
                    {
                        String number2=set.getString("number");
                        if(number1.equals(number2)){
                            Statement s1=EasyPay.con.createStatement();
                            String query1="select password from user_information where password='"+password1+"' and number = '"+number1+"'";
                            ResultSet set1=s1.executeQuery(query1);
                            while(set1.next())
                            {
                                String password2=set1.getString("password");
                                if(password1.equals(password2))
                                {
                                   MainMenu mm=new MainMenu();
                                   mm.setComponents();
                                   mm.actionPerform();
                                   frame.dispose();
                                    a=5;
                                }
                            }
                            i=5;
                            if(a!=5)
                            {
                                JOptionPane.showMessageDialog(null,"Password is not correct !!!!");
                            }

                        }
                    }
                    if(i!=5){
                        JOptionPane.showMessageDialog(null,"Number is not correct !!!!");
                    }
                }catch(Exception exception)
                {
                    JOptionPane.showMessageDialog(null,exception.getMessage());
//                    System.exit(0);
                }
            }
        });
    }
   }
