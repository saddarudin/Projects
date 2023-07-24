import javax.swing.*;
import java.awt.*;

public class Loading extends JFrame {
    JProgressBar bar;
    Image image;
    JLabel label;
    Loading(){
//        JOptionPane.showMessageDialog(null,"Enter your name: ","Username",JOptionPane.PLAIN_MESSAGE);
        image=Toolkit.getDefaultToolkit().getImage("game1.png");
        this.setTitle("Rock Scissors and Paper Game");
        this.setLayout(null);
        this.setBounds(300,50,700,600);
        this.setIconImage(image);
        this.setResizable(false);
        label=new JLabel();
        label.setSize(700,600);
        label.setBackground(Color.ORANGE);
        label.setOpaque(true);
        bar=new JProgressBar(0,100);
        bar.setBounds(0,200,700,50);
        bar.setStringPainted(true);
        bar.setValue(0);
        bar.setForeground(Color.green);
        bar.setBackground(Color.white);
        label.add(bar);
        this.add(label);
        JTextField t=new JTextField();
        int option=JOptionPane.showConfirmDialog(null, t, "Enter your name: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(option==JOptionPane.OK_OPTION){
            if(t.getText().isEmpty())Scissors_Paper_Rock.name="Player1";
            else Scissors_Paper_Rock.name=t.getText();
            this.setVisible(true);
            fill();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
            new Rules();
        }
    }
    public void fill(){
        int counter=0;
        while (counter<=100){
            counter+=5;
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
            bar.setValue(counter);

        }
    }
}
