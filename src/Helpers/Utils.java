/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import DataClass.DocElement;
import DataClass.XmlDocument;
import java.io.File;
import java.io.IOException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author patrik
 */
public class Utils {
    
     public static XmlDocument parse(File inputFile){
        try { 
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         XMLHandler xmlHandler = new XMLHandler();
         saxParser.parse(inputFile, xmlHandler);     
      } catch (ParserConfigurationException | SAXException | IOException e) {
         e.printStackTrace();
      } 
        return XMLHandler.doc;
    }
     
     public static DefaultMutableTreeNode toTreeModel(XmlDocument docElement){
         DocElement elem=docElement.getRoot();
         DefaultMutableTreeNode root= new DefaultMutableTreeNode(elem.getTag()+" ["+elem.getLeftPos()+" ,"+elem.getRigthPos()+"],"+elem.getLevel());
         if(docElement.isLeaf())
             return root;
         else{
             for(int i=0; i <= docElement.childSize() - 1; i++){
                 root.add(toTreeModel(docElement.getChildAt(i)));
             }
         }
         return root;
     } 
}
