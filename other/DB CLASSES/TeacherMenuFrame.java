package tgs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



class TeacherMenuFrame extends JFrame implements ActionListener {
    
    JLabel uid;

    JButton classSummary;
    JButton learnerSummary;
    JButton learnerSingleResult;
    JButton generatePdf;
    JButton btnSaveResults;
    JButton btnAddUser;

    JTextField txtLearnerID;
    JTextField txtGameID;
    JTextField txtResult;

    JComboBox learnerSelect;
    JComboBox gameSelect;
    JComboBox classSelect;
    
    CurrentUser cu;

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    JTable table = new JTable();
    JScrollPane scrollPane;

    ViewPanel vp = new ViewPanel();

    ButtonListener buttonListener = new ButtonListener();
    ComboBoxActionListener cbal = new ComboBoxActionListener();

    public TeacherMenuFrame(int userID) {
        this.setTitle("Login");
        this.setDefaultCloseOperation(TeacherMenuFrame.EXIT_ON_CLOSE);
        Container contentPane = this.getContentPane();

        this.setSize(500, 250);
        
        cu = new CurrentUser();
        cu.setUserID(userID);
        
        uid = new JLabel();

        uid.setText(cu.toString());
        
        classSelect = new JComboBox();
        classSelect = loadComboBox("select LearnerGrade from Learner group by LearnerGrade");

        learnerSelect = new JComboBox();
        learnerSelect.setEditable(true);
        learnerSelect = loadComboBox("select LearnerName from Learner where LearnerGrade = '"
                + classSelect.getSelectedItem().toString() + "'");

        gameSelect = new JComboBox();
        gameSelect = loadComboBox("select GameName from GameSelection");

        learnerSummary = new JButton("Learner Summary");
        learnerSingleResult = new JButton(" Learner Game Results");
        generatePdf = new JButton("Export to PDF");
        classSummary = new JButton("Class Summary");
        btnSaveResults = new JButton("Save");
        btnAddUser = new JButton("Add User");

        txtLearnerID = new JTextField(15);
        txtGameID = new JTextField(15);
        txtResult = new JTextField(15);

        JPanel panel = new JPanel();
        panel.add(uid);
        panel.add(classSelect);
        panel.add(learnerSelect);
        panel.add(gameSelect);
        panel.add(classSummary);
        panel.add(learnerSummary);
        panel.add(learnerSingleResult);
        panel.add(generatePdf);
        panel.add(txtLearnerID);
        panel.add(txtGameID);
        panel.add(txtResult);
        panel.add(btnSaveResults);
        panel.add(btnAddUser);
        panel.add(vp);
        learnerSummary.addActionListener(buttonListener);
        learnerSingleResult.addActionListener(buttonListener);
        generatePdf.addActionListener(buttonListener);
        btnSaveResults.addActionListener(buttonListener);
        classSelect.addActionListener(cbal);
        btnAddUser.addActionListener(buttonListener);
        classSummary.addActionListener(buttonListener);

        contentPane.add(panel);

    }

