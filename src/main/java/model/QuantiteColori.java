/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author itu
 */
public class QuantiteColori extends Colori {

    private double quantite;
    private double prixunitaire;
    private double prixdevente;
    private double marge;

    public QuantiteColori() {
    }

    public double getMarge() {
        return marge;
    }

    public void setMarge(double marge) {
        this.marge = marge;
    }

    public void setMarge(String marge) {
        this.marge = Double.parseDouble(marge);
    }

    public double getPrixdevente() {
        return prixdevente;
    }

    public void setPrixdevente(double prixdevente) {
        this.prixdevente = prixdevente;
    }

    public void setPrixdevente(String prixdevente) {
        this.prixdevente = Double.parseDouble(prixdevente);
    }

    public double getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(double prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) throws Exception {
        if (quantite > 0) {
            this.quantite = quantite;
        } else {
            throw new Exception("quantite doit etre superieur a 0");
        }

    }

    public void setQuantite(String quantite) throws Exception {
        if (Double.parseDouble(quantite) > 0) {
            this.quantite = Double.parseDouble(quantite);
        } else {
            throw new Exception("quantite doit etre superieur a 0");
        }
    }

    public String getValue() {

        String valC = "idcolori,quantites,prixunitaire";
        return valC;
    }

    public String getCol() {
        String args = "'" + this.getIdcolori() + "','" + quantite + "','" + prixdevente + "'";
        return args;
    }
}
