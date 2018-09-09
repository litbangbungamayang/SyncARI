/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.ari.model;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class M_ari {
    
    private String no_spta;
    private String id_analisa;
    private Double persen_brix_ari;
    private Double persen_pol_ari;
    private Double faktor_rendemen; //a.k.a faktor perah KNPP
    private Double hk;
    private Double nilai_nira;
    private Double rendAri;
    private String syncStat;
    
    public M_ari(String id_spta, Double persen_brix_ari, Double persen_pol_ari, Double faktor_rendemen){
        this.no_spta = id_spta;
        this.persen_brix_ari = persen_brix_ari;
        this.persen_pol_ari = persen_pol_ari;
        this.faktor_rendemen = faktor_rendemen;
        if (this.persen_pol_ari > 0.00 && this.persen_brix_ari > 0.00){
            this.hk = (this.persen_pol_ari/this.persen_brix_ari)*100;
            this.nilai_nira = this.persen_pol_ari - 0.4*(this.persen_brix_ari - this.persen_pol_ari);
            this.rendAri = this.nilai_nira * this.faktor_rendemen;
        } else {
            this.hk = 0.00;
            this.nilai_nira = 0.00;
        }
    }
    
    public Double getPersen_brix_ari() {
        return persen_brix_ari;
    }

    public void setPersen_brix_ari(Double persen_brix_ari) {
        this.persen_brix_ari = persen_brix_ari;
    }

    public Double getPersen_pol_ari() {
        return persen_pol_ari;
    }

    public void setPersen_pol_ari(Double persen_pol_ari) {
        this.persen_pol_ari = persen_pol_ari;
    }

    public Double getFaktor_rendemen() {
        return faktor_rendemen;
    }

    public void setFaktor_rendemen(Double faktor_rendemen) {
        this.faktor_rendemen = faktor_rendemen;
    }

    public Double getHk() {
        return hk;
    }

    public void setHk(Double hk) {
        this.hk = hk;
    }

    public Double getNilai_nira() {
        return nilai_nira;
    }

    public void setNilai_nira(Double nilai_nira) {
        this.nilai_nira = nilai_nira;
    }

    public String getSyncStat() {
        return syncStat;
    }

    public void setSyncStat(String syncStat) {
        this.syncStat = syncStat;
    }

    public String getId_analisa() {
        return id_analisa;
    }

    public void setId_analisa(String id_analisa) {
        this.id_analisa = id_analisa;
    }

    public void setNo_spta(String no_spta) {
        this.no_spta = no_spta;
    }

    public String getNo_spta() {
        return no_spta;
    }

    public Double getRendAri() {
        return rendAri;
    }

    public void setRendAri(Double rendAri) {
        this.rendAri = rendAri;
    }
    
}
