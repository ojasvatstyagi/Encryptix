import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGradeCalc implements ActionListener, KeyListener{

    //Crating Object of UIManager class to manage the look and feel
    UIManager UI = new UIManager();

    // Creating objects of JFrame and JPanel
    private JFrame frame;
    private JPanel panel1, panel2;

    // Creating objects of components
    private JLabel textLabel1, textLabel2, textLabel3, textLabel4, textLabel5, imageLabel, totalLabel, gradeLabel;
    private JButton calButton;
    private JTextField numberOfSubjects, marksOfSubjects, totalField, gradeField;

    @SuppressWarnings("static-access")
    StudentGradeCalc(){

        //Managing look and feel of JOptionPane
        UI.put("OptionPane.background",new Color(199, 249, 204));
        UI.put("Panel.background", new Color(199, 249, 204));

        // Setting properties of JFrame
        frame = new JFrame();
        frame.setTitle("Student Grade Calculator");
        frame.getContentPane().setLayout(null);

        // Setting Properties of JPanel one and two
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(56, 163, 165));
        panel1.setBounds(0, 0, 330, 500);

        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(new Color(128, 237, 153));
        panel2.setBounds(331, 370, 350, 100);

        // Adding panels to JFrame
        frame.add(panel1);
        frame.add(panel2);

        textLabel1 = new JLabel();
        textLabel1.setText("Student");
        textLabel1.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        textLabel1.setForeground(Color.WHITE);
        textLabel1.setBounds(55, 20, 300, 100);

        textLabel2 = new JLabel();
        textLabel2.setText("Grade Calculator");
        textLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
        textLabel2.setForeground(Color.WHITE);
        textLabel2.setBounds(35, 80, 300, 100);

        textLabel3 = new JLabel();
        textLabel3.setText("Number of Subjects");
        textLabel3.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        textLabel3.setForeground(new Color(34, 87, 122));
        textLabel3.setBounds(350, 20, 400, 100);

        textLabel4 = new JLabel();
        textLabel4.setText("Enter the marks");
        textLabel4.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        textLabel4.setForeground(new Color(34, 87, 122));
        textLabel4.setBounds(390, 100, 300, 100);

        textLabel5 = new JLabel();
        textLabel5.setText("Enter the marks separated by spaces");
        textLabel5.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        textLabel5.setForeground(new Color(34, 87, 122));
        textLabel5.setBounds(370, 150, 300, 100);

        numberOfSubjects = new JTextField();
        numberOfSubjects.setDocument(new JTextFieldCharLimit(2));
        numberOfSubjects.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfSubjects.setBounds(440, 100, 145, 30);
        numberOfSubjects.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        numberOfSubjects.setBackground(new Color(232, 232, 232));
        numberOfSubjects.setBorder(BorderFactory.createBevelBorder(1));

        marksOfSubjects = new JTextField();
        marksOfSubjects.setHorizontalAlignment(SwingConstants.CENTER);
        marksOfSubjects.setBounds(420, 220, 200, 30);
        marksOfSubjects.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        marksOfSubjects.setBackground(new Color(232, 232, 232));
        marksOfSubjects.setBorder(BorderFactory.createBevelBorder(1));

        calButton = new JButton();
        calButton.setText("Calculate");
        calButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        calButton.setBounds(450, 270, 125, 35);
        calButton.setFocusable(false);
        calButton.setBackground(new Color(56, 163, 165));
        calButton.setForeground(Color.white);

        totalLabel = new JLabel();
        totalLabel.setText("Total marks: ");
        totalLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        totalLabel.setBounds(10, 5, 150, 35);
        totalLabel.setForeground(Color.black);

        gradeLabel = new JLabel();
        gradeLabel.setText("Grade: ");
        gradeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        gradeLabel.setBounds(10, 40, 150, 35);
        gradeLabel.setForeground(Color.black);

        totalField = new JTextField();
        totalField.setEditable(false);
        totalField.setBounds(200, 14, 100, 25);
        totalField.setHorizontalAlignment(SwingConstants.CENTER);
        totalField.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        totalField.setBorder(BorderFactory.createBevelBorder(1));

        gradeField = new JTextField();
        gradeField.setEditable(false);
        gradeField.setText("");
        gradeField.setBounds(200, 50, 100, 25);
        gradeField.setHorizontalAlignment(SwingConstants.CENTER);
        gradeField.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        gradeField.setBorder(BorderFactory.createBevelBorder(1));

        ImageIcon imageIcon = new ImageIcon("calculator.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon icon = new ImageIcon(newimg);  // transform it back
        imageLabel = new JLabel(icon);
        imageLabel.setBounds(70, 200, icon.getIconWidth(), icon.getIconHeight());

        // Adding Action Listener to JButtons
        calButton.addActionListener(this);
        // Adding Key Listener to JTextField
        numberOfSubjects.addKeyListener(this);

        // Adding components to the container
        panel1.add(textLabel1);
        panel1.add(textLabel2);
        panel1.add(imageLabel);
        panel2.add(totalLabel);
        panel2.add(gradeLabel);
        panel2.add(totalField);
        panel2.add(gradeField);
        frame.add(textLabel3);
        frame.add(textLabel4);
        frame.add(textLabel5);
        frame.add(numberOfSubjects);
        frame.add(marksOfSubjects);
        frame.add(calButton);

        // Setting properties of JFrame
        frame.getContentPane().setBackground(new Color(87, 204, 153));
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        // Creating object of the class
        @SuppressWarnings("unused")
        StudentGradeCalc studentGradeCalc = new StudentGradeCalc();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calButton) {
            try {
                int numberOfSubj = Integer.parseInt(numberOfSubjects.getText());
                String[] marksStr = marksOfSubjects.getText().split("\\s+");
                if (marksStr.length != numberOfSubj) {
                    JOptionPane.showMessageDialog(frame, "Please enter the correct number of marks.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int totalMarks = 0;
                for (String markStr : marksStr) {
                    totalMarks += Integer.parseInt(markStr);
                }
                
                double averagePercentage = (double) totalMarks / numberOfSubj;
                char grade;
                if (averagePercentage >= 90) {
                    grade = 'A';
                } else if (averagePercentage >= 80) {
                    grade = 'B';
                } else if (averagePercentage >= 70) {
                    grade = 'C';
                } else if (averagePercentage >= 60) {
                    grade = 'D';
                } else {
                    grade = 'F';
                }
                
                totalField.setText(String.valueOf(totalMarks));
                gradeField.setText(String.valueOf(grade));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}

// Restricting JTextField Character limits using PlainDocument class
class JTextFieldCharLimit extends PlainDocument {
    int limit;
    public JTextFieldCharLimit(int limitation){
        this.limit = limitation;
    }
    public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
        if (str == null) {
            return;
        } else if (getLength() + str.length() <= limit) {
            super.insertString(offset, str, set);
        } else if (getLength() + str.length() > limit) {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
