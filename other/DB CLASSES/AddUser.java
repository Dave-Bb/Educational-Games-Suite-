package tgs;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public final class AddUser extends JFrame {

    JRadioButton selectLearner;
    JRadioButton selectTeacher;
    ButtonGroup group;

    JButton btnAdd;
    JLabel lblName;
    JLabel lblSurname;
    JLabel lblGrade;
    JLabel lblPassword;
    JLabel lblTeacherID;
    JTextField txtName;
    JTextField txtSurname;
    JTextField txtGrade;
    JTextField txtPassword;
    JTextField txtTeacherID;

    JComboBox comboTeacher;

    JPanel panel = new JPanel();
    JPanel radioPanel = new JPanel();

    RadioButtonActionListener rbal = new RadioButtonActionListener();
    ButtonActionListener bal = new ButtonActionListener();
    Container contentPane = this.getContentPane();

    Connection con;
    Statement stmt;
    ResultSet resultSet;

    public AddUser() {

        this.setSize(500, 300);

        selectLearner = new JRadioButton("Learner", true);
        selectLearner.setActionCommand("Learner");
        selectTeacher = new JRadioButton("Teacher");
        selectTeacher.setActionCommand("Teacher");
        group = new ButtonGroup();
        group.add(selectLearner);
        group.add(selectTeacher);
        selectLearner.addActionListener(rbal);
        selectTeacher.addActionListener(rbal);

        radioPanel.setLayout(new GridLayout(1, 2, 10, 30));
        radioPanel.add(selectLearner);
        radioPanel.add(selectTeacher);

        btnAdd = new JButton("Add User");
        btnAdd.addActionListener(bal);

        lblName = new JLabel("Name: ");
        lblSurname = new JLabel("Surname: ");
        lblGrade = new JLabel("Grade: ");
        lblPassword = new JLabel("Password: ");
        lblTeacherID = new JLabel("Teacher: ");
        txtName = new JTextField(10);
        txtSurname = new JTextField(10);
        txtGrade = new JTextField(10);
        txtPassword = new JTextField(10);
        txtTeacherID = new JTextField(10);
        comboTeacher = new JComboBox();
        comboTeacher = fillComboTeacher();

        panel = setPanelLayout("Learner");

        contentPane.add(radioPanel, BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.CENTER);
    }

    public JPanel setPanelLayout(String value) {
        GridLayout layout;
        JPanel p = new JPanel();
        switch (value) {
            case "Learner":
                layout = new GridLayout(6, 2, 5, 10);
                p.setLayout(layout);
                p.add(lblName);
                p.add(txtName);
                p.add(lblSurname);
                p.add(txtSurname);
                p.add(lblGrade);
                p.add(txtGrade);
                p.add(lblPassword);
                p.add(txtPassword);
                p.add(lblTeacherID);
                p.add(comboTeacher);
                btnAdd.setText("Add Learner");
                p.add(btnAdd);
                break;
            case "Teacher":
                layout = new GridLayout(5, 2, 5, 10);
                p.setLayout(layout);
                p.add(lblName);
                p.add(txtName);
                p.add(lblSurname);
                p.add(txtSurname);
                p.add(lblGrade);
                p.add(txtGrade);
                p.add(lblPassword);
                p.add(txtPassword);
                btnAdd.setText("Add Teacher");
                p.add(btnAdd);
                break;
        }
        return p;
    }

    public JComboBox fillComboTeacher() {
        JComboBox jcb = new JComboBox();
        try {

            con = DBConnection.getConnection();
            stmt = con.createStatement();
            resultSet = stmt.executeQuery("select TeacherName, TeacherSurname from Teacher");

            //adding each item to the combobox
            while (resultSet.next()) {
                jcb.addItem(resultSet.getString(2) + ", " + resultSet.getString(1));
            }
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jcb;
    }

    public String getTeacherID(String name, String surname) {
        stmt = null;
        String id = "hmmm";
        ArrayList<String> teacherID = new ArrayList();
        ArrayList<String> teacherName = new ArrayList();
        ArrayList<String> teacherSurname = new ArrayList();
        try {
            //creating db connectionint id = 0;
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            resultSet = stmt.executeQuery("select * from Teacher");

            //adding each item to the combobox 
            while (resultSet.next()) {
                teacherID.add(resultSet.getString(1));
                teacherName.add(resultSet.getString(2));
                teacherSurname.add(resultSet.getString(3));
            }
            for (int i = 0; i < teacherID.size(); i++) {
                if (teacherName.get(i) == null ? name == null : teacherName.get(i).equals(name)) {
                    if (teacherSurname.get(i) == null ? surname == null : teacherSurname.get(i).equals(surname)) {
                        return teacherID.get(i);
                    }
                }
            }
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    class ButtonActionListener implements ActionListener {

        private Connection con = null;
        private Statement s = null;
        private String teacherName[];

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //create db connection
                con = DBConnection.getConnection();
                s = con.createStatement();

                //JOptionPane.showMessageDialog(null, teacherName[1] + "," + teacherName[0] + "," + id);
                if (e.getActionCommand().contains("Add Learner")) {
                    teacherName = comboTeacher.getSelectedItem().toString().split(",");
                    String tid = getTeacherID(teacherName[1].trim(), teacherName[0]);
                    boolean result = s.execute("INSERT INTO Learner (LearnerName,LearnerSurname,LearnerGrade,LearnerPassword,TeacherID) "
                            + "VALUES ('" + txtName.getText() + "','"
                            + txtSurname.getText() + "','"
                            + txtGrade.getText() + "','"
                            + txtPassword.getText() + "','"
                            + tid + "')");
                    JOptionPane.showMessageDialog(null, "Learner Added!");
                    AddUser.this.dispose();
                } else if (e.getActionCommand().contains("Add Teacher")) {
                    boolean result = s.execute("INSERT INTO Teacher (TeacherName,TeacherSurname,TeacherPassword,ClassID) "
                            + "VALUES ('" + txtName.getText() + "','"
                            + txtSurname.getText() + "','"
                            + txtPassword.getText() + "','"
                            + txtGrade.getText() + "')");
                    JOptionPane.showMessageDialog(null, "Teacher Added!");
                    AddUser.this.dispose();
                }

                s.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class RadioButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            contentPane.remove(panel);
            panel.removeAll();
            panel = setPanelLayout((String) e.getActionCommand());
            contentPane.add(panel, BorderLayout.CENTER);
            contentPane.revalidate();
        }
    }

}