    //this method inserts the names and game names into the comboboxes
    public final JComboBox loadComboBox(String query) {
        JComboBox jcb = new JComboBox();
        try {
            //creating db connection
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            //adding each item to the combobox 
            while (rs.next()) {
                jcb.addItem(rs.getString(1));
            }
            rs.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(TeacherMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jcb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class ButtonListener implements ActionListener {

        private Connection con = null;
        private Statement s = null;
        private ResultSet rs = null;
        String query = "";
        String learnerName;
        String gameName;

        @Override
        public void actionPerformed(ActionEvent evt) {

            try {
                //create db connection
                con = DBConnection.getConnection();
                s = con.createStatement();
                //specify learner and game name as selected in the combobox
                learnerName = learnerSelect.getSelectedItem().toString();
                gameName = gameSelect.getSelectedItem().toString();

                //specify the query to be executed depending on which action command is issued
                if (evt.getActionCommand().contains("Export to PDF")) {
                    //query to generate PDF
                    query = "select * from ("
                            + "select Learner.LearnerName,GameSelection.GameName,Result.Result,Result.ResultDate\n"
                            + "from Learner\n"
                            + "join Result on Learner.LearnerID = Result.LearnerID\n"
                            + "join GameSelection on Result.GameID = GameSelection.GameID\n"
                            + "where Learner.LearnerName = '" + learnerName + "'\n"
                            + "and GameSelection.GameName = '" + gameName + "') sub\n"
                            + "order by ResultDate desc limit 10";
                    rs = s.executeQuery(query);
                    //call to the create PDF method which takes a ResultSet and two Strings as parameters
                    CreatePDF.createPDFSingleDetailed(rs, learnerName, gameName);
                    table.setModel(createTable(rs));
                    rs.close();

                    //fills the table to show on screen
                    rs = s.executeQuery(query);
                    table.setModel(createTable(rs));
                } else if (evt.getActionCommand().contains(" Learner Game Results")) {
                    //query to view results for a single game
                    query = "select Result,ResultDate from ("
                            + "select Result.ResultID,Result.Result as Result,Result.ResultDate as ResultDate\n"
                            + "from Learner\n"
                            + "join Result on Learner.LearnerID = Result.LearnerID\n"
                            + "join GameSelection on Result.GameID = GameSelection.GameID\n"
                            + "where Learner.LearnerName = '" + learnerName + "'\n"
                            + "and GameSelection.GameName = '" + gameName + "') sub\n"
                            + "order by ResultID desc limit 10";

                    //fills the table to show on screen
                    rs = s.executeQuery(query);
                    table.setModel(createTable(rs));
                } else if (evt.getActionCommand().contains("Learner Summary")) {
                    //query to view a summary for a learner for all games
                    query = "select GameSelection.GameName, ROUND(AVG(Result)) as Average\n"
                            + "from Learner \n"
                            + "join Result on Learner.LearnerID = Result.LearnerID\n"
                            + "join GameSelection on Result.GameID = GameSelection.GameID\n"
                            + "where Learner.LearnerName = '" + learnerName
                            + "' group by GameSelection.GameName";
                    rs = s.executeQuery(query);
                    //call to the create PDF summary method which takes a ResultSet and one String as parameters
                    CreatePDF.createPDFSingleSummary(rs, learnerName);
                    //table.setModel(createTable(rs));
                    rs.close();

                    //fills the table to show on screen
                    rs = s.executeQuery(query);
                    table.setModel(createTable(rs));
                } else if (evt.getActionCommand().contains("Class Summary")) {
                    query = "select Learner.LearnerName,GameSelection.GameName, ROUND(AVG(Result.Result)) as Average\n"
                            + "from Learner \n"
                            + "join Result on Learner.LearnerID = Result.LearnerID\n"
                            + "join GameSelection on Result.GameID = GameSelection.GameID\n"
                            + "where Learner.LearnerGrade = '" + classSelect.getSelectedItem().toString()
                            + "' group by Learner.LearnerName,GameSelection.GameName";
                    rs = s.executeQuery(query);
                    //call to the create PDF summary method which takes a ResultSet and one String as parameters
                    CreatePDF.createPDFClassSummary(rs, classSelect.getSelectedItem().toString());
                    //table.setModel(createTable(rs));
                    rs.close();
                } else if (evt.getActionCommand().contains("Save")) {
                    insertResult();
                } else if (evt.getActionCommand().contains("Add User")) {
                    AddUser frame = new AddUser();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }

                s.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class ComboBoxActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String grade = (String) cb.getSelectedItem();
            cb = loadComboBox("select LearnerName from Learner where LearnerGrade = '" + grade + "'");
            DefaultComboBoxModel model = (DefaultComboBoxModel) cb.getModel();
            learnerSelect.setModel(model);
            //JOptionPane.showMessageDialog(null, "select LearnerName from Learner where LearnerGrade = '" + grade + "'");
        }
    }

//table model which is filled by a ResultSet to be shown on screen
    public DefaultTableModel createTable(ResultSet rs) {
        DefaultTableModel tableModel = new DefaultTableModel();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            // column names are added here
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnLabel(i));
            }

            Object[] row = new Object[columnCount];

            //actual table data gets added here
            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tableModel;
    }

    //panel in which the table is loaded
    class ViewPanel extends JPanel {

        public ViewPanel() {
            scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(650, 400));
            add(scrollPane);
        }
    }

    public void insertResult() {
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
            boolean result = stmt.execute("INSERT INTO Result (LearnerID,GameID,Result,ResultDate) VALUES ("
                    + cu.getUserID() + "," + cu.getGameID() + ",'"
                    + Integer.parseInt(txtResult.getText()) + "','" + timeStamp + "')");
            stmt.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(TeacherMenuFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
