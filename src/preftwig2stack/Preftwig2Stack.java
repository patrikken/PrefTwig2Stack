/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preftwig2stack;

import DataClass.DocElement;
import DataClass.DocElementPref;
import DataClass.GTPResult;
import DataClass.HierachicalStack;
import DataClass.SOT;
import DataClass.Stack;
import DataClass.StackTree;
import DataClass.XmlDocument;
import DataGen.GenData;
import Helpers.Utils;
import View.Home;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author patrik
 */
public class Preftwig2Stack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
            XmlDocument xmlDocument= new XmlDocument(new DocElement("a1"));
            XmlDocument d2= new XmlDocument(new DocElement("b1"));
            xmlDocument.addChild(new XmlDocument(new DocElement("a2")));
            xmlDocument.addChild(d2);
            xmlDocument.addChild(new XmlDocument(new DocElement("a3")));
            d2.addChild(new XmlDocument(new DocElement("b2")));
            d2.addChild(new XmlDocument(new DocElement("b3")));
            xmlDocument.printPosOrder();
         
        File inputFile = new File("doc.xml");
        XmlDocument doc = Utils.parse(inputFile);
        Stack<DocElement> stack = new Stack<>();
        doc.buildStack(stack); 
        try { 
            Home fen = new Home(doc);
            fen.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Preftwig2Stack.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        // Stack<DocElementPref> stack = new Stack<>();
        File inputFile = new File("firstTest.xml");
        XmlDocument doc = Utils.parse(inputFile);
        //doc.buildPrefStack(stack);
        // doc = null;   
        //System.out.println("Hs before PrefTiwg2Stack: " + Hs.toString());
        GTPResult response = null, result = null;
        Thread t2 = new Thread() {
            public void run() {
                Stack<DocElementPref> stack = new Stack<>();
                doc.buildPrefStack(stack);
                long startTime = System.nanoTime();
                HierachicalStack Hs = GenData.generateHierachicalStackForTreeBank();
                MainFunctions instance = new MainFunctions();
                instance.PrefTwig2Stack(stack, Hs);
                //instance.filterHS(Hs);
                SOT eSOT = Hs.makeSOT();
                GTPResult response = instance.enumTiwg2Stack(Hs, eSOT);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println(totalTime);
            }
        };

        for (int i = 0; i <= 10; i++) {
            Thread t1 = new Thread() {
                public void run() {
                    Stack<DocElement> stack = new Stack<>();
                    doc.buildStack(stack);
                    long startTime = System.nanoTime();
                    HierachicalStack hs = GenData.generateHierachicalStackForTreeBank();
                    Twig2Stack instance = new Twig2Stack();
                    instance.twig2Stack(stack, hs);
                    SOT eSOT = hs.makeSOT();
                    GTPResult result = instance.enumTiwg2Stack(hs, eSOT);
                    long endTime = System.nanoTime();
                    long totalTime = endTime - startTime;
                    System.out.println(totalTime);
                }
            };
            t1.start();
        } 
    }

}
