package tgs;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class LoginFrame extends JFrame implements ActionListener {

    private JButton btnSubmit;
    private JLabel lblUserName;
    private JLabel lblPassword;
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    
    Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public LoginFrame() {
        this.setTitle("Login");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        Container contentPane = this.getContentPane();

        this.setSize(300, 150);
        GridLayout layout = new GridLayout(3, 2, 5, 10);

        btnSubmit = new JButton("Log In");
        lblUserName = new JLabel("User Name:");
        lblPassword = new JLabel("Password:");
        txtUserName = new JTextField(10);
        txtPassword = new JPasswordField(10);

        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(lblUserName);
        panel.add(txtUserName);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnSubmit);
        btnSubmit.addActionListener(this);

        contentPane.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT LearnerID,LearnerName,LearnerPassword FROM Learner "
                    + "WHERE LearnerName = '" + txtUserName.getText() + "'");

            int i = 0;
            String p = "";
            String n = "";

            while (rs.next()) {
                i = rs.getInt(1);
                n = rs.getString(2);
                p = rs.getString(3);
            }

            if (n != "") {
                if (txtPassword.getText() == null ? p == null : txtPassword.getText().equals(p)) {

                    TeacherMenuFrame bframe = new TeacherMenuFrame(i);                    
                    bframe.setLocationRelativeTo(null);
                    bframe.setVisible(true);
                    this.dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password. Please try again.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid user name and password.");
            }

            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}