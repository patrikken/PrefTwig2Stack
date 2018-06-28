/*
 * This class define tree structure of an XML Document.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataClass;

import java.util.ArrayList;
 

/**
 *
 * @author patrik
 */
public class XmlDocument {

    private DocElement root;

    private ArrayList<XmlDocument> childs;

    public XmlDocument(DocElement root) {
        this.root = root;
        this.childs = new ArrayList<>();
    }

    public XmlDocument(String element) {
        this.root = new DocElement(element);
        this.childs = new ArrayList<>();
    }

    public boolean isLeaf() {
        return childs.isEmpty();
    }

    @Override
    public String toString() {
        return root.getTag() + "{" + childs + '}';
    }

    public void addChild(XmlDocument xmlDoc) {
        childs.add(xmlDoc);
    }

    public int childSize() {
        return childs.size();
    }

    public XmlDocument getChildAt(int i) {
        return childs.get(i);
    }

    public void printPosOrder() {
        String toret = "";
        if (isLeaf()) {
            toret = toret + root.getTag() + " : Value = " + root.getValue();
        } else {
            toret = toret + "[";
            for (int i = 0; i <= childs.size() - 1; i++) {
                childs.get(i).printPosOrder();
                toret = toret + childs.get(i).getRoot().getTag() + ",";
            }
            toret = toret + "]" + root.getTag();
        }
        System.out.println(toret);
    }

    public DocElement getRoot() {
        return root;
    }

    public void setRoot(DocElement root) {
        this.root = root;
    }

    public void buildStack(Stack<DocElement> stack) {
        stack.push(root);
        for (int i = childs.size() - 1; i >=0 ; i--) {
            childs.get(i).buildStack(stack);
        } 
    }
    
    public void buildPrefStack(Stack<DocElementPref> stack) {
        DocElementPref elem = new DocElementPref(root);
        stack.push(elem);
        for (int i = childs.size() - 1; i >=0 ; i--) {
            childs.get(i).buildPrefStack(stack);
        } 
    }
}
