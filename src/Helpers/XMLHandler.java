/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import DataClass.XmlDocument;
import java.io.File;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author patrik
 */
public class XMLHandler extends DefaultHandler {

    public static XmlDocument doc;

    private Stack<XmlDocument> st;

    private int index;

    private int depth;

    public XMLHandler() {
        this.st = new Stack<>();
        this.index = 1;
        this.depth = 0;
    }

    public XmlDocument getDoc() {
        return doc;
    }

    @Override
    public void startDocument() throws SAXException {
        //System.out.println("Helpers.XMLHandler.startDocument()");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atrbts) throws SAXException {
        //System.out.println("qName param ="); 
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
        String s = new String(chars, start, length);  
        doc.getRoot().setValue(s.trim().replaceAll("\n", ""));
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
    }

    @Override
    public void endDocument() throws SAXException {
        //  System.out.println("***************Fin du parser*************");
        //doc.printPosOrder();
    }

}
