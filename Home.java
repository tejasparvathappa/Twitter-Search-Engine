import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   Home window = new Home();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Home() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(88, 24, 69));
        frame.setBounds(100, 100, 799, 557);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Find users and their tweets!");
        btnNewButton.setForeground(new Color(255,255,255));
        btnNewButton.setBackground(new Color(88, 24, 69));
        btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 26));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new First().setVisible(true);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                dispose();
            }
        });
        btnNewButton.setBounds(530, 368, 382, 66);
        frame.getContentPane().add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Welcome to Twitter Search Engine!");
        lblNewLabel.setForeground(new Color(255,255,255));
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblNewLabel.setBounds(500, 54, 531, 50);
        frame.getContentPane().add(lblNewLabel);
        
       
        JLabel lblNewLabel_1 = new JLabel("Let's discover some tweets and their users!");
        lblNewLabel_1.setForeground(new Color(255,255,255));
        lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
        lblNewLabel_1.setBounds(500, 292, 500, 43);
        frame.getContentPane().add(lblNewLabel_1);


        JEditorPane dtrpnHereWePredict = new JEditorPane();
        dtrpnHereWePredict.setEditable(false);
        dtrpnHereWePredict.setForeground(new Color(255,255,255));
        dtrpnHereWePredict.setBackground(new Color(88, 24, 69));
        dtrpnHereWePredict.setFont(new Font("SansSerif", Font.ITALIC, 18));
        dtrpnHereWePredict.setText("Here, we compute a score for each user based on your input.");
        dtrpnHereWePredict.setBounds(500, 116, 633, 42);
        frame.getContentPane().add(dtrpnHereWePredict);
        
        
    }


}