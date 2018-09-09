/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.ari.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class ButtonAction implements ActionListener {
    
    private final AriController ac;

    public ButtonAction(AriController ac){
        this.ac = ac;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String btnName = btn.getName();
        switch (btnName){
            case "btnGetAri" :
                ac.getDataAri();
                break;
            case "btnSyncSimpg" :
                if (ac.syncSimpg()){
                    JOptionPane.showMessageDialog(null, "Sync berhasil!");
                } else {
                    JOptionPane.showMessageDialog(null, "Sync gagal!");
                }
                break;
        }
    }
    
}
