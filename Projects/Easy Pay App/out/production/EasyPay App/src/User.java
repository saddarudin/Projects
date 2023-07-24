
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

class User extends EasyPay{
    JPanel panel1;
    JLabel label;
    JButton b1,b2,b3,b4;
    public User() {
        panel1=new JPanel(null);
        label=new JLabel("USER PAGE");
        b1=new JButton("LOGIN");
        b2=new JButton("Create A New Account");
        b3=new JButton("Forgot Password?");
        b4=new JButton("BACK");

    }
    public void setComponents() {

        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width,screenSize.height);
        panel1.setSize(screenSize.width,screenSize.height);
        panel1.setBackground(Color.cyan);
        label.setForeground(Color.RED);
        label.setFont(new Font("Serif",Font.BOLD,50));
        label.setBounds(500,50,600,50);
        b1.setFont(new Font("Serif",Font.BOLD,20));
        b1.setBackground(Color.GREEN);
        b1.setForeground(Color.white);
        b1.setBounds(500,200,300,30);

        b2.setFont(new Font("Serif",Font.BOLD,20));
        b2.setBackground(Color.RED);
        b2.setForeground(Color.white);
        b2.setBounds(500,300,300,30);

        b3.setFont(new Font("Serif",Font.BOLD,20));
        b3.setBackground(Color.YELLOW);
        b3.setForeground(Color.white);
        b3.setBounds(500,400,300,30);

        b4.setFont(new Font("Serif",Font.BOLD,20));
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.white);
        b4.setBounds(500,500,300,30);
        panel1.add(label);
        panel1.add(b1);
        panel1.add(b2);
        panel1.add(b3);
        panel1.add(b4);
        frame.add(panel1);
        panel1.setVisible(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void actionPerform() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage lp=new LoginPage();
                lp.setComponents();
                lp.actionPerform();
                frame.dispose();
            }
        });


        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreatingNewAccount cna=new CreatingNewAccount();
                cna.setComponents();
                cna.actionPerform();
                frame.dispose();
            }
        });


        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField t = new JTextField(),t1=new JTextField();
                int option=JOptionPane.showConfirmDialog(panel1, t, "Enter mobile number", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(option==JOptionPane.OK_OPTION)
                {
                    String number2=t.getText();
                    try{
                        int i=0,a=0;
                        Statement s = con.createStatement();
                        String queryCheck = "select * from user_information where number='" + number2 + "'";
                        ResultSet rs=s.executeQuery(queryCheck);
                        while (rs.next())
                        {
                            String number3=rs.getString("number");
                            if(number2.equals(number3)){
                                int confirm=JOptionPane.showConfirmDialog(panel1, t1, "username", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                                if(confirm==JOptionPane.OK_OPTION)
                                {
                                    String userName1=t1.getText();
                                    Statement st=EasyPay.con.createStatement();
                                    String userName="select username from user_information where number = '"+number2+"'";
                                    ResultSet set= st.executeQuery(userName);
                                    while(set.next()){
                                        String userName2= set.getString("username");
                                        if(userName1.equals(userName2)){
                                            Statement stat=EasyPay.con.createStatement();
                                            String userPass="select password from user_information where number = '"+number2+"'";
                                            ResultSet set1=stat.executeQuery(userPass);
                                            while(set1.next()){
                                                String actualPass=set1.getString("password");
                                                JOptionPane.showMessageDialog(null,"Your Password is: "+actualPass);
                                            }

                                            a=10;

                                        }

                                    }
                                    if(a!=10) {
                                        JOptionPane.showMessageDialog(null, "Incorrect Username");
                                    }
                                }
                                i=20;
                            }
                        }
                        if(i!=20)
                        {
                            JOptionPane.showMessageDialog(null,"Number does not match!");
                        }

                    }catch(Exception exception)
                    {
                        JOptionPane.showMessageDialog(null,exception.getMessage());
//                        System.exit(0);
                    }
                }

            }
        });


        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main(null);
                frame.dispose();
            }
        });

    }
}