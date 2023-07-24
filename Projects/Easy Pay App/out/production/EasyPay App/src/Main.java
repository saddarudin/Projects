
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
class EasyPay {
    public static Connection con=null;
    public static void connectWithDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver successfully loaded");
        }catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.exit(0);
        }
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "saddarudin338@");
            System.out.println("Connection Established successfully");
        }  catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception);
//            System.exit(0);
        }
    }
    public static void disconnectDB() {

        try{
            con.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }

    }
    JFrame frame;
    JPanel panel;
    JButton b1,b2;
    JLabel l1;
    public EasyPay(){
        frame=new JFrame("EasyPay Application");
        panel=new JPanel(null);
        l1=new JLabel("WELCOME  TO  EASYPAY  APPLICATION");
        b1=new JButton("ADMIN");
        b2=new JButton("USER");
    }
    void setComponents() {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width,screenSize.height);
        panel.setSize(screenSize.width,screenSize.height);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Serif",Font.BOLD,50));
        l1.setBounds(140,50,1050,100);
        b1.setFont(new Font("Serif",Font.BOLD,30));
        b1.setBackground(Color.GREEN);
        b1.setForeground(Color.white);
        b1.setBounds(570,250,150,30);
        b2.setFont(new Font("Serif",Font.BOLD,30));
        b2.setBackground(Color.RED);
        b2.setForeground(Color.white);
        b2.setBounds(570,320,150,30);
        panel.add(l1);
        panel.add(b1);
        panel.add(b2);
        panel.setBackground(Color.cyan);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void actionPerform() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField pf=new JPasswordField();
                int a=JOptionPane.showConfirmDialog(null,pf,"Enter Password",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                if(a==JOptionPane.OK_OPTION){
                    String pass=new String(pf.getPassword());
                    if(pass.equals("saddar")){
                        AdminControl ac=new AdminControl();
                        ac.setComponents();
                        ac.actionPerform();
                        frame.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Access Denied");
                        Main.main(null);
                    }
                }
                else Main.main(null);
                frame.dispose();
            }
        });

        b2.addActionListener(new ActionListener() {
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
class MyThread1 extends Thread{
    @Override
    public void run() {
        EasyPay.connectWithDB();
    }
}
class MyThread2 extends Thread{
    @Override
    public void run() {
        EasyPay easyPay=new EasyPay();
        easyPay.setComponents();
        easyPay.actionPerform();
    }
}


public class Main{
    public static void main(String[]args){
        MyThread1 t1=new MyThread1();
        MyThread2 t2=new MyThread2();

        try{
            t1.start();
            t1.join();
            t2.start();
            t2.join();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
//            System.exit(0);
        }
    }

}