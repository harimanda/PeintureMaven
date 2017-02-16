/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Colori;
import model.Commande;
import model.Composition;
import model.Getcompo;
import model.Marge;
import model.QuantiteColori;
import peinture.Compositions;

/**
 *
 * @author itu
 */
public class GetColori {

    boolean bool = false;
    Function fonc = new Function();
    String nomTable = "";
    ResultSet resultats = null;
    int rep = 0;
    String[] col = null;
    String[] colfiltre = null;
    String[][] filtres = null;
    Vector vec = new Vector();
    int fr = 0;
    int fg = 0;
    int fb = 0;
    Object[] id;
    Object[] nom;
    Object[] r;
    Object[] v;
    Object[] b;
    Object[] qu;
    Object[] vo;
    Object[] vente;

    public boolean verifColori(String nom, double r, double g, double b) {
        nomTable = "colori";
        col = new String[]{"count(idcolori) as c"};
        colfiltre = new String[]{"nom", "r", "g", "b"};
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

    public Colori getIdColor(String nom, double r, double g, double b) {
        Colori coll = new Colori();
        nomTable = "colori";
        col = new String[]{"idcolori"};
        colfiltre = new String[]{"nom", "r", "g", "b"};
        filtres = new String[][]{{" = ", " = ", " = ", " = "}, {"'" + nom + "'", "'" + r + "'", "'" + g + "'", "'" + b + "'"}};
        resultats = fonc.find(nomTable, col, colfiltre, filtres, null);
        try {
            while (resultats.next()) {
                coll.setIdcolori(resultats.getInt("idcolori"));;
            }

        } catch (Exception e) {

        }
        return coll;
    }

    public Marge getMarge() {
        Marge coll = new Marge();
        nomTable = "marge";
        col = new String[]{"marge"};
        resultats = fonc.find(nomTable, col, null, null, null);
        try {
            while (resultats.next()) {
                coll.setMarge(resultats.getDouble("marge"));
                coll.setIdmarge(resultats.getInt("idmarge"));

            }

        } catch (Exception e) {

        }
        return coll;
    }

    public boolean insertColori(String nom, String quantite, String r, String g, String b, String prix) throws Exception {
        nomTable = "colori";
        Colori temp = new Colori();
        QuantiteColori q = new QuantiteColori();
        Colori comm = new Colori();
        comm.setNom(nom);
        comm.setR(r);
        comm.setG(g);
        comm.setB(b);
        bool = this.verifColori(comm.getNom(), comm.getR(), comm.getG(), comm.getB());
        if (bool == true) {
            try {
                rep = fonc.insertToTable(comm, nomTable);
                temp = this.getIdColor(nom, comm.getR(), comm.getG(), comm.getB());
                q.setIdcolori(temp.getIdcolori());
                q.setQuantite(quantite);
                q.setPrixdevente(prix);
                nomTable = "quantite";
                rep = fonc.insertToTable(q, nomTable);

            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Couleur deja inserer");
        }
        return bool;
    }

    public double getPrixVente(double rev, double marge) {
        double val = 0;
        val = (rev * marge) + rev;
        return val;
    }

    public double getPrixRevient(double val, double marge) {
        double rev = 0;
        rev = (val) / ((marge) + 1);
        return rev;
    }

    public QuantiteColori[] getAllColori() {
        QuantiteColori[] colori = null;
        Marge cols = new Marge();
        cols = this.getMarge();
        nomTable = "allstock";
        resultats = fonc.find(nomTable, null, null, null, null);
        try {
            while (resultats.next()) {
                QuantiteColori temp = new QuantiteColori();
                temp.setIdcolori(resultats.getInt("idcolori"));
                temp.setNom(resultats.getString("nom"));
                temp.setRF(resultats.getDouble("r"));
                temp.setBF(resultats.getDouble("b"));
                temp.setGF(resultats.getDouble("g"));
                temp.setQuantite(resultats.getDouble("quantites"));
                temp.setPrixdevente(resultats.getDouble("prixunitaire"));
                temp.setPrixunitaire(this.getPrixRevient(resultats.getDouble("prixunitaire"), cols.getMarge()));
                vec.add(temp);
            }
            colori = new QuantiteColori[vec.size()];
            for (int i = 0; i < vec.size(); i++) {
                colori[i] = (QuantiteColori) vec.get(i);
            }

        } catch (Exception e) {

        }

        return colori;
    }

    public Object[][] getAllRows(JTable table) {
        int taille = table.getModel().getRowCount();
        int taillc = table.getModel().getColumnCount();
        Object[][] val = new Object[taille][taillc];
        for (int i = 0; i < taille; i++) {
            for (int u = 0; u < taillc; u++) {
                val[i][u] = table.getModel().getValueAt(i, u);
            }
        }
        return val;
    }

    public Commande[] getAllCommande() {
        Commande[] comand = null;
        nomTable = "allcommande";
        resultats = fonc.find(nomTable, null, null, null, null);
        try {
            while (resultats.next()) {
                Commande temp = new Commande();
                temp.setIdcommande(resultats.getInt("idcommande"));
                temp.setNom_commande(resultats.getString("nom_commande"));
                temp.setQuantite(resultats.getDouble("quantite"));
                temp.setR(resultats.getDouble("r"));
                temp.setG(resultats.getDouble("g"));
                temp.setB(resultats.getDouble("b"));
                vec.add(temp);
            }
            comand = new Commande[vec.size()];
            for (int i = 0; i < vec.size(); i++) {
                comand[i] = (Commande) vec.get(i);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return comand;
    }

    public Getcompo[] getComposition(String idcolori) {
        Getcompo[] val = null;
        nomTable = "getcompo";
        colfiltre = new String[]{"idproduit"};
        filtres = new String[][]{{" = "}, {"'" + idcolori + "'"}};
        resultats = fonc.find(nomTable, null, colfiltre, filtres, null);
        try {
            while (resultats.next()) {
                Getcompo temp = new Getcompo();
                temp.setIdcolori(resultats.getInt("idcolori"));
                temp.setNom(resultats.getString("nom"));
                temp.setRF(resultats.getDouble("r"));
                temp.setGF(resultats.getDouble("g"));
                temp.setBF(resultats.getDouble("b"));
                temp.setQuantites(resultats.getDouble("quantites"));
                temp.setTaux(resultats.getDouble("taux"));
                vec.add(temp);
            }
            val = new Getcompo[vec.size()];
            for (int i = 0; i < vec.size(); i++) {
                val[i] = (Getcompo) vec.get(i);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return val;
    }

    public QuantiteColori simuler(Object[][] obj, String qcommande, String nom) throws Exception {
        QuantiteColori col = new QuantiteColori();
        Marge cols = new Marge();
        cols = this.getMarge();
        int taille = obj.length;
        double r = 0;
        double v = 0;
        double b = 0;
        double p = 0;
        double poids = 0;
        double prxv = 0;
        for (int i = 0; i < taille; i++) {
            if (!String.valueOf(obj[i][1]).equals("")) {
                r = r + (((Double.parseDouble(String.valueOf(obj[i][1]))) * Double.parseDouble(String.valueOf(obj[i][4]))) / (Double.parseDouble(String.valueOf(qcommande))));
                v = v + (((Double.parseDouble(String.valueOf(obj[i][1]))) * Double.parseDouble(String.valueOf(obj[i][5]))) / (Double.parseDouble(String.valueOf(qcommande))));
                b = b + (((Double.parseDouble(String.valueOf(obj[i][1]))) * Double.parseDouble(String.valueOf(obj[i][6]))) / (Double.parseDouble(String.valueOf(qcommande))));
                p = p + (((Double.parseDouble(String.valueOf(obj[i][1]))) * Double.parseDouble(String.valueOf(obj[i][7]))));
                poids = poids + (Double.parseDouble(String.valueOf(obj[i][1])));
            }
        }
        if (poids == (Double.parseDouble(String.valueOf(qcommande)))) {
            p = p / (Double.parseDouble(String.valueOf(qcommande)));
            prxv = this.getPrixVente(p, cols.getMarge());
            col.setNom(nom);
            col.setBF(b);
            col.setGF(v);
            col.setRF(r);
            col.setPrixunitaire(p);
            col.setPrixdevente(prxv);
            try {
                col.setQuantite(qcommande);
            } catch (Exception e) {

            }
        } else {
            throw new Exception("Quantite different");
        }
        return col;
    }

    public double getTaux(double pnouv, double pcompo) {
        double repp = 0;
        repp = (pnouv * 100) / pcompo;
        return repp;
    }

    public void enregistrerM(Object[][] val, Object[][] compo) throws Exception {
        String nom = String.valueOf(val[0][1]);
        double quantite = Double.parseDouble(String.valueOf(val[0][0]));
        double r = Double.parseDouble(String.valueOf(val[0][2]));
        double v = Double.parseDouble(String.valueOf(val[0][3]));
        double b = Double.parseDouble(String.valueOf(val[0][4]));
        double p = Double.parseDouble(String.valueOf(val[0][6]));
        double compou = 0;
        double asp = 0;
        int idc = 0;
        double tau = 0;
        int taille = compo.length;
        nomTable = "colori";
        Colori temp = new Colori();
        QuantiteColori q = new QuantiteColori();
        Colori comm = new Colori();
        Composition compot = new Composition();
        comm.setNom(nom);
        comm.setRF(r);
        comm.setGF(v);
        comm.setBF(b);
        bool = this.verifColori(comm.getNom(), comm.getR(), comm.getG(), comm.getB());
        if (bool == true) {
            try {
                rep = fonc.insertToTable(comm, nomTable);
                temp = this.getIdColor(nom, comm.getR(), comm.getG(), comm.getB());
                q.setIdcolori(temp.getIdcolori());
                q.setQuantite(quantite);
                q.setPrixdevente(p);
                nomTable = "quantite";
                rep = fonc.insertToTable(q, nomTable);
                for (int i = 0; i < taille; i++) {
                    if (!String.valueOf(compo[i][1]).equals("0.0")) {
                        compou = Double.parseDouble(String.valueOf(compo[i][1]));
                        idc = Integer.parseInt(String.valueOf(compo[i][0]));
                        asp = Double.parseDouble(String.valueOf(compo[i][2]));
                        tau = this.getTaux(compou, asp);
                        compot.setIdproduit(temp.getIdcolori());
                        compot.setIdproduitcompsition(idc);
                        compot.setTaux(tau);
                        nomTable = "composition";
                        rep = fonc.insertToTable(compot, nomTable);
                    }
                }

            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Couleur deja inserer");
        }

    }

    public QuantiteColori getColoriByid(String id) {
        QuantiteColori valic = new QuantiteColori();
        nomTable = "allstock";
        colfiltre = new String[]{"idcolori"};
        filtres = new String[][]{{" = "}, {"'" + id + "'"}};
        resultats = fonc.find(nomTable, null, colfiltre, filtres, null);
        try {
            while (resultats.next()) {
                valic.setIdcolori(resultats.getInt("idcolori"));
                valic.setNom(resultats.getString("nom"));
                valic.setRF(resultats.getDouble("r"));
                valic.setBF(resultats.getDouble("b"));
                valic.setGF(resultats.getDouble("g"));
                valic.setQuantite(resultats.getDouble("quantites"));
            }

        } catch (Exception e) {

        }
        return valic;

    }

    public void rempTableM(String idcolori, Compositions compo) {
        Getcompo[] fer = this.getComposition(idcolori);
        QuantiteColori qt = this.getColoriByid(idcolori);
        int f = fer.length;
        String[] columns = null;
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        String[] rep = null;
        nom = new Object[f];
        r = new Object[f];
        v = new Object[f];
        b = new Object[f];
        qu = new Object[f];
        vo = new Object[f];
        vente = new Object[f];
        id = new Object[f];
        int u = 0;
        for (int i = 0; i < f; i++) {
            id[u] = (Object) fer[i].getIdcolori();
            nom[u] = (Object) fer[i].getNom();
            r[u] = (Object) fer[i].getR();
            v[u] = (Object) fer[i].getG();
            b[u] = (Object) fer[i].getB();
            qu[u] = (Object) fer[i].getQuantites();
            vo[u] = (Object) (fer[i].getTaux());

            u++;

        }
        compo.setTable().setModel(model);
        model.addColumn("id", id);
        model.addColumn("Nom", nom);
        model.addColumn("R (%)", r);
        model.addColumn("V (%)", v);
        model.addColumn("B (%)", b);
        model.addColumn("B (%)", b);
        model.addColumn("Quantite (kg)", qu);
        model.addColumn("Taux (%)", vo);
        
            fr = (int)fonc.getRVB(qt.getR());
            fg = (int)fonc.getRVB(qt.getG());
            fb = (int)fonc.getRVB(qt.getB());
            compo.setNomCol().setText(qt.getNom());
            compo.setQuant().setText(String.valueOf(qt.getQuantite()));
            compo.setR().setText(String.valueOf(qt.getR()));
            compo.setV().setText(String.valueOf(qt.getG()));
            compo.setB().setText(String.valueOf(qt.getB()));
            Color colo = new Color(fr,fg,fb);
            compo.setBut().setBackground(colo);
            

    }

    public static void main(String[] args) {
        GetColori t = new GetColori();
        Getcompo[] gy = null;
        double v = 0;
        double q = 20;
        double qt = 10;
        String id = "33";
        gy = t.getComposition(id);
        System.out.println(gy[0].getIdcolori());
    }

}
