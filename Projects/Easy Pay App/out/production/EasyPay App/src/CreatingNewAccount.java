import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreatingNewAccount {
    JFrame frame;
    JPanel panel;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JTextField name,uname,email,nic,dob,number;
    JPasswordField passwordField1,passwordField2;
    JButton b1,b2;
    JCheckBox checkBox1,checkBox2;
    String[] network={"030","031","032","033","034"};
    String mobileNum;
    JComboBox<String> comboBox=new JComboBox<>(network);

    public CreatingNewAccount() {
        frame=new JFrame("EasyPay Application");
        panel=new JPanel(null);
        l1 = new JLabel("Creating New Account");
        l2=new JLabel("Full Name:");
        l3=new JLabel("Enter user name: ");
        l4=new JLabel("Enter mobile number");
        l5=new JLabel("Enter email: ");
        l6=new JLabel("NIC#: ");
        l7=new JLabel("Date of birth: ");
        l8=new JLabel("Enter new Password: ");
        l9=new JLabel("Confirm your password: ");
        name=new JTextField();
        uname=new JTextField();
        email=new JTextField();
        nic=new JTextField();
        dob=new JTextField();
        number=new JTextField();
        passwordField1=new JPasswordField(20);
        passwordField2=new JPasswordField(20);
        checkBox1=new JCheckBox("Show Password");
        checkBox2=new JCheckBox("Show Password");
        b1=new JButton("Back");
        b2=new JButton("Next");
    }
    public void setComponents() {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width,screenSize.height);
        panel.setSize(screenSize.width,screenSize.height);
        panel.setBackground(Color.cyan);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Serif",Font.BOLD,50));
        l1.setBounds(450,50,600,70);

        l2.setForeground(Color.blue);
        l2.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l2.setBounds(300,150,300,30);
        l3.setForeground(Color.BLACK);
        l3.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l3.setBounds(300,200,300,30);

        l4.setForeground(Color.GREEN);
        l4.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l4.setBounds(300,250,300,30);
        l5.setForeground(Color.darkGray);
        l5.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l5.setBounds(300,300,300,30);

        l6.setForeground(Color.pink);
        l6.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l6.setBounds(300,350,300,30);
        l7.setForeground(Color.magenta);
        l7.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l7.setBounds(300,400,300,30);

        l8.setForeground(Color.PINK);
        l8.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l8.setBounds(300,450,300,30);

        l9.setForeground(Color.RED);
        l9.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l9.setBounds(300,500,300,30);

        name.setBounds(550,150,150,30);
        uname.setBounds(550,200,150,30);
        number.setBounds(600,250,100,30);
        comboBox.setBounds(550,250,50,30);
        email.setBounds(550,300,150,30);
        nic.setBounds(550,350,150,30);
        dob.setBounds(550,400,150,30);
        passwordField1.setBounds(550,450,150,30);
        passwordField2.setBounds(550,500,150,30);

        b1.setFont(new Font("Serif",Font.BOLD,20));
        b1.setBackground(Color.RED);
        b1.setForeground(Color.white);
        b1.setBounds(550,550,100,30);
        b2.setFont(new Font("Serif",Font.BOLD,20));
        b2.setBackground(Color.GREEN);
        b2.setForeground(Color.white);
        b2.setBounds(700,550,100,30);

        checkBox1.setBounds(750,450,100,20);
        checkBox2.setBounds(750,500,100,20);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(l4);
        panel.add(l5);
        panel.add(l6);
        panel.add(l7);
        panel.add(l8);
        panel.add(l9);
        panel.add(name);
        panel.add(uname);
        panel.add(number);
        panel.add(comboBox);
        panel.add(email);
        panel.add(nic);
        panel.add(dob);
        panel.add(b1);
        panel.add(b2);
        panel.add(passwordField1);
        panel.add(passwordField2);
        panel.add(checkBox1);
        panel.add(checkBox2);
        frame.add(panel);
        panel.setVisible(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerform() {
        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBox1.isSelected())
                    passwordField1.setEchoChar((char)0);
                else
                    passwordField1.setEchoChar('*');
            }
        });
        checkBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBox2.isSelected())
                    passwordField2.setEchoChar((char)0);
                else
                    passwordField2.setEchoChar('*');
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user=new User();
                user.actionPerform();
                user.setComponents();
                frame.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i=0;
                    String sql = "insert into user_information(name,username,email,cnic,dob,password,balance,number) values(?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = EasyPay.con.prepareStatement(sql);
                    String pass = new String(passwordField1.getPassword());
                    String conPas = new String(passwordField2.getPassword());
                    String balance = "0.00";
                    String name1=name.getText();
                    String uname1=uname.getText();
                    String email1=email.getText();
                    String nic1=nic.getText();
                    String dob1=dob.getText();
                    String number1=number.getText();
                    int length=number1.length();
                    mobileNum=comboBox.getSelectedItem()+number1;
                    boolean check=mobileNum.matches("[0-9]+");
                    if(check){
                        if(length!=8)JOptionPane.showMessageDialog(null,"Please ensure that your mobile number is correct!!");
                        else{
                            String numberCheckQuery="select * from user_information where number='" + mobileNum + "'";
                            Statement s = EasyPay.con.createStatement();
                            ResultSet rs=s.executeQuery(numberCheckQuery);
                            while(rs.next()){
                                String phone=rs.getString("number");

                                if(phone.equals(mobileNum)){
                                    JOptionPane.showMessageDialog(null,"This number is already registered with an account");
                                    i=15;
                                }
                            }
                            if(i!=15){

                                if (pass.length()<8||pass.length()>15) {
                                    JOptionPane.showMessageDialog(null, "Password should be between 7 to 16 characters");
                                } else {
                                    if (pass.equals(conPas)) {
                                        if(name1.equals("")||uname1.equals("")||email1.equals("")||nic1.equals("")||dob1.equals("")||mobileNum.equals(""))
                                            JOptionPane.showMessageDialog(null,"Something missing!!");
                                        else{
                                            ps.setString(1,name1);
                                            ps.setString(2,uname1);
                                            ps.setString(3,email1);
                                            ps.setString(4,nic1);
                                            ps.setString(5,dob1);
                                            ps.setString(6,pass);
                                            ps.setString(7,balance);
                                            ps.setString(8,mobileNum);
                                            ps.executeUpdate();
                                            JOptionPane.showMessageDialog(null, "Account Created Successfully");
                                            User user=new User();
                                            user.actionPerform();
                                            user.setComponents();
                                            frame.dispose();
                                        }

                                    } else JOptionPane.showMessageDialog(null, "Please provide same password in both fields!!");
                                }
                            }
                        }
                    }else JOptionPane.showMessageDialog(null,"Mobile number contains digits only");
                }
                catch (Exception exception)
                    {
                        JOptionPane.showMessageDialog(null,exception);
                    }
            }
        });
    }
}