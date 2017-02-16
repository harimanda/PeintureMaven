/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import connexion.ConnectBase;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author itu
 */
public class Function {

    ConnectBase con = new ConnectBase();
    Connection c = null;
    ResultSet resultats = null;
    Statement stmt = null;
    String requete = "";



    public int insertToTable(Object o, String nomTable) throws Exception {

        c = con.connect();

        int status = 0;
        Method met = o.getClass().getMethod("getCol");
        String valCol = (String) met.invoke(o, null);
        Method met1 = o.getClass().getMethod("getValue");
        String valueCol = (String) met1.invoke(o, null);
        String requete = "INSERT INTO " + nomTable + "( " + valueCol + ") VALUES (" + valCol + ")";

        try {
            System.out.println(requete);
            stmt = c.createStatement();
            stmt.executeUpdate(requete);
            c.close();

            //resultats.close();
        } catch (Exception e) {

        }

        return status;
    }

    public ResultSet find(String table, String[] col, String[] nomColFiltre, String[][] filtre, String[] groupByClause) {
        c = con.connect();
        String where = "";
        String colonne = "";
        String groupBy = "";
        if (col != null) {
            for (int i = 0; i < col.length; i++) {
                colonne += col[i];
                if (i != col.length - 1) {
                    colonne += ", ";
                }
            }
        } else {
            colonne = "*";
        }
        String sql = "SELECT " + colonne + " FROM " + table + " " + where;

        if (nomColFiltre != null && filtre != null) {
            where = " WHERE ";
            for (int i = 0; i < nomColFiltre.length; i++) {
                where += nomColFiltre[i] + filtre[0][i] + filtre[1][i];
                if (i != nomColFiltre.length - 1) {
                    where = where + " AND ";
                }
            }
            sql = sql + where;
        }
        if (groupByClause != null) {
            for (int i = 0; i < groupByClause.length; i++) {
                groupBy += groupByClause[i];
                if (i < groupByClause.length - 1) {
                    groupBy += ", ";
                }
            }
            //System.out.println(groupBy);
            groupBy = " GROUP BY " + groupBy;
            sql += groupBy;

        }

        try {
            System.out.println(sql);
            stmt = c.createStatement();
            resultats = stmt.executeQuery(sql);
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultats;
    }

    public int updateToTable(Object o, String nomTable, String[] nomColFiltre, String[][] filtre) throws Exception {
        String where = "";
        c = con.connect();
        int status = 0;
        Method met = o.getClass().getMethod("Update");
        String valCol = (String) met.invoke(o, null);
        String requete = "UPDATE " + nomTable + " SET " + valCol + " " + where;
        for (int i = 0; i < nomColFiltre.length; i++) {
            where = " WHERE ";
            where += nomColFiltre[i] + filtre[0][i] + filtre[1][i];
            if (i != nomColFiltre.length - 1) {
                where = where + " AND ";
            }
        }
        requete = requete + where;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(requete);
            String comm = "commit";
            stmt.executeUpdate(comm);
            c.close();

            //resultats.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
    public double getPourcentage(double couleur){
        double val = 0;
        val = (couleur * 100)/255;
        return val;
    }
     public double getRVB(double couleur){
        double val = 0;
        val = (couleur * 255)/100;
        return val;
    }


}
