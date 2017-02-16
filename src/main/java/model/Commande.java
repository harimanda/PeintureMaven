/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import utils.Function;

/**
 *
 * @author itu
 */
public class Commande {

    private int idcommande;
    private String nom_commande;
    private double quantite;
    private double r;
    private double g;
    private double b;
    Function fonc = new Function();

    public Commande() {
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public void setIdcommande(String idcommande) {
        this.idcommande = Integer.parseInt(idcommande);
    }

    public String getNom_commande() {
        return nom_commande;
    }

    public void setNom_commande(String nom_commande) {
        this.nom_commande = nom_commande;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) throws Exception {
        if (quantite > 0) {
            this.quantite = quantite;
        } else {
            throw new Exception("quantite superieur a 0");
        }

    }

    public void setQuantite(String quantite) throws Exception {
        if (Double.parseDouble(quantite) > 0) {
            this.quantite = Double.parseDouble(quantite);
        } else {
            throw new Exception("quantite superieur a 0");
        }
    }

    public double getR() {
        return r;
    }

    public void setR(double r) throws Exception {
        double temp = fonc.getPourcentage(r);
        if (temp >= 0 && temp <= 100) {
            this.r = temp;
        } else {
            throw new Exception("pourcentage couleur errone");
        }

    }

    public void setR(String r) throws Exception {
        double temp = fonc.getPourcentage(Double.parseDouble(r));

        if (temp >= 0 && temp <= 100) {
            this.r = temp;
        } else {
            throw new Exception("pourcentage couleur errone");
        }
    }

    public double getG() {
        return g;
    }

    public void setG(double g) throws Exception {
        double temp = fonc.getPourcentage(g);

        if (temp >= 0 && temp <= 100) {
            this.g = temp;
        } else {
            throw new Exception("pourcentage couleur errone");
        }
    }

    public void setG(String g) throws Exception {
        double temp = fonc.getPourcentage(Double.parseDouble(g));

        if (temp >= 0 && temp <= 100) {
            this.g = temp;
        } else {
            throw new Exception("pourcentage couleur errone");
        }
    }

    public double getB() {
        return b;
    }

    public void setB(double b) throws Exception {
        double temp = fonc.getPourcentage(b);

        if (temp >= 0 && temp <= 100) {
            this.b = temp;
        } else {
            throw new Exception("pourcentage couleur errone");
        }

    }

    public void setB(String b) throws Exception {

        double temp = fonc.getPourcentage(Double.parseDouble(b));

        if (temp >= 0 && temp <= 100) {
            this.b = temp;
        } else {
            throw new Exception("pourcentage couleur errone");
        }
    }

    public String getValue() {

        String valC = "nom_commande,quantite,r,g,b";
        return valC;
    }

    public String getCol() {
        String args = "'" + nom_commande + "','" + quantite + "','" + r + "','" + g + "','" + b + "'";
        return args;
    }

}
