import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

class AdminControl extends EasyPay{
    JLabel label;
    JButton b2,b3,b4,b5;

    AdminControl(){
        label=new JLabel("ADMIN CONTROL");
        b2=new JButton("Search any user");
        b3=new JButton("Add Balance");
        b4=new JButton("Remove Account");
        b5=new JButton("BACK");
    }

        public void search(){
            JTextField t=new JTextField();
            int option=JOptionPane.showConfirmDialog(panel,t,"Enter Mobile Number",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(option==JOptionPane.OK_OPTION)
            {
                String number=t.getText();
                int i=0;
                try {
                    Statement s = con.createStatement();
                    String queryCheck="select * from user_information where number='"+number+"'";
                    ResultSet rs=s.executeQuery(queryCheck);
                    while (rs.next())
                    {
                        String phone=rs.getString("number");
                        if(phone.equals(number)){
                            String name=rs.getString("name");
                            String username=rs.getString("username");
                            String email=rs.getString("email");
                            String cnic=rs.getString("cnic");
                            String dob=rs.getString("dob");
                            String password=rs.getString("password");
                            String balance=rs.getString("balance");
                            String num=rs.getString("number");
                            JOptionPane.showMessageDialog(null,"Name:  "+name+" \nUser Name:  "+username+
                                    " \nemail: " +email+" \nNIC# :  "+cnic+" \nDate of Birth:  "+dob+" \nPassword:  "+password+
                                    " \nBalance:  "+balance+" \nNumber:  "+num);
                            i=10;
                        }
                    }
                    if(i!=10)JOptionPane.showMessageDialog(null,"Data not found!");

                }catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,e.getMessage());
//                    System.exit(0);
                }
            }

        }
        public void addBalance() {
        JTextField t = new JTextField(),t1=new JTextField();
        int option=JOptionPane.showConfirmDialog(panel, t, "Enter Mobile Number", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(option==JOptionPane.OK_OPTION){
            String number1 = t.getText();
            ResultSet rs1;
            int i=0;
            try {
                Statement s = con.createStatement();
                String queryCheck = "select * from user_information where number='" + number1 + "'";
                rs1 = s.executeQuery(queryCheck);
                while(rs1.next())
                {
                    String phone=rs1.getString("number");
                    if(phone.equals(number1)){
                        String balance=rs1.getString("balance");
                        double oldBal=Double.parseDouble(balance);
                        int confirm=JOptionPane.showConfirmDialog(panel, t1, "Enter amount to add", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if(confirm==JOptionPane.OK_OPTION){
                            String bal=t1.getText();
                            if(bal.equals("")){
                                JOptionPane.showMessageDialog(null,"Provide correct amount");
                            }
                            else{
                                boolean result=bal.matches("[0-9]+");
                                if(result){
                                    if(bal.charAt(0)=='0')JOptionPane.showMessageDialog(panel,"First digit should not be zero");
                                    else{
                                        double newBal=Double.parseDouble(bal);
                                        double totalBal=oldBal+newBal;
                                        String totalBalance=Double.toString(totalBal);
                                        Statement stmt=con.createStatement();
                                        String changeBalance="update user_information set balance='"+totalBalance+"' where number='"+number1+"'";
                                        stmt.executeUpdate(changeBalance);
                                        JOptionPane.showMessageDialog(null,"Balance successfully added");
                                    }

                                }else{
                                    JOptionPane.showMessageDialog(null,"Enter correct amount");
                                }
                            }
                        }
                        i=10;
                    }
                }
                if(i!=10)JOptionPane.showMessageDialog(null,"Result not found!");
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
//                System.exit(0);
            }
        }
    }
        public void remove(){
        JTextField t1=new JTextField();
            int option=JOptionPane.showConfirmDialog(panel,t1,"Enter Mobile Number",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(option==JOptionPane.OK_OPTION){
                String num= t1.getText();
                int i=0;
                try {
                    Statement s1=con.createStatement();
                    String check = "select * from user_information where number='" + num + "'";
                    ResultSet rs =s1.executeQuery(check);
                    while(rs.next())
                    {
                        String phone=rs.getString("number");
                        if(phone.equals(num)){
                            Statement statement=(Statement) con.createStatement();
                            String query="delete from user_information where number='"+num+"'";
                            statement.executeUpdate(query);
                            JOptionPane.showMessageDialog(null,"Account deleted successfully!");
                            i=10;
                        }
                    }
                    if(i!=10)JOptionPane.showMessageDialog(null,"Result not found!");
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e.getMessage());
//                    System.exit(0);
                }
            }

        }
    @Override
    public void setComponents() {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width,screenSize.height);
        panel.setSize(screenSize.width,screenSize.height);
        panel.setLayout(null);
        panel.setBackground(Color.cyan);
        label.setForeground(Color.RED);
        label.setFont(new Font("Serif",Font.BOLD,50));
        label.setBounds(400,100,700,50);

        b2.setFont(new Font("Serif",Font.BOLD,30));
        b2.setBackground(Color.green);
        b2.setForeground(Color.white);
        b2.setBounds(500,250,250,40);
        b3.setFont(new Font("Serif",Font.BOLD,30));
        b3.setBackground(Color.yellow);
        b3.setForeground(Color.white);
        b3.setBounds(500,350,250,40);
        b4.setFont(new Font("Serif",Font.BOLD,30));
        b4.setBackground(Color.red);
        b4.setForeground(Color.white);
        b4.setBounds(500,450,250,40);
        b5.setFont(new Font("Serif",Font.BOLD,30));
        b5.setBackground(Color.magenta);
        b5.setForeground(Color.white);
        b5.setBounds(500,550,250,40);
        panel.add(label);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerform() {
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               AdminControl ac=new AdminControl();
               ac.search();
//               frame.dispose();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminControl ac=new AdminControl();
                ac.addBalance();
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminControl ac=new AdminControl();
                ac.remove();
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main(null);
                frame.dispose();
            }
        });

    }
}