/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.ari.controller;

import id.buma.ari.dao.AriDAOSQL;
import id.buma.ari.model.M_CsResult;
import id.buma.ari.model.M_ari;
import id.buma.ari.view.MainWindow;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class AriController {
    
    private MainWindow mw;
    
    private static List<M_ari> lsAri;
    
    public AriController(MainWindow mw){
        this.mw = mw;
    }
    
    public boolean getDataAri(){
        lsAri = new ArrayList<>();
        java.sql.Date periode = new java.sql.Date(mw.getDtpPeriode().getDate().getTime());
        AriDAOSQL ariDao = new AriDAOSQL();
        lsAri = ariDao.bacaDbCsByPeriode(periode);
        mw.getLblJmlData().setText(String.valueOf(lsAri.size()));
        return lsAri.size() > 0;
    }
    
    public boolean syncSimpg(){
        if (lsAri.size() > 0){
            AriDAOSQL ariDao = new AriDAOSQL();
            M_CsResult syncRes = ariDao.updateSimpgAri(lsAri);
            if (syncRes.isSyncStat()){
                ariDao.updateDbCsSyncStat(syncRes.getLsAri());
            }
            return syncRes.isSyncStat();
        } else {
            JOptionPane.showMessageDialog(null, "Array size = 0");
        }
        return false;
    }
}
