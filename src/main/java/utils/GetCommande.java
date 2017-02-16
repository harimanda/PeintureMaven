/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.ResultSet;
import model.Commande;

/**
 *
 * @author itu
 */
public class GetCommande {

    boolean bool = false;
    Function fonc = new Function();
    String nomTable = "";
    ResultSet resultats = null;
    int rep = 0;
    String[] col = null;
    String[] colfiltre = null;
    String[][] filtres = null;

    public boolean verifCommande(String nom, double r, double g, double b) {
        nomTable = "commande";
        col = new String[]{"count(idcommande) as c"};
        colfiltre = new String[]{"nom_commande", "r", "g", "b"};
        filtres = new String[][]{{" = ", " = ", " = ", " = "}, {"'" + nom + "'", "'" + r + "'", "'" + g + "'", "'" + b + "'"}};
        resultats = fonc.find(nomTable, col, colfiltre, filtres, null);
        try {
            while (resultats.next()) {
                if (resultats.getInt("c") == 0) {
                    bool = true;
                } else {
                    bool = false;
                }
            }

        } catch (Exception e) {

        }
        return bool;
    }

    public boolean insertCommande(String nom, String quantite, String r, String g, String b) throws Exception {
        nomTable = "commande";
        Commande comm = new Commande();
        comm.setNom_commande(nom);
        comm.setQuantite(quantite);
        comm.setB(b);
        comm.setG(g);
        comm.setR(r);
        bool = this.verifCommande(comm.getNom_commande(), comm.getR(), comm.getG(), comm.getB());
        if (bool == true) {
            try {
                rep = fonc.insertToTable(comm, nomTable);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Commande deja envoye");
        }
        return bool;
    }
   
}
