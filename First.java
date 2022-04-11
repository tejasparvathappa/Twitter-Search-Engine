import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class First extends JFrame implements ActionListener{

    private JPanel contentPane;
    //private JTextField reqUser;
    //private JTextField desiredSkills;
    String LNG;
    String dte;
    //String year;
    String User;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    First frame = new First();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public First() {
        setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
        setTitle("Homepage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1391, 561);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(88, 24, 69));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Enter details here:");
        lblNewLabel_1.setBounds(470, 54, 775, 52);
        lblNewLabel_1.setForeground(new Color(255,255,255));
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 44));
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Language");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel_2.setForeground(new Color(255,255,255));
        lblNewLabel_2.setBounds(448, 151, 150, 41);
        contentPane.add(lblNewLabel_2);

        JLabel lblDiscipline = new JLabel("Date");
        lblDiscipline.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblDiscipline.setForeground(new Color(255,255,255));
        lblDiscipline.setBounds(448, 219, 150, 41);
        contentPane.add(lblDiscipline);

        JLabel lblRequiredSkills = new JLabel("User");
        lblRequiredSkills.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblRequiredSkills.setForeground(new Color(255,255,255));
        lblRequiredSkills.setBounds(448, 286, 175, 41);
        contentPane.add(lblRequiredSkills);


        JComboBox language = new JComboBox();
        language.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox language = (JComboBox) event.getSource();
                Object selected = language.getSelectedItem();
                LNG = selected.toString();
            }
        });
        language.setModel(new DefaultComboBoxModel(new String[] {"Please Select","English", "Spanish", "vietnamese", "portugese", "Italian", "French", "Undefined","Arabic","Indian"})); // UI for langauge drop down
        language.setBounds(879, 161, 150, 32);
        contentPane.add(language);


        JComboBox date = new JComboBox();
        date.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox date = (JComboBox) event.getSource();
                Object selected = date.getSelectedItem();
                dte = selected.toString();

            }
        });
        date.setModel(new DefaultComboBoxModel(new String[] {"Please Select", "1-Oct-12","2-Oct-12","3-Oct-12","4-Oct-12","5-Oct-12","6-Oct-12","7-Oct-12","8-Oct-12","9-Oct-12","10-Oct-12","11-Oct-12","12-Oct-12","13-Oct-12","14-Oct-12","15-Oct-12","16-Oct-12","17-Oct-12","18-Oct-12","19-Oct-12","20-Oct-12","21-Oct-12","22-Oct-12","23-Oct-12","24Oct-12","25-Oct-12","26-Oct-12","27-Oct-12","28-Oct-12","29-Oct-12","30-Oct-12","31-Oct-12"}));
        date.setBounds(879, 229, 150, 32);
        contentPane.add(date);


        JTextField reqUser = new JTextField("",10000);
        reqUser.setBackground(new Color(88, 24, 69));
        reqUser.setForeground(new Color(255,255,255));
        reqUser.setEditable(true);
        reqUser.setColumns(50);

        reqUser.setBounds(879, 291, 457, 41);
        contentPane.add(reqUser);

        JButton enterButton = new JButton("SUBMIT");
        enterButton.setForeground(new Color(255,255,255));
        enterButton.setBackground(new Color(88, 24, 69));
        enterButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User = reqUser.getText();
                String[] tokens = User.split(",");
                for(int i=0;i<tokens.length;i++)
                    User.charAt(i);
                new Result(LNG,dte,User).setVisible(true);
                dispose();
            }
        });
        enterButton.setBounds(671, 459, 143, 41);
        contentPane.add(enterButton);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}