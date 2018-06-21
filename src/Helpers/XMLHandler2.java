/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import DataClass.XmlDocument;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author patrik
 */
public class XMLHandler2 extends DefaultHandler {

    public static XmlDocument doc;

    private Stack<XmlDocument> st;

    private int index;

    private int depth;
    
    File fout = new File("out.txt");
    FileOutputStream fos;
    BufferedWriter bw;

    public XMLHandler2() throws FileNotFoundException {
        this.st = new Stack<>();
        this.index = 1;
        this.depth = 0; 
        fos = new FileOutputStream(fout);
        bw = new BufferedWriter(new OutputStreamWriter(fos));
    }

    public XmlDocument getDoc() {
        return doc;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Helpers.XMLHandler.startDocument()");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atrbts) throws SAXException {
        //System.out.println("qName param ="+qName); 
        if (index == 1) {
            doc = new XmlDocument(qName);
            doc.getRoot().setLeftPos(index);
            st.push(doc);
            index++;
        } else {
            XmlDocument x = new XmlDocument(qName);
            st.push(doc);
            doc = x;
            doc.getRoot().setLeftPos(index);
            index++;
        }
        depth++;
    }

    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        doc.getRoot().setValue(new String(chars, start, length));
    }

    @Override
    public void endElement(String string, String qName, String string2) throws SAXException {
        XmlDocument x = st.pop();
        doc.getRoot().setRigthPos(index);
        doc.getRoot().setLevel(depth);
        depth--;
        index++;
        if (x != doc) {
            x.addChild(doc);
        }
        doc = x;
        try { 
            bw.write(doc.getRoot().print());
            bw.newLine();
        } catch (IOException ex) {
            Logger.getLogger(XMLHandler2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            //  System.out.println("***************Fin du parser*************");
            //doc.printPosOrder();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(XMLHandler2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
