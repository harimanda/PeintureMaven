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
public class Colori {

    private int idcolori;
    private String nom;
    private double r;
    private double g;
    private double b;
    private double prix;
    Function fonc = new Function();

    public Colori() {
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
     public void setPrix(String prix) {
        this.prix = Double.parseDouble(prix);
    }
    
    public int getIdcolori() {
        return idcolori;
    }

    public void setIdcolori(int idcolori) {
        this.idcolori = idcolori;
    }

    public void setIdcolori(String idcolori) {
        this.idcolori = Integer.parseInt(idcolori);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public void setRF(double r) {

        this.r = r;

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

    public void setGF(double g) {

        this.g = g;

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

    public void setBF(double b) {

        this.b = b;

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

        String valC = "nom,r,g,b";
        return valC;
    }

    public String getCol() {
        String args = "'" + nom + "','" + r + "','" + g + "','" + b + "'";
        return args;
    }
    public String Update()
	{
		return "nom='"+nom+"'";
	}
}
