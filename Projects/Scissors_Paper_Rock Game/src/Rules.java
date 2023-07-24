import javax.swing.*;
import java.awt.*;

public class Rules extends JFrame {
    Image image;
    JTextArea text1,text2;
    JButton button;
    Rules(){
        image= Toolkit.getDefaultToolkit().getImage("game1.png");
        this.setTitle("Rock Scissors and Paper Game");
        this.setLayout(null);
        this.setBounds(300,50,700,600);
        this.setIconImage(image);
        this.setResizable(false);
        text2=new JTextArea();
        text2.setBounds(0,0,700,300);
        text2.setText("\t\t***How to play?***\n\n   Make any choice and other choice will be made by computer\n\n   and the result will be decided on the basis of below rules.");
        text2.setFont(new Font("Poppins",Font.ITALIC,20));
        text2.setBackground(Color.BLACK);
        text2.setForeground(Color.white);
        text2.setEditable(false);
        text1=new JTextArea();
        text1.setBounds(0,250,700,300);
        text1.setFont(new Font("Poppins",Font.ITALIC,20));
        text1.setBackground(Color.BLACK);
        text1.setForeground(Color.white);
        text1.setText("\t\t***RULES***\n   Rule 1->Scissors can cut the paper so scissors dominate the paper.\n\n   Rule 2->Paper can wrap the rock so Paper dominates the rock.\n\n   Rule 3->Rock can smash the scissors so Rock dominates the scissors.");
        text1.setEditable(false);
        button=new JButton("START");
        button.setBounds(560,450,100,20);
        button.addActionListener(e->{
            this.dispose();
            new Scissors_Paper_Rock();
        });
        this.add(button);
        this.add(text1);
        this.add(text2);


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
