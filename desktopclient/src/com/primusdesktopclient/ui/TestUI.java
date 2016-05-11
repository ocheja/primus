/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.ui;

import com.primusdesktopclient.gsondata.Lecturer;
import com.primusdesktopclient.service.RunnableService;
import com.primusdesktopclient.service.Test;
import com.primusdesktopclient.service.UIService;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Olisa
 */
public class TestUI extends javax.swing.JFrame {

    private RunnableService runnable;
    private Thread thread;

    /**
     * Creates new form TestUI
     */
    public TestUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enrollButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();
        startButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        enrollButton.setText("Enroll");
        enrollButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                enrollButtonMousePressed(evt);
            }
        });

        logArea.setColumns(20);
        logArea.setRows(5);
        jScrollPane1.setViewportView(logArea);

        startButton.setText("Start Notification");
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                startButtonMousePressed(evt);
            }
        });
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enrollButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startButton)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enrollButton)
                    .addComponent(startButton))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enrollButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enrollButtonMousePressed
        System.out.println("Stopping thread: " + thread);
        if (thread != null) {
            runnable.terminate();
            try {
                thread.join();
               System.out.println("Thread successfully stopped.");
                Thread.sleep(1000); // wait for io port to settle
            } catch (InterruptedException ex) {
                Logger.getLogger(TestUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Lecturer lecturer = new Lecturer();
        UIService service = new UIService();
        try {
            lecturer = service.getLecturer("mike");
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean status = service.registerLecturer("FIFTH_YEAR_CLASS", Long.valueOf(lecturer.getLecturerID()));
        TestUI.logArea.append("\nlecturer status is  " + status + "\n");
        System.out.println("lecturer status is  " + status);


        // TODO add your handling code here:
    }//GEN-LAST:event_enrollButtonMousePressed

    private void startButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMousePressed
        runnable = new RunnableService();
        thread = new Thread(runnable);
        System.out.println("Starting thread: " + thread);
        thread.start();
        System.out.println("Background process successfully started.");


        // TODO add your handling code here:
    }//GEN-LAST:event_startButtonMousePressed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        URL url = ClassLoader.getSystemResource("com\\primusdesktopclient\\service\\log4j.properties");
        PropertyConfigurator.configure(url);
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
            java.util.logging.Logger.getLogger(TestUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enrollButton;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea logArea;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}