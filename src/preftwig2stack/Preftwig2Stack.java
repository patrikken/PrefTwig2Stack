/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preftwig2stack;

import DataClass.DocElement;
import DataClass.Stack;
import DataClass.StackTree;
import DataClass.XmlDocument;
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
        try {
            /*
            XmlDocument xmlDocument= new XmlDocument(new DocElement("a1"));
            XmlDocument d2= new XmlDocument(new DocElement("b1"));
            xmlDocument.addChild(new XmlDocument(new DocElement("a2")));
            xmlDocument.addChild(d2);
            xmlDocument.addChild(new XmlDocument(new DocElement("a3")));
            d2.addChild(new XmlDocument(new DocElement("b2")));
            d2.addChild(new XmlDocument(new DocElement("b3")));
            xmlDocument.printPosOrder();
             */
            File inputFile = new File("doc.xml");
            XmlDocument doc = Utils.parse(inputFile); 
            Stack<DocElement> stack = new Stack<>();
            doc.buildStack(stack);
            DocElement e = new DocElement("e");
            stack.push(e);
            stack.push(new DocElement());
            int i = stack.getIndex(e); 
            System.out.println("index="+i); 
            Home fen = new Home(doc); 
           // fen.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Preftwig2Stack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
