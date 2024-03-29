/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author 7M1SRVGT3
 */
public class GlobalSettingsFrame extends javax.swing.JFrame {

    private String DBLocation;
    private String DBUserName;
    private String DBPassword;
    
    private int loginCount;
    
    public GlobalSettingsFrame(String l, String u, String p) {
        initComponents();   //INITIALISES FRAME
        //SETS THE CURRENT LOCATION, USERNAME AND PASSWORD
        DBLocation = l;
        DBUserName = u;
        DBPassword = p;
        
        loginCount = 0;
        
        txt_dbLocation.setText(l);
        txt_userName.setText(u);
        txt_password.setText(p);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_dbLocation = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        CheckBoxDemo = new javax.swing.JCheckBox();
        CheckBoxFullScreen = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        btn_accept = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_userName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Settings");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("Database Location:");

        jLabel3.setText("Database Password:");

        txt_dbLocation.setName("txt_dbLocation"); // NOI18N

        jLabel4.setText("Demo Mode:");

        jLabel5.setText("Full Screen:");

        btn_accept.setText("Accept");
        btn_accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_acceptActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        jLabel6.setText("Database User Name:");

        txt_userName.setName(""); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(CheckBoxDemo)
                                .addComponent(CheckBoxFullScreen)
                                .addComponent(txt_dbLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .addComponent(txt_userName)
                                .addComponent(txt_password))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(btn_accept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cancel)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_dbLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CheckBoxDemo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckBoxFullScreen))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_accept)
                    .addComponent(btn_cancel))
                .addContainerGap())
        );

        jLabel2.getAccessibleContext().setAccessibleName("lbl_dbLocation");
        jLabel3.getAccessibleContext().setAccessibleName("lbl_dbPassword");
        txt_dbLocation.getAccessibleContext().setAccessibleName("txt_dbUserName");
        txt_password.getAccessibleContext().setAccessibleName("txt_dbPassword");
        jLabel4.getAccessibleContext().setAccessibleName("lbl_demo");
        CheckBoxDemo.getAccessibleContext().setAccessibleName("tickBox_demo");
        CheckBoxFullScreen.getAccessibleContext().setAccessibleName("tickBox_fullScreen");
        jLabel5.getAccessibleContext().setAccessibleName("lbl_fullScreen");
        btn_accept.getAccessibleContext().setAccessibleName("btn_accept");
        btn_cancel.getAccessibleContext().setAccessibleName("btn_cancel");
        jLabel6.getAccessibleContext().setAccessibleName("lbl_dbUserName");
        txt_userName.getAccessibleContext().setAccessibleName("txt_dbUserName");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_acceptActionPerformed
        //CALLS METHOD TO DISPLAY OPTIONPANE FOR ADMIN PASSWORD VERIFICATION
        openOptionPane();
    }//GEN-LAST:event_btn_acceptActionPerformed

    private void openOptionPane(){
        JTextField password = new JPasswordField();
        Object[] message = {
            "Admin Password:", password
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Confirm Changes", JOptionPane.OK_CANCEL_OPTION);
        //CHECKS WHETHER ENTERED PASSWORD IS CORRECT
        if (option == JOptionPane.OK_OPTION) {
            
            if (password.getText().equals("h")) {
                //IF PASSWORD IS CORRECT, DETAILS ARE UPDATED
                System.out.println("Login successful");
                loginCount = 0;
                JOptionPane.showMessageDialog(null, "Settings successfully updated.","Settings Updated!", JOptionPane.OK_CANCEL_OPTION);
                updateDetails();
            } else {
                //IF PASSWORD IS INCORRECT, AN ERROR MESSAGE IS DISPLAYED AND USER IS PROMPTED 
                //TO ENTER PASSWORD AGAIN
                System.out.println("Login failed!");
                loginCount++;
                //IF MORE THAN 3 LOGIN ATTEMPTS FAILED, CLOSES ADMIN PASSWORD REQUEST
                if (loginCount == 3) {
                    JOptionPane.showMessageDialog(null, "Maximum log in attemps exceeded. Please try again later.","Incorrect Password!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Please insert the correct password.","Incorrect Password!", JOptionPane.ERROR_MESSAGE);
                openOptionPane();
            }
        } else {
            System.out.println("Login canceled");
        }
    }
    
    private void updateDetails(){
        //CODE TO UPDATE THE LOCATION, USERNAME, PASSWORD, DEMO AND FULL SCREEN MODE GOES HERE
    }
    
    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        this.setDefaultCloseOperation(GlobalSettingsFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GlobalSettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GlobalSettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GlobalSettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GlobalSettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GlobalSettingsFrame("","","").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckBoxDemo;
    private javax.swing.JCheckBox CheckBoxFullScreen;
    private javax.swing.JButton btn_accept;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txt_dbLocation;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_userName;
    // End of variables declaration//GEN-END:variables
}
