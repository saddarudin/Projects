import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Scissors_Paper_Rock extends JFrame {
    static String name="Player1";
    BufferedImage image1,image2,image3;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    ImageIcon scissors,rock,paper;
    Image img1,img2,img3,image;
    int computerCount=0,userCount=0,round=1;
    JButton button;

    Scissors_Paper_Rock(){
        image= Toolkit.getDefaultToolkit().getImage("game1.png");
        this.setTitle("Rock Scissors and Paper Game");
        this.setLayout(null);
        this.setBounds(300,50,700,600);
        this.setIconImage(image);
        this.getContentPane().setBackground(Color.CYAN);
        this.setResizable(false);
        try {
            image1= ImageIO.read(new File("scissors.jpg"));
            image3= ImageIO.read(new File("paper.png"));
            image2= ImageIO.read(new File("rock.jpeg"));
            img1=image1.getScaledInstance(100,100,Image.SCALE_DEFAULT);
            img2=image2.getScaledInstance(100,100,Image.SCALE_DEFAULT);
            img3=image3.getScaledInstance(100,100,Image.SCALE_DEFAULT);
            scissors=new ImageIcon(img1);
            rock=new ImageIcon(img2);
            paper=new ImageIcon(img3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l1=new JLabel("Scissors");
        l1.setBounds(50,150,200,150);
        l1.setIcon(scissors);
        l1.setVerticalTextPosition(JLabel.TOP);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setFont(new Font("SANS_SERIF",Font.BOLD,25));
        l2=new JLabel("Rock");
        l2.setBounds(250,150,200,150);
        l2.setIcon(rock);
        l2.setVerticalTextPosition(JLabel.TOP);
        l2.setHorizontalTextPosition(JLabel.CENTER);
        l2.setFont(new Font("SANS_SERIF",Font.BOLD,25));
        l3=new JLabel("Paper");
        l3.setBounds(450,150,200,150);
        l3.setIcon(paper);
        l3.setVerticalTextPosition(JLabel.TOP);
        l3.setHorizontalTextPosition(JLabel.CENTER);
        l3.setFont(new Font("SANS_SERIF",Font.BOLD,25));
        l4=new JLabel("WELCOME "+name);
        l4.setBounds(100,20,500,50);
        l4.setFont(new Font("SANS_SERIF",Font.BOLD,40));
        l4.setForeground(new Color(255,51,51));
        l5=new JLabel("Click any picture to select your choice");
        l5.setBounds(150,320,500,40);
        l5.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        l5.setForeground(new Color(51,51,255));
        l6=new JLabel ();
        l6.setBounds(50,400,500,40);
        l6.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        l6.setForeground(new Color(32,32,32));

        l7=new JLabel();
        l7.setBounds(50,460,500,40);
        l7.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        l7.setForeground(new Color(32,32,32));
        l8=new JLabel("Round "+round);
        l8.setBounds(50,520,100,40);
        l8.setFont(new Font("SANS_SERIF",Font.BOLD,20));
        l8.setForeground(new Color(153,0,0));
        button=new JButton("Submit and see the result");
        button.setBounds(400,500,200,30);
        l1.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Random random=new Random();
                int computer=random.nextInt(3)+1;
                l6.setText("Your choice: Scissors");
                if(computer==2){
                    l7.setText("Computer's choice: Rock");
                    computerCount++;
                }
                else if(computer==3){
                    l7.setText("Computer's choice: Paper");
                    userCount++;
                }
                else l7.setText("Computer's choice: Scissors");
                l8.setText("Round "+(++round));
            }
        });
        l2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Random random=new Random();
                int computer=random.nextInt(3)+1;
                l6.setText("Your choice: Rock");
                if(computer==1){
                    l7.setText("Computer's choice: Scissors");
                    userCount++;
                }
                else if(computer==3){
                    l7.setText("Computer's choice: Paper");
                    computerCount++;
                }
                else l7.setText("Computer's choice: Rock");
                l8.setText("Round "+(++round));
            }
        });
         l3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Random random=new Random();
                int computer=random.nextInt(3)+1;
                l6.setText("Your choice: Paper");
                if(computer==1){
                    l7.setText("Computer's choice: Scissors");
                    computerCount++;
                }
                else if(computer==2){
                    l7.setText("Computer's choice: Rock");
                    userCount++;
                }
                else l7.setText("Computer's choice: Paper");
                l8.setText("Round "+(++round));
            }
        });
         button.addActionListener(e->{
             if(l6.getText().isEmpty()){
                 JOptionPane.showMessageDialog(null,"Play at least one round!!!");
                 this.dispose();
                 new Scissors_Paper_Rock();
             }
             else {
                 if(computerCount>userCount)JOptionPane.showMessageDialog(null,"Computer win by ("+computerCount+"-"+userCount+")");
                 else if(computerCount<userCount)JOptionPane.showMessageDialog(null,Scissors_Paper_Rock.name+" win by ("+userCount+"-"+computerCount+")");
                 else JOptionPane.showMessageDialog(null,"Series Drawn ("+computerCount+"-"+userCount+")");
                 this.dispose();
             }
         });


        this.add(l1);
        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);
        this.add(l6);
        this.add(l7);
        this.add(l8);
        this.add(button);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new Loading();
//        new Scissors_Paper_Rock();
    }

}
