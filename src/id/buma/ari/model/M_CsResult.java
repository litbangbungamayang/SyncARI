/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.ari.model;

import java.util.List;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class M_CsResult {
    
    private List<M_ari> lsAri;
    private boolean syncStat;
    
    public M_CsResult(List<M_ari> lsAri, boolean syncStat){
        this.lsAri = lsAri;
        this.syncStat = syncStat;
    }

    public List<M_ari> getLsAri() {
        return lsAri;
    }

    public void setLsAri(List<M_ari> lsAri) {
        this.lsAri = lsAri;
    }

    public boolean isSyncStat() {
        return syncStat;
    }

    public void setSyncStat(boolean syncStat) {
        this.syncStat = syncStat;
    }
    
}
