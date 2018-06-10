/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preftwig2stack;

import DataClass.DocElement;
import DataClass.HierachicalStack;
import DataClass.Stack;
import DataClass.StackTree;
import DataClass.XmlDocument;
import DataGen.GenData;
import Helpers.Constantes;
import Helpers.Utils;
import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patrik
 */
public class MainFunctionsTest {

    public MainFunctionsTest() {
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
     * Test of merge method, of class MainFunctions.
     */
    @Test
    public void testMerge() {
        System.out.println("merge");
        HierachicalStack HS = GenData.generateHierachicalStack();
        DocElement e = new DocElement("elem");
        e.setLeftPos(1);
        e.setRigthPos(-1);
        e.setLevel(1);
        String axis = Constantes.PC_EDGE;
        //System.out.println("Hs before mergin: " + HS.toString());
        MainFunctions instance = new MainFunctionsImpl();
        boolean expResult = false;
        boolean result = instance.merge(HS, e, axis);
        //System.out.println("Hs after mergin: " + HS.toString());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMergedStackTree method, of class MainFunctions.
     */
    @Test
    public void testCreateMergedStackTree() {
        System.out.println("createMergedStackTree");
        StackTree st1 = GenData.generateStackTree(2);
        StackTree st2 = GenData.generateStackTree(4);
        HierachicalStack Hs = new HierachicalStack("name");
        Hs.addStackTree(st2);
        Hs.addStackTree(st1);
        //  System.out.println("Hs before mergin: "+ Hs.toString());
        ArrayList<StackTree> STS = new ArrayList<>();
        STS.add(st2);
        STS.add(st1);
        MainFunctions instance = new MainFunctionsImpl();
        instance.createMergedStackTree(Hs, STS);
        // System.out.println("Hs after mergin: "+ Hs.toString());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class MainFunctionsImpl extends MainFunctions {
    }

    /**
     * Test of Twig2Stack method, of class MainFunctions.
     */
    @Test
    public void testTwig2Stack() {
        System.out.println("Twig2Stack");
        File inputFile = new File("doc.xml");
        XmlDocument doc = Utils.parse(inputFile);
        Stack<DocElement> stack = new Stack<>();
        doc.buildStack(stack); 
        HierachicalStack hs = GenData.generateHierachicalStackForEmpleDB();
         System.out.println("Hs before prefTiwg2Stack: " + hs.toString());
        MainFunctions instance = new MainFunctionsImpl();
        instance.Twig2Stack(stack, hs);
         System.out.println("Hs After: " + hs.toString());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
