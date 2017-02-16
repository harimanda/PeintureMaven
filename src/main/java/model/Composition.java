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
public class Composition {

    private int idcomposition;
    private int idproduit;
    private int idproduitcompsition;
    private double taux;
    private int idcommande;

    public Composition() {
    }

    public int getIdcomposition() {
        return idcomposition;
    }

    public void setIdcomposition(int idcomposition) {
        this.idcomposition = idcomposition;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getIdproduitcompsition() {
        return idproduitcompsition;
    }

    public void setIdproduitcompsition(int idproduitcompsition) {
        this.idproduitcompsition = idproduitcompsition;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public void setTaux(String taux) {
        this.taux = Double.parseDouble(taux);
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public String getValue() {

        String valC = "idproduit,idproduitcomposant,taux";
        return valC;
    }

    public String getCol() {
        String args = "'" + idproduit + "','" + idproduitcompsition + "','" + taux + "'";
        return args;
    }

}
