/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.ari.dao;

import id.buma.ari.database.DB;
import id.buma.ari.model.M_CsResult;
import id.buma.ari.model.M_ari;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class AriDAOSQL  implements AriDAO{

    @Override
    public List<M_ari> bacaDbCsByPeriode(Date tglPeriode) {
        List<M_ari> lsAri = new ArrayList<>();
        try {            
            Connection conn = new DB().getConnCs();
            String sql = "select cs.numerator, cs.brix, cs.pol, cs.knpp from tbl_corelab cs where periode = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setDate(1, tglPeriode);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    if (rs.getDouble("brix") > 0.00 && rs.getDouble("pol") > 0.00){
                        M_ari ari = new M_ari(
                                rs.getString("numerator"),
                                rs.getDouble("brix"),
                                rs.getDouble("pol"),
                                rs.getDouble("knpp")
                        );
                        lsAri.add(ari);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ada data kosong! No SPAT = " + rs.getString("numerator"));
                        break;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(AriDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error bacaDbCsByPeriode! \nError code = " + ex.toString());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AriDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsAri;
    }

    @Override
    public M_CsResult updateSimpgAri(List<M_ari> lsAri) {
        M_CsResult csResult = null;
        Connection conn = new DB().getConnSimpg();
        String sql = "insert into t_ari(id_spta, persen_brix_ari, persen_pol_ari, hk, nilai_nira, faktor_rendemen, "
                    + "rendemen_ari, tgl_ari, ptgs_ari) values " 
                    + "((select spta.id from t_spta spta where spta.no_spat = ?),?,?,?,?,?,?,now(),?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            for (M_ari ari : lsAri){
                ps.setString(1, ari.getNo_spta());
                ps.setDouble(2, ari.getPersen_brix_ari());
                ps.setDouble(3, ari.getPersen_pol_ari());
                ps.setDouble(4, ari.getHk());
                ps.setDouble(5, ari.getNilai_nira());
                ps.setDouble(6, ari.getFaktor_rendemen());
                ps.setDouble(7, ari.getRendAri());
                ps.setString(8, "Auto Sync TR Buma");
                ari.setSyncStat("Y");
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            csResult = new M_CsResult(lsAri, true);
        } catch (SQLException ex) {
            Logger.getLogger(AriDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error updateSimpgAri! \nError code = " + ex.toString());
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AriDAOSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return csResult;
    }

    @Override
    public boolean updateDbCsSyncStat(List<M_ari> lsAri) {
        try {
            Connection conn = new DB().getConnCs();
            String sql = "update tbl_corelab set sync_simpg = ? where numerator = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)){
                conn.setAutoCommit(false);
                conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                for (M_ari ari : lsAri){
                    ps.setString(1, "Y");
                    ps.setString(2, ari.getNo_spta());
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.commit();
                conn.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(AriDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error updateDbCsSyncStat! \nError code = " + ex.toString());
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(AriDAOSQL.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AriDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
