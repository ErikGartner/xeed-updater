/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * xeed_update.java
 *
 * Created on 2010-dec-12, 20:02:39
 */
package xeed.updater;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Erik
 */
public class xeed_update extends javax.swing.JFrame {

    public xeed_update() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screensize = tk.getScreenSize();
        this.setLocation(screensize.width / 2 - this.getWidth() / 2, screensize.height / 2 - this.getHeight() / 2); //Bra start pos
    }

    public void setProgress(int procent, String msg) {
        jProgressBar1.setValue(procent);
        lblMsg.setText(msg);
        jProgressBar1.repaint();
        lblMsg.repaint();
    }

    public void setIndeterminate(boolean b) {
        jProgressBar1.setIndeterminate(b);
        jProgressBar1.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        lblMsg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        jProgressBar1.setForeground(new java.awt.Color(255, 153, 0));
        jProgressBar1.setMaximum(10000);
        jProgressBar1.setString("");

        lblMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMsg.setText("Loading...");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/xeed_updater/screen.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblMsg))
            .addComponent(jLabel1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(final String args[]) {

        if (args.length < 2) {
            JOptionPane.showMessageDialog(null, "Error: Arguments missing, please restart XEED manually.");
            return;
        }

        xeed_update x = new xeed_update();
        x.setVisible(true);

        engine en = new engine(args[1], x,args[0]);
        Thread tmpThread = new Thread(en);
        tmpThread.start();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lblMsg;
    // End of variables declaration//GEN-END:variables
}
