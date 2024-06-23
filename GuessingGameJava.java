import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

//Creating class and implementing intefaces
public class GuessingGameJava implements ActionListener, KeyListener {
 
    
       //Creating objects of JFrame and JPanel
       private JFrame frame;
       private JPanel panel1, panel2;
 
        //Creating Constructor
       GuessingGameJava(){
 
       //Setting properties of JFrame
       frame =new JFrame();
        frame.setTitle("Number Guessing Game");
        frame.getContentPane().setLayout(null);
        
        //Setting Properties of JPanel one and two
        panel1 =new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(128,0,128));
        panel1.setBounds(0,0,350,500);
 
        panel2=new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(new Color(255,150,134));
        panel2.setBounds(351,370,350,100);
        
        //Adding panels to JFrame
        frame.add(panel1);
        frame.add(panel2);
 
        //Setting properties of JFrame
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(700,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } 
 
    @Override
    public void keyPressed(KeyEvent e) {
    
    
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    
    
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    
    }
    
    //Creating main method
    public static void main(String[] args) {
    
    
    }
    
   }