/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.ari.dao;

import id.buma.ari.model.M_CsResult;
import id.buma.ari.model.M_ari;
import java.util.List;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public interface AriDAO {
    
    public List<M_ari> bacaDbCsByPeriode(java.sql.Date tglPeriode);
    
    public M_CsResult updateSimpgAri(List<M_ari> lsAri);
    
    public boolean updateDbCsSyncStat(List<M_ari> lsAri);
    
}
