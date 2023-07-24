import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainMenu {
    String senderNumber=LoginPage.t1.getText();
    JFrame frame;
    JPanel panel;
    JLabel l;
    JButton b1,b2,b3,b4,b5,b6;
    MainMenu(){
        frame=new JFrame("EasyPay Application");
        panel=new JPanel(null);
        l=new JLabel("WELCOME  TO  MAIN  MENU");
        b1=new JButton("Money Transfer");
        b2=new JButton("Mobile Load");
        b3=new JButton("Payments");
        b4=new JButton("Check Balance");
        b5=new JButton("Manage Pin");
        b6=new JButton("Log Out");
    }
    public void moneyTransfer(){
        JPanel p1=new JPanel(null);
        JLabel l1=new JLabel("MONEY TRANSFER");
        JButton b1,b2,b3,b4;
        b1=new JButton("Mobile account");
        b2=new JButton("Bank account");
        b3=new JButton("NIC");
        b4=new JButton("BACK");
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width,screenSize.height);
        p1.setSize(screenSize.width,screenSize.height);
        p1.setBackground(Color.cyan);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Serif",Font.BOLD,50));
        l1.setBounds(400,100,700,50);

        b1.setFont(new Font("Serif",Font.BOLD,30));
        b1.setBackground(Color.green);
        b1.setForeground(Color.white);
        b1.setBounds(500,250,250,40);

        b2.setFont(new Font("Serif",Font.BOLD,30));
        b2.setBackground(Color.red);
        b2.setForeground(Color.white);
        b2.setBounds(500,350,250,40);

        b3.setFont(new Font("Serif",Font.BOLD,30));
        b3.setBackground(Color.black);
        b3.setForeground(Color.white);
        b3.setBounds(500,450,250,40);

        b4.setFont(new Font("Serif",Font.BOLD,30));
        b4.setBackground(Color.yellow);
        b4.setForeground(Color.white);
        b4.setBounds(500,550,250,40);

        p1.add(l1);
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        frame.add(p1);
        frame.setVisible(true);
        p1.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField t=new JTextField();
                int option=JOptionPane.showConfirmDialog(panel, t, "Enter Mobile Number", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(option==JOptionPane.OK_OPTION){
                    String receiverNumber=t.getText();
                    if(senderNumber.equals(receiverNumber))
                    {
                        JOptionPane.showMessageDialog(p1,"You can't transfer balance to your own account");
                    }
                    else if(receiverNumber.equals("")){
                        JOptionPane.showMessageDialog(p1,"Provide Correct Number");
                    }
                    else{
                        try{
                            int x=0;
                            Statement s = EasyPay.con.createStatement();
                            String queryCheck = "select * from user_information where number='" + receiverNumber + "'";
                            ResultSet rs = s.executeQuery(queryCheck);
                            while (rs.next())
                            {
                                String phone=rs.getString("number");
                                if(receiverNumber.equals(phone)){
                                    String enteredBalance=JOptionPane.showInputDialog(p1,"Enter amount");
                                    if(enteredBalance!=null){
                                        if(enteredBalance.isEmpty())
                                        JOptionPane.showMessageDialog(p1,"Enter correct amount");
                                        else{
                                            boolean check=enteredBalance.matches("[0-9]+");
                                            if(check){
                                                if(enteredBalance.charAt(0)=='0')JOptionPane.showMessageDialog(p1,"First digit should not be zero");
                                                else{
                                                    Statement sm=EasyPay.con.createStatement();
                                                    String senderBal="select * from user_information where number='" + senderNumber + "'";
                                                    ResultSet reSet=sm.executeQuery(senderBal);
                                                    while(reSet.next())
                                                    {
                                                        String sendIniBal=reSet.getString("balance");
                                                        double send=Double.parseDouble(sendIniBal);
                                                        double enteredBal=Double.parseDouble(enteredBalance);
                                                        double updatedBalance=send-enteredBal;
                                                        String initialBalance=rs.getString("balance");
                                                        double initialBal=Double.parseDouble(initialBalance);
                                                        double receiverUpdate=initialBal+enteredBal;
                                                        String receiverUpdatedBalance=Double.toString(receiverUpdate);
                                                        if(updatedBalance<0)JOptionPane.showMessageDialog(p1,"Insufficient Balance");
                                                        else{
                                                            String remainingBalance=Double.toString(updatedBalance);
                                                            Statement stmt=EasyPay.con.createStatement();
                                                            String changeBalance="update user_information set balance='"+remainingBalance+"' where number='"+senderNumber+"'";
                                                            stmt.executeUpdate(changeBalance);
                                                            Statement statement=EasyPay.con.createStatement();
                                                            String setBalance="update user_information set balance='"+receiverUpdatedBalance+"' where number='"+receiverNumber+"'";
                                                            statement.executeUpdate(setBalance);
                                                            JOptionPane.showMessageDialog(p1,"Balance successfully transferred.");
                                                        }
                                                    }
                                                }

                                            }else JOptionPane.showMessageDialog(p1,"Enter correct amount");

                                        }
                                    }

                                    x=10;
                                }
                            }
                            if(x!=10){
                                JOptionPane.showMessageDialog(p1,"Account does not exist on this number");
                            }
                        }catch(Exception exception){
                            JOptionPane.showMessageDialog(p1,exception.getMessage());
//                            System.exit(0);
                        }

                    }
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String[] banks={"Al Baraka Bank (Pakistan) Limited","Allied Bank Limited","Askari Bank","Bank Alfalah Limited","Bank Al-Habib Limited","Bank Islami Pakistan Limited",
                        "Deutsche Bank A.G","The Bank of Tokyo-Mitsubishi UFJ","Dubai Islamic Bank Pakistan Limited","Faysal Bank Limited","First Women Bank Limited","Habib Bank Limited",
                        "Standard Chartered Bank (Pakistan) Limited","Habib Metropolitan Bank Limited","Industrial and Commercial Bank of China", "Industrial Development Bank of Pakistan",
                        "JS Bank Limited","MCB Bank Limited","MCB Islamic Bank Limited","Meezan Bank Limited","National Bank of Pakistan"};
                JFrame frame1=new JFrame("EasyPay Application");
                JPanel p2=new JPanel(null);
                JComboBox<String> cb=new JComboBox<>(banks);
                cb.setEditable(false);
                JLabel l=new JLabel("Bank Transfer");
                JLabel l1=new JLabel("Enter bank account number");
                JLabel l2=new JLabel("Enter receiver mobile number");
                JLabel l3=new JLabel("Select Bank");
                JLabel l4=new JLabel("Enter amount to send");
                JTextField t1=new JTextField();
                JTextField t2=new JTextField();
                JTextField t4=new JTextField();
                JButton b1=new JButton("Back");
                JButton b2=new JButton("Send");

                Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                frame1.setSize(screenSize.width,screenSize.height);
                p2.setSize(screenSize.width,screenSize.height);
                p2.setBackground(Color.cyan);

                l.setForeground(Color.RED);
                l.setFont(new Font("Serif",Font.BOLD,50));
                l.setBounds(500,100,700,50);

                l1.setForeground(Color.blue);
                l1.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
                l1.setBounds(400,200,300,30);
                l2.setForeground(Color.blue);
                l2.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
                l2.setBounds(400,300,300,30);

                l3.setForeground(Color.blue);
                l3.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
                l3.setBounds(400,400,300,30);

                l4.setForeground(Color.blue);
                l4.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
                l4.setBounds(400,500,300,30);

                t1.setBounds(650,200,200,30);
                t2.setBounds(650,300,200,30);
                cb.setBounds(650,400,200,30);
                t4.setBounds(650,500,200,30);

                b1.setFont(new Font("Serif",Font.BOLD,20));
                b1.setBackground(Color.RED);
                b1.setForeground(Color.white);
                b1.setBounds(650,600,100,30);
                b2.setFont(new Font("Serif",Font.BOLD,20));
                b2.setBackground(Color.GREEN);
                b2.setForeground(Color.white);
                b2.setBounds(900,600,100,30);

                p2.add(l);
                p2.add(l1);
                p2.add(l2);
                p2.add(l3);
                p2.add(l4);
                p2.add(t1);
                p2.add(t2);
                p2.add(t4);
                p2.add(cb);
                p2.add(b1);
                p2.add(b2);
                frame1.add(p2);
                frame1.setVisible(true);
                p2.setVisible(true);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainMenu mm=new MainMenu();
                        mm.moneyTransfer();
                        frame1.dispose();
                    }
                });
                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(t1.getText().isEmpty())JOptionPane.showMessageDialog(p2,"must enter bank account number");
                        else{
                            if(t2.getText().isEmpty())JOptionPane.showMessageDialog(p2,"must enter receiver number");
                            else{
                                if(t4.getText().isEmpty())JOptionPane.showMessageDialog(p2,"must enter amount");
                                else{
                                    String money=t4.getText();
                                    boolean check=money.matches("[0-9]+");
                                    if(check){
                                        if(money.charAt(0)=='0')JOptionPane.showMessageDialog(p2,"First digit should not be zero");
                                        else{

                                            int Ok=JOptionPane.showConfirmDialog(p2,"You are sending Rs. "+t4.getText()+"\n" +
                                                    " to bank account "+t1.getText()+" \nPress OK to confirm","Bank Transfer",JOptionPane.OK_CANCEL_OPTION);
                                            if(Ok==JOptionPane.OK_OPTION){
                                                try{
                                                    Statement st=EasyPay.con.createStatement();
                                                    String query="select * from user_information where number = '"+senderNumber+"'";
                                                    ResultSet rs=st.executeQuery(query);
                                                    while(rs.next()){
                                                        String firstBalance=rs.getString("balance");
                                                        double firstBal=Double.parseDouble(firstBalance);
                                                        double secondBal=Double.parseDouble(t4.getText());
                                                        double updatedBalance=firstBal-secondBal;
                                                        if(updatedBalance<0)JOptionPane.showMessageDialog(p2,"Insufficient Balance");
                                                        else{

                                                            String sqlQuery="update user_information set balance = '"+updatedBalance+"' where number = '"+senderNumber+"'";
                                                            PreparedStatement ps=EasyPay.con.prepareStatement(sqlQuery);
                                                            ps.executeUpdate();
                                                            JOptionPane.showMessageDialog(p2,"Transaction successful");
                                                            MainMenu mm=new MainMenu();
                                                            mm.moneyTransfer();
                                                            frame1.dispose();
                                                        }
                                                    }
                                                }catch(Exception ex)
                                                {
                                                    JOptionPane.showMessageDialog(p2,ex.getMessage());
//                                            System.exit(0);
                                                }
                                            }
                                        }
                                    }else JOptionPane.showMessageDialog(p2,"Enter correct amount");
                                }
                            }
                        }
                    }
                });
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame frame2=new JFrame("EasyPay Application");
                JPanel p2=new JPanel(null);
                JLabel l=new JLabel("NIC Transfer");
                JLabel l1=new JLabel("Enter NIC number");
                JLabel l2=new JLabel("Enter receiver mobile number");
                JLabel l4=new JLabel("Enter amount to send");
                JTextField t1=new JTextField();
                JTextField t2=new JTextField();
                JTextField t4=new JTextField();
                JButton b1=new JButton("Back");
                JButton b2=new JButton("Send");

                Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                frame2.setSize(screenSize.width,screenSize.height);
                p2.setSize(screenSize.width,screenSize.height);
                p2.setBackground(Color.cyan);

                l.setForeground(Color.RED);
                l.setFont(new Font("Serif",Font.BOLD,50));
                l.setBounds(500,100,700,50);

                l1.setForeground(Color.blue);
                l1.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
                l1.setBounds(400,200,300,30);

                l2.setForeground(Color.blue);
                l2.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
                l2.setBounds(400,300,300,30);

                l4.setForeground(Color.blue);
                l4.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
                l4.setBounds(400,400,300,30);

                t1.setBounds(650,200,200,30);
                t2.setBounds(650,300,200,30);
                t4.setBounds(650,400,200,30);

                b1.setFont(new Font("Serif",Font.BOLD,20));
                b1.setBackground(Color.RED);
                b1.setForeground(Color.white);
                b1.setBounds(650,500,100,30);
                b2.setFont(new Font("Serif",Font.BOLD,20));
                b2.setBackground(Color.GREEN);
                b2.setForeground(Color.white);
                b2.setBounds(900,500,100,30);

                p2.add(l);
                p2.add(l1);
                p2.add(l2);
                p2.add(l4);
                p2.add(t1);
                p2.add(t2);
                p2.add(t4);
                p2.add(b1);
                p2.add(b2);
                frame2.add(p2);
                frame2.setVisible(true);
                p2.setVisible(true);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainMenu mm=new MainMenu();
                        mm.moneyTransfer();
                        frame2.dispose();
                    }
                });
                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(t1.getText().isEmpty())JOptionPane.showMessageDialog(p2,"must enter nic number");
                        else{
                            if(t2.getText().isEmpty())JOptionPane.showMessageDialog(p2,"must enter receiver number");
                            else{
                                if(t4.getText().isEmpty())JOptionPane.showMessageDialog(p2,"must enter amount");
                                else{
                                    String money=t4.getText();
                                    boolean check=money.matches("[0-9]+");
                                    if(check){
                                        if(money.charAt(0)=='0')JOptionPane.showMessageDialog(p2,"First digit should not be zero");
                                        else{

                                            int Ok=JOptionPane.showConfirmDialog(p2,"You are sending Rs. "+t4.getText()+"\n" +
                                                    " on nic "+t1.getText()+" \nPress OK to confirm","NIC Transfer",JOptionPane.OK_CANCEL_OPTION);
                                            if(Ok==JOptionPane.OK_OPTION){
                                                try{
                                                    Statement st=EasyPay.con.createStatement();
                                                    String query="select * from user_information where number = '"+senderNumber+"'";
                                                    ResultSet rs=st.executeQuery(query);
                                                    while(rs.next()){
                                                        String firstBalance=rs.getString("balance");
                                                        double firstBal=Double.parseDouble(firstBalance);
                                                        double secondBal=Double.parseDouble(t4.getText());
                                                        double updatedBalance=firstBal-secondBal;
                                                        if(updatedBalance<0)JOptionPane.showMessageDialog(p2,"Insufficient Balance");
                                                        else{

                                                            String sqlQuery="update user_information set balance = '"+updatedBalance+"' where number = '"+senderNumber+"'";
                                                            PreparedStatement ps=EasyPay.con.prepareStatement(sqlQuery);
                                                            ps.executeUpdate();
                                                            JOptionPane.showMessageDialog(p2,"Transaction successful");
                                                            MainMenu mm=new MainMenu();
                                                            mm.moneyTransfer();
                                                            frame2.dispose();
                                                        }
                                                    }
                                                }catch(Exception ex)
                                                {
                                                    JOptionPane.showMessageDialog(p2,ex.getMessage());
//                                            System.exit(0);
                                                }
                                            }
                                        }

                                    }else JOptionPane.showMessageDialog(p2,"Enter correct amount");


                                }
                            }
                        }
                    }
                });

            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mm=new MainMenu();
                mm.setComponents();
                mm.actionPerform();
                frame.dispose();
            }
        });
    }
    public void mobileLoad(){
        JFrame frame3=new JFrame("EasyPay Application");
        JPanel p1=new JPanel(null);
        JLabel l=new JLabel("Mobile Load");
        JLabel l1=new JLabel("Select Network");
        JLabel l2=new JLabel("Enter mobile number");
        JLabel l3=new JLabel("Enter amount");
        JCheckBox c1=new JCheckBox("Telenor");
        JCheckBox c2=new JCheckBox("Mobilink");
        JCheckBox c3=new JCheckBox("Zong");
        JCheckBox c4=new JCheckBox("Ufone");
        ButtonGroup buttonGroup=new ButtonGroup();
        JTextField t2=new JTextField();
        JTextField t3=new JTextField();
        JButton b1=new JButton("Back");
        JButton b2=new JButton("OK");

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frame3.setSize(screenSize.width,screenSize.height);
        p1.setSize(screenSize.width,screenSize.height);
        p1.setBackground(Color.cyan);

        l.setForeground(Color.RED);
        l.setFont(new Font("Serif",Font.BOLD,50));
        l.setBounds(500,100,700,50);

        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l1.setBounds(400,200,300,30);

        l2.setForeground(Color.blue);
        l2.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l2.setBounds(400,350,300,30);

        l3.setForeground(Color.blue);
        l3.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        l3.setBounds(400,450,300,30);

        c1.setForeground(Color.RED);
        c1.setFont(new Font("Serif",Font.BOLD,20));
        c1.setBounds(650,200,100,30);


        c2.setForeground(Color.GREEN);
        c2.setFont(new Font("Serif",Font.BOLD,20));
        c2.setBounds(800,200,100,30);

        c3.setForeground(Color.BLACK);
        c3.setFont(new Font("Serif",Font.BOLD,20));
        c3.setBounds(650,250,100,30);

        c4.setForeground(Color.blue);
        c4.setFont(new Font("Serif",Font.BOLD,20));
        c4.setBounds(800,250,100,30);
        t2.setBounds(650,350,150,30);
        t3.setBounds(650,450,150,30);

        b1.setFont(new Font("Serif",Font.BOLD,20));
        b1.setBackground(Color.RED);
        b1.setForeground(Color.white);
        b1.setBounds(650,550,100,30);

        b2.setFont(new Font("Serif",Font.BOLD,20));
        b2.setBackground(Color.GREEN);
        b2.setForeground(Color.white);
        b2.setBounds(900,550,100,30);

//        b1.setBounds(450,550,70,20);
//        b2.setBounds(900,550,70,20);
        buttonGroup.add(c1);
        buttonGroup.add(c2);
        buttonGroup.add(c3);
        buttonGroup.add(c4);
        p1.add(l);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(c1);
        p1.add(c2);
        p1.add(c3);
        p1.add(c4);
        p1.add(t2);
        p1.add(t3);
        p1.add(b1);
        p1.add(b2);
        frame3.add(p1);
        frame3.setVisible(true);
        p1.setVisible(true);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mm=new MainMenu();
                mm.setComponents();
                mm.actionPerform();
                frame.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(c1.isSelected() ||c2.isSelected() ||c3.isSelected() ||c4.isSelected()){
                    if(t2.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(p1,"must enter mobile number");
                    }else{
                        if(t3.getText().isEmpty()){
                            JOptionPane.showMessageDialog(p1,"must enter amount");
                        }else{
                            String money=t3.getText();
                            boolean check=money.matches("[0-9]+");
                            if(check){
                                if(money.charAt(0)=='0')JOptionPane.showMessageDialog(p1,"First digit should not be zero");
                                else{
                                    try{
                                        Statement st=EasyPay.con.createStatement();
                                        String query="select balance from user_information where number = '"+senderNumber+"'";
                                        ResultSet rs=st.executeQuery(query);
                                        while(rs.next())
                                        {
                                            String initialBalance=rs.getString("balance");
                                            double initialBal=Double.parseDouble(initialBalance);
                                            double sendingBal=Double.parseDouble(t3.getText());
                                            double updatedBal=initialBal-sendingBal;
                                            if(updatedBal<0){
                                                JOptionPane.showMessageDialog(p1,"Insufficient Balance");
                                            }else{
                                                String queryUpdate="update user_information set balance = '"+updatedBal+"' where number = '"+senderNumber+"'";
                                                PreparedStatement ps=EasyPay.con.prepareStatement(queryUpdate);
                                                ps.executeUpdate();
                                                JOptionPane.showMessageDialog(p1,"Transaction Successful.");
                                                MainMenu mm=new MainMenu();
                                                mm.setComponents();
                                                mm.actionPerform();
                                                frame3.dispose();
                                            }
                                        }
                                    }catch(Exception ex)
                                    {
                                        JOptionPane.showMessageDialog(p1,ex.getMessage());
//                                System.exit(0);
                                    }
                                }
                            }else JOptionPane.showMessageDialog(p1,"Enter correct amount");

                        }
                    }
                }else JOptionPane.showMessageDialog(p1,"Select network");
            }
        });
    }
    public void payments(){
        JPanel p1=new JPanel(null);
        JLabel l=new JLabel("PAYMENTS");
        JButton b1=new JButton("Bill Payment");
        JButton b3=new JButton("Donation");
        JButton b4=new JButton("Loan Repayments");
        JButton b5=new JButton("Fee Collections");
        JButton b6=new JButton("Traffic Challan");
        JButton b8=new JButton("BACK");

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width,screenSize.height);
        p1.setSize(screenSize.width,screenSize.height);
        p1.setBackground(Color.cyan);
        l.setForeground(Color.RED);
        l.setFont(new Font("Serif",Font.BOLD,50));
        l.setBounds(500,50,700,50);

        b1.setFont(new Font("Serif",Font.BOLD,30));
        b1.setBackground(Color.green);
        b1.setForeground(Color.white);
        b1.setBounds(510,150,250,40);

        b3.setFont(new Font("Serif",Font.BOLD,30));
        b3.setBackground(Color.red);
        b3.setForeground(Color.white);
        b3.setBounds(510,250,250,40);

        b4.setFont(new Font("Serif",Font.BOLD,30));
        b4.setBackground(Color.black);
        b4.setForeground(Color.white);
        b4.setBounds(510,350,250,40);

        b5.setFont(new Font("Serif",Font.BOLD,30));
        b5.setBackground(Color.yellow);
        b5.setForeground(Color.white);
        b5.setBounds(510,450,250,40);

        b6.setFont(new Font("Serif",Font.BOLD,30));
        b6.setBackground(Color.pink);
        b6.setForeground(Color.white);
        b6.setBounds(510,550,250,40);

        b8.setFont(new Font("Serif",Font.BOLD,30));
        b8.setBackground(Color.magenta);
        b8.setForeground(Color.white);
        b8.setBounds(510,650,250,40);

        p1.add(l);
        p1.add(b1);
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
        p1.add(b6);
        p1.add(b8);
        frame.add(p1);
        p1.setVisible(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] bills={"Electricity","Telephone","Gas","Internet","Water"};
                JComboBox<String> cb=new JComboBox<>(bills);
                int conf=JOptionPane.showConfirmDialog(p1,cb,"Select Bill type",JOptionPane.OK_CANCEL_OPTION);
                if(conf==JOptionPane.OK_OPTION){
                    String type=(String) cb.getSelectedItem();
                    String consumerNumber=JOptionPane.showInputDialog(p1,"Enter consumer number");
                    if(consumerNumber!=null){
                        if(consumerNumber.isEmpty())JOptionPane.showMessageDialog(p1,"must enter consumer number");
                        else{
                            String amount=JOptionPane.showInputDialog(p1,"Enter amount to pay");
                            if(amount!=null){
                                if(amount.isEmpty())JOptionPane.showMessageDialog(p1,"must enter amount");
                                else {
                                    boolean check=amount.matches("[0-9]+");
                                    if(check){
                                        if(amount.charAt(0)=='0')JOptionPane.showMessageDialog(p1,"First digit should not be zero");
                                        else{
                                            try{
                                                Statement st=EasyPay.con.createStatement();
                                                String query="select balance from user_information where number = '"+senderNumber+"'";
                                                ResultSet rs=st.executeQuery(query);
                                                while(rs.next()){
                                                    String initialBalance=rs.getString("balance");
                                                    double initialBal=Double.parseDouble(initialBalance);
                                                    double enteredBal=Double.parseDouble(amount);
                                                    double updatedBal=initialBal-enteredBal;
                                                    if(updatedBal<0)JOptionPane.showMessageDialog(p1,"Insufficient Balance");
                                                    else{
                                                        int confirm=JOptionPane.showConfirmDialog(p1,"You are paying "+type+" bill\nConsumer number: "
                                                                +consumerNumber+"\namount: "+amount+"\npress OK to confirm.","Confirmation Message",JOptionPane.OK_CANCEL_OPTION);
                                                        if(confirm==JOptionPane.OK_OPTION){
                                                            String query1="update user_information set balance = '"+updatedBal+"' where number = '"+senderNumber+"'";
                                                            PreparedStatement ps=EasyPay.con.prepareStatement(query1);
                                                            ps.executeUpdate();
                                                            JOptionPane.showMessageDialog(p1,"Transaction Successful");
                                                        }
                                                    }
                                                }
                                            }catch(Exception ex){
                                                JOptionPane.showMessageDialog(p1,ex.getMessage());
//                                        System.exit(0);
                                            }
                                        }
                                    }else JOptionPane.showMessageDialog(p1,"Enter correct amount");


                                }
                            }
                        }
                    }
                }
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] donate={"EDHI","Chhipa","PM Dam Fund","PM Flood Relief","Al Khidmat Foundation","Pakistan Children Heart Foundation"};
                JComboBox<String> cb=new JComboBox<>(donate);
                int ok=JOptionPane.showConfirmDialog(p1,cb,"Select from below to donate",JOptionPane.OK_CANCEL_OPTION);
                if(ok==JOptionPane.OK_OPTION){
                    String type=(String) cb.getSelectedItem();
                    String amount=JOptionPane.showInputDialog(p1,"Enter amount to pay");
                    if(amount!=null){
                        if(amount.isEmpty())JOptionPane.showMessageDialog(p1,"must enter amount");
                        else {
                            boolean check=amount.matches("[0-9]+");
                            if(check){
                                if(amount.charAt(0)=='0')JOptionPane.showMessageDialog(p1,"First digit should not be zero");
                                else{
                                    try{
                                        Statement st=EasyPay.con.createStatement();
                                        String query="select balance from user_information where number = '"+senderNumber+"'";
                                        ResultSet rs=st.executeQuery(query);
                                        while(rs.next()){
                                            String initialBalance=rs.getString("balance");
                                            double initialBal=Double.parseDouble(initialBalance);
                                            double enteredBal=Double.parseDouble(amount);
                                            double updatedBal=initialBal-enteredBal;
                                            if(updatedBal<0)JOptionPane.showMessageDialog(p1,"Insufficient Balance");
                                            else{
                                                int confirm=JOptionPane.showConfirmDialog(p1,"You are donating Rs. "+
                                                        enteredBal+" to "+type+"\npress OK to confirm.","Confirmation Message",JOptionPane.OK_CANCEL_OPTION);
                                                if(confirm==JOptionPane.OK_OPTION){
                                                    String query1="update user_information set balance = '"+updatedBal+"' where number = '"+senderNumber+"'";
                                                    PreparedStatement ps=EasyPay.con.prepareStatement(query1);
                                                    ps.executeUpdate();
                                                    JOptionPane.showMessageDialog(p1,"Transaction Successful");
                                                }
                                            }
                                        }
                                    }catch(Exception ex){
                                        JOptionPane.showMessageDialog(p1,ex.getMessage());
//                                System.exit(0);
                                    }
                                }
                            }else JOptionPane.showMessageDialog(p1,"Enter correct amount");


                        }
                    }
                }
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] donate={"TRDP","OPRCT","KASHF","SAFCO"};
                JComboBox<String> cb=new JComboBox<>(donate);
                int ok=JOptionPane.showConfirmDialog(p1,cb,"Select from below ",JOptionPane.OK_CANCEL_OPTION);
                if(ok==JOptionPane.OK_OPTION){
                    String type=(String) cb.getSelectedItem();
                    String loanID=JOptionPane.showInputDialog(p1,"Enter Loan ID",type,JOptionPane.PLAIN_MESSAGE);
                    if(loanID!=null){
                        if(loanID.isEmpty())JOptionPane.showMessageDialog(p1,"Must enter Loan ID");
                        else{
                            String amount=JOptionPane.showInputDialog(p1,"Enter amount to pay");
                            if(amount!=null){
                                if(amount.isEmpty())JOptionPane.showMessageDialog(p1,"must enter amount");
                                else {
                                    boolean check=amount.matches("[0-9]+");
                                    if(check){
                                        if(amount.charAt(0)=='0')JOptionPane.showMessageDialog(p1,"First digit should not be zero");
                                        else{
                                            try{
                                                Statement st=EasyPay.con.createStatement();
                                                String query="select balance from user_information where number = '"+senderNumber+"'";
                                                ResultSet rs=st.executeQuery(query);
                                                while(rs.next()){
                                                    String initialBalance=rs.getString("balance");
                                                    double initialBal=Double.parseDouble(initialBalance);
                                                    double enteredBal=Double.parseDouble(amount);
                                                    double updatedBal=initialBal-enteredBal;
                                                    if(updatedBal<0)JOptionPane.showMessageDialog(p1,"Insufficient Balance");
                                                    else{
                                                        int confirm=JOptionPane.showConfirmDialog(p1,"You are paying Rs. "+
                                                                enteredBal+" to "+type+"\npress OK to confirm.","Confirmation Message",JOptionPane.OK_CANCEL_OPTION);
                                                        if(confirm==JOptionPane.OK_OPTION){
                                                            String query1="update user_information set balance = '"+updatedBal+"' where number = '"+senderNumber+"'";
                                                            PreparedStatement ps=EasyPay.con.prepareStatement(query1);
                                                            ps.executeUpdate();
                                                            JOptionPane.showMessageDialog(p1,"Transaction Successful");
                                                        }
                                                    }
                                                }
                                            }catch(Exception ex){
                                                JOptionPane.showMessageDialog(p1,ex.getMessage());
//                                        System.exit(0);
                                            }
                                        }
                                    }else JOptionPane.showMessageDialog(p1,"Enter correct amount");


                                }
                            }

                        }
                    }
                }
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] donate={"School","College","University"};
                JComboBox<String> cb=new JComboBox<>(donate);
                int ok=JOptionPane.showConfirmDialog(p1,cb,"Select from below ",JOptionPane.OK_CANCEL_OPTION);
                if(ok==JOptionPane.OK_OPTION){
                    String type=(String) cb.getSelectedItem();
                    String[] feeType={"Tution Fees","Hostel Fees","Transport Fee","Other Fees"};
                    JComboBox<String> cb1=new JComboBox<>(feeType);
                    int okay=JOptionPane.showConfirmDialog(p1,cb1,"Select from below ",JOptionPane.OK_CANCEL_OPTION);
                    if(okay==JOptionPane.OK_OPTION){
                        String type1=(String) cb1.getSelectedItem();
//                        JTextField t=new JTextField();
                        String name=JOptionPane.showInputDialog(p1,"Enter name of institute",JOptionPane.PLAIN_MESSAGE);
                        if(name!=null){
                            if(name.isEmpty())JOptionPane.showMessageDialog(p1,"must enter name");
                            else{
                                String loanID=JOptionPane.showInputDialog(p1,"Enter Voucher ID",type,JOptionPane.PLAIN_MESSAGE);
                                if(loanID!=null){
                                    if(loanID.isEmpty())JOptionPane.showMessageDialog(p1,"Must enter Voucher ID");
                                    else{
                                        String amount=JOptionPane.showInputDialog(p1,"Enter amount to pay");
                                        if(amount!=null){
                                            if(amount.isEmpty())JOptionPane.showMessageDialog(p1,"must enter amount");
                                            else {
                                                boolean check=amount.matches("[0-9]+");
                                                if(check){
                                                    if(amount.charAt(0)=='0')JOptionPane.showMessageDialog(p1,"First digit should not be zero");
                                                    else {
                                                        try{
                                                            Statement st=EasyPay.con.createStatement();
                                                            String query="select balance from user_information where number = '"+senderNumber+"'";
                                                            ResultSet rs=st.executeQuery(query);
                                                            while(rs.next()){
                                                                String initialBalance=rs.getString("balance");
                                                                double initialBal=Double.parseDouble(initialBalance);
                                                                double enteredBal=Double.parseDouble(amount);
                                                                double updatedBal=initialBal-enteredBal;
                                                                if(updatedBal<0)JOptionPane.showMessageDialog(p1,"Insufficient Balance");
                                                                else{
                                                                    int confirm=JOptionPane.showConfirmDialog(p1,"You are paying "+type1+
                                                                                    " \nto "+name+"\namount "+amount+"\nPress 'OK' to confirm",
                                                                            "Confirmation Message",JOptionPane.OK_CANCEL_OPTION);
                                                                    if(confirm==JOptionPane.OK_OPTION){
                                                                        String query1="update user_information set balance = '"+updatedBal+"' where number = '"+senderNumber+"'";
                                                                        PreparedStatement ps=EasyPay.con.prepareStatement(query1);
                                                                        ps.executeUpdate();
                                                                        JOptionPane.showMessageDialog(p1,"Transaction Successful");
                                                                    }
                                                                }
                                                            }
                                                        }catch(Exception ex){
                                                            JOptionPane.showMessageDialog(p1,ex.getMessage());
//                                                    System.exit(0);
                                                        }
                                                    }
                                                }else JOptionPane.showMessageDialog(p1,"Enter correct amount");


                                            }
                                        }

                                    }
                        }
                    }

                        }
                    }
                }
            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] challan={"1.Exceeding prescribed speed limit.","2.Violation of traffic signals","3.Overloading a goods vehicle",
                "4.Driving a motor vehicle at night without proper lights","5.Driving a motor vehicle on the wrong side of the road",
                "6.Driving a motor vehicle with covered glasses","7.Violation of line/lane/zebra crossing etc","8.Riding/Driving a motor vehicle when/where it is prohibited",
                "9.Driving a motor vehicle without driving license","10.Using horn in silence zone","11.Driving unregistered motor vehicle","12.Driving in violation of " +
                "age limit","13.Driving motor cycle without safety helmet","14.Pillion riding by more than two persons","15.Use of hand-held mobile phone while driving",
                 "16.Non-fastening of seat belt while driving on a notified road.","17.Violation of parking rules"};
                JComboBox<String> cb=new JComboBox<>(challan);
                int ok=JOptionPane.showConfirmDialog(p1,cb,"Select type of challan:",JOptionPane.OK_CANCEL_OPTION);
                if(ok==JOptionPane.OK_OPTION){
                            String price=JOptionPane.showInputDialog(p1,"Note:For motor cycle challan =200    For other vehicle challan=500\n" +
                            "Enter amount to pay:","Traffic challan",JOptionPane.PLAIN_MESSAGE);
                    if(price!=null){
                        try{
                            Statement st=EasyPay.con.createStatement();
                            String query1="select balance from user_information where number = '"+senderNumber+"'";
                            ResultSet rs=st.executeQuery(query1);
                            while(rs.next()){
                                String initialBalance=rs.getString("balance");
                                double initialBal=Double.parseDouble(initialBalance);
                                double enteredBal=Double.parseDouble(price);
                                double updatedBalance=initialBal-enteredBal;
                                if(updatedBalance<0)JOptionPane.showMessageDialog(p1,"Insufficient Balance");
                                else{
                                    if(price.equals("200")||price.equals("500")){
                                        int confirm=JOptionPane.showConfirmDialog(p1,"You are paying challan For\n"+cb.getSelectedItem()+"\n" +
                                                "Press OK to confirm","Traffic Challan",JOptionPane.OK_CANCEL_OPTION);
                                        if(confirm==JOptionPane.OK_OPTION){
                                            String query="update user_information set balance= '"+updatedBalance+"' where number= '"+senderNumber+"'";
                                            PreparedStatement ps=EasyPay.con.prepareStatement(query);
                                            ps.executeUpdate();
                                            JOptionPane.showMessageDialog(p1,"Challan paid successfully");
                                        }
                                    }
                                    else JOptionPane.showMessageDialog(p1,"Incorrect amount");
                                }
                            }
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(p1,ex.getMessage());
//                            System.exit(0);
                        }
                    }
                }
            }
        });
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mm=new MainMenu();
                mm.setComponents();
                mm.actionPerform();
                frame.dispose();
            }
        });
    }
    public void setComponents(){
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width,screenSize.height);
        panel.setSize(screenSize.width,screenSize.height);
        panel.setBackground(Color.cyan);
        l.setForeground(Color.RED);
        l.setFont(new Font("Serif",Font.BOLD,60));
        l.setBounds(200,50,900,50);
        b1.setFont(new Font("Serif",Font.BOLD,30));
        b1.setBackground(Color.green);
        b1.setForeground(Color.white);
        b1.setBounds(450,150,250,40);

        b2.setFont(new Font("Serif",Font.BOLD,30));
        b2.setBackground(Color.red);
        b2.setForeground(Color.white);
        b2.setBounds(450,250,250,40);

        b3.setFont(new Font("Serif",Font.BOLD,30));
        b3.setBackground(Color.black);
        b3.setForeground(Color.white);
        b3.setBounds(450,350,250,40);

        b4.setFont(new Font("Serif",Font.BOLD,30));
        b4.setBackground(Color.yellow);
        b4.setForeground(Color.white);
        b4.setBounds(450,450,250,40);

        b5.setFont(new Font("Serif",Font.BOLD,30));
        b5.setBackground(Color.pink);
        b5.setForeground(Color.white);
        b5.setBounds(450,550,250,40);

        b6.setFont(new Font("Serif",Font.BOLD,30));
        b6.setBackground(Color.magenta);
        b6.setForeground(Color.white);
        b6.setBounds(450,650,250,40);
        panel.add(l);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerform(){
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mm=new MainMenu();
                mm.moneyTransfer();
                frame.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mm=new MainMenu();
                mm.mobileLoad();
                frame.dispose();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mm=new MainMenu();
                mm.payments();
                frame.dispose();
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Statement st=EasyPay.con.createStatement();
                    String query="select balance from user_information where number = '"+senderNumber+"'";
                    ResultSet rs=st.executeQuery(query);
                    while(rs.next())
                    {
                        String balance=rs.getString("balance");
                        JOptionPane.showMessageDialog(panel,"Your available balance is : "+balance);
                    }
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(panel,ex.getMessage());
//                    System.exit(0);
                }
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String userName=JOptionPane.showInputDialog(panel,"Enter user name");
                    if(userName!=null){
                        if(userName.isEmpty()){
                            JOptionPane.showMessageDialog(panel,"must provide user name");
                        }
                        else{

                            Statement st=EasyPay.con.createStatement();
                            String query="select username from user_information where number= '"+senderNumber+"'";
                            ResultSet rs=st.executeQuery(query);
                            while(rs.next()){
                                String username1=rs.getString("username");
                                if(userName.equals(username1)){
                                    String oldPassword=JOptionPane.showInputDialog(panel,"Enter old password");
                                    if(oldPassword!=null){
                                        if(oldPassword.isEmpty()){
                                            JOptionPane.showMessageDialog(panel,"must provide password");
                                        }
                                        else{
                                            Statement state=EasyPay.con.createStatement();
                                            String query1="select password from user_information where number = '"+senderNumber+"'";
                                            ResultSet resultSet=state.executeQuery(query1);
                                            while(resultSet.next()){
                                                String pass1=resultSet.getString("password");
                                                if(oldPassword.equals(pass1)){
                                                    String newPassword=JOptionPane.showInputDialog(panel,"Enter new password");
                                                    if(newPassword!=null){
                                                        if(newPassword.isEmpty()){
                                                            JOptionPane.showMessageDialog(panel,"Password can't be empty");
                                                        }
                                                        else{
                                                            String query2="update user_information set password = '"+newPassword+"' where number = '"+senderNumber+"'";
                                                            PreparedStatement ps=EasyPay.con.prepareStatement(query2);
                                                            ps.executeUpdate();
                                                            JOptionPane.showMessageDialog(panel,"Password changed successfully");
                                                        }
                                                    }

                                                }else{
                                                    JOptionPane.showMessageDialog(panel,"Incorrect password!! Try again");
                                                }
                                            }
                                        }
                                    }


                                }
                                else{
                                    JOptionPane.showMessageDialog(panel,"Incorrect user name!!!");
                                }
                            }
                        }

                    }

                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(panel,ex.getMessage());
//                    System.exit(0);
                }

            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user=new User();
                user.setComponents();
                user.actionPerform();
                frame.dispose();
            }
        });
    }
}
