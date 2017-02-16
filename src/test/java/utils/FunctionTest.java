/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.ResultSet;
import model.Colori;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author itu
 */
public class FunctionTest {
    
    public FunctionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Execute method, of class Function.
     */
  
    
      @org.junit.Test
    public void testInsertToTable() throws Exception {
        System.out.println("insertToTable");
        Colori o = new Colori();
        o.setNom("Blanc taps");
        o.setR("127");
        o.setG("37");
        o.setB("41");
        String nomTable = "colori";
        Function instance = new Function();
        int expResult = 0;
        int result = instance.insertToTable(o, nomTable);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class Function.
     */
        @org.junit.Test
    public void testFind() {
        System.out.println("find");
        String table = "colori";
        String[] col = null;
        String[] nomColFiltre = new String[]{"nom"};
        String[][] filtre = new String[][]{{" = "},{"'Abricot'"}};
        String[] groupByClause = null;
        Function instance = new Function();
         Colori colo1 = new Colori();
        ResultSet expResult = null;
         
               
                colo1.setNom("Abricot");
         
        ResultSet result = instance.find(table, col, nomColFiltre, filtre, groupByClause);
         Colori colo2 = new Colori();
        try{
            while(result.next()){
               
                colo2.setNom(result.getString("nom"));
            }
        }
        catch(Exception e){
            
        }
        assertEquals(colo1.getNom(),colo2.getNom());
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of updateToTable method, of class Function.
     */
   

    /**
     * Test of getPourcentage method, of class Function.
     */
     @org.junit.Test
    public void testGetPourcentage() {
        System.out.println("getPourcentage");
        double couleur = 134;
        Function instance = new Function();
        double expResult = 52.549019607843137254901960784314;
        double result = instance.getPourcentage(couleur);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getRVB method, of class Function.
     */
    @org.junit.Test
    public void testGetRVB() {
        System.out.println("getRVB");
        double couleur = 13.5;
        Function instance = new Function();
        double expResult = 34.425;
        double result = instance.getRVB(couleur);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of deleteFromTable method, of class Function.
     */
    @org.junit.Test
    public void testDeleteFromTable() throws Exception {
        System.out.println("deleteFromTable");
        String nomTable = "colori";
        String idel = "Blanc taps";
        String idcol = "nom";
        Function instance = new Function();
        int expResult = 1;
        int result = instance.deleteFromTable(nomTable, idel, idcol);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
     @org.junit.Test
    public void testUpdateToTable() throws Exception {
        System.out.println("updateToTable");
        Colori o = new Colori();
        o.setNom("Blanc taps");
        String nomTable = "colori";
        String[] nomColFiltre = new String[]{"nom"};
        String[][] filtre = new String[][]{{" = "},{"'Blanc taps'"}};
        Function instance = new Function();
        int expResult = 0;
        int result = instance.updateToTable(o, nomTable, nomColFiltre, filtre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
