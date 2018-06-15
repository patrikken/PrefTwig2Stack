/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preftwig2stack;

import DataClass.DocElement;
import DataClass.HierachicalStack;
import DataClass.SOT;
import DataClass.Stack;
import DataClass.StackTree;
import DataClass.Tree;
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
        SOT sot = new SOT(st1.toTree(2));
        System.out.println("ST = : "+ st1.toString());
        System.out.println("SOT = : "+ sot.toString());
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
     /*   System.out.println("Twig2Stack");
        File inputFile = new File("doc.xml");
        XmlDocument doc = Utils.parse(inputFile);
        Stack<DocElement> stack = new Stack<>();
        doc.buildStack(stack); 
        HierachicalStack hs = GenData.generateHierachicalStackForEmpleDB();
         System.out.println("Hs before prefTiwg2Stack: " + hs.toString());
        MainFunctions instance = new MainFunctionsImpl();
        instance.Twig2Stack(stack, hs);
         System.out.println("Hs After: " + hs.toString());*/
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Prefmerge method, of class MainFunctions.
     */
    @Test
    public void testPrefmerge() {
        System.out.println("Prefmerge");
        HierachicalStack HS = null;
        DocElement e = null;
        String axis = "";
        MainFunctions instance = new MainFunctionsImpl();
        boolean expResult = false;
        boolean result = instance.Prefmerge(HS, e, axis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MatchOneNode method, of class MainFunctions.
     */
    @Test
    public void testMatchOneNode() {
        System.out.println("MatchOneNode");
        DocElement currentElem = null;
        HierachicalStack HS = null;
        MainFunctions instance = new MainFunctionsImpl();
        instance.MatchOneNode(currentElem, HS);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pointPC method, of class MainFunctions.
     */
    @Test
    public void testPointPC() {
        System.out.println("pointPC");
        DocElement e = null; 
        /*Stack<DocElement> stack = new Stack<>();
        File inputFile = new File("doc.xml");
        XmlDocument doc = Utils.parse(inputFile);
        doc.buildStack(stack); 
        HierachicalStack Hs = GenData.generateHierachicalStackForEmpleDB();
        System.out.println("Hs before PrefTiwg2Stack: " + Hs.toString());
        MainFunctions instance = new MainFunctionsImpl();
        instance.Twig2Stack(stack, Hs);
        e = Hs.getStackTrees().get(0).getRacine().get(0);
        System.out.println("e = "+e.toString());  
        SOT expResult = null;
        SOT result = instance.pointPC(e, Hs.getChilds().get(0).getTarget()); 
        System.out.println("Hs After: " + Hs.toString());
        System.out.println("SOT :"+ result.toString());
        assertEquals(expResult, result);*/
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pointAD method, of class MainFunctions.
     */
    @Test
    public void testPointAD() {
        System.out.println("pointAD");
      /*  DocElement e = null;
        Stack<DocElement> stack = new Stack<>();
        File inputFile = new File("doc.xml");
        XmlDocument doc = Utils.parse(inputFile);
        doc.buildStack(stack); 
        HierachicalStack Hs = GenData.generateHierachicalStackForEmpleDB();
        System.out.println("Hs before PrefTiwg2Stack: " + Hs.toString());
        MainFunctions instance = new MainFunctionsImpl();
        instance.Twig2Stack(stack, Hs);
        e = Hs.getStackTrees().get(1).getRacine().get(0);
        System.out.println("e = "+e.toString());  
        SOT expResult = null; 
        System.out.println("Hs After: " + Hs.toString());
        SOT result = instance.pointAD(e, Hs.getChilds().get(0).getTarget());
        System.out.println("SOT :"+ result.toString());
        assertEquals(expResult, result);*/
        /*StackTree st = GenData.generateStackTree(1);
        StackTree st1 = GenData.generateStackTree(2);
        StackTree st2 = GenData.generateStackTree(3);
        st.addChild(st1);
        st.addChild(st2);
        System.out.println("StackTree = "+st.toString());
        Tree t = st.toTree(2);
        System.out.println("Tree = "+t.toString());*/
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }     

    /**
     * Test of computeTotalEffects method, of class MainFunctions.
     */
    @Test
    public void testComputeTotalEffects() {
        System.out.println("computeTotalEffects");
         Stack<DocElement> stack = new Stack<>();
        File inputFile = new File("doc.xml");
        XmlDocument doc = Utils.parse(inputFile);
        doc.buildStack(stack); 
        HierachicalStack Hs = GenData.generateHierachicalStackForEmpleDB();
        System.out.println("Hs before PrefTiwg2Stack: " + Hs.toString());
        MainFunctions instance = new MainFunctionsImpl();
        instance.Twig2Stack(stack, Hs); 
        System.out.println("Hs after PrefTiwg2Stack: " + Hs.toString());
        SOT eSOT = Hs.makeSOT();
        System.out.println("SOT HS = "+ eSOT.toString());
        String axis = Constantes.AD_EDGE;
        HierachicalStack hs = null; 
        SOT expResult = null;
        SOT result = instance.computeTotalEffects(eSOT, axis, Hs.getChilds().get(0).getTarget());
        System.out.println("SOT result = "+ result.toString());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 
}
