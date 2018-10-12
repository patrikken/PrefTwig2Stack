/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataClass;

import Helpers.Constantes;
import java.util.ArrayList;

/**
 *
 * @author patrik
 */
public class DocElement {

    private int leftPos;
    private int rigthPos;
    private int level;
    private String tag;
    private String value;

    private ArrayList<Axis<ChildResults>> childsResults;

    public DocElement(String tag) {
        this.leftPos = 0;
        this.rigthPos = 0;
        this.level = 0;
        this.tag = tag;
        this.value = "";
        this.childsResults = new ArrayList<>();
    }

    public DocElement() {
        this.leftPos = 0;
        this.rigthPos = 0;
        this.level = 0;
        this.tag = "";
        this.value = "";
        this.childsResults = new ArrayList<>();
    }

    public int getLeftPos() {
        return leftPos; 
    }

    public void setLeftPos(int leftPos) {
        this.leftPos = leftPos;
    }

    public int getRigthPos() {
        return rigthPos;
    }

    public void setRigthPos(int rigthPos) {
        this.rigthPos = rigthPos;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addPNEdge(DocElement e, String hsName) {
        Axis<ChildResults> axis = new Axis(Constantes.PN_EDGE, new ChildResults(e, hsName));
        childsResults.add(axis);
    }

    public void addPCEdge(DocElement e, String hsName) {
        Axis<ChildResults> axis = new Axis(Constantes.PC_EDGE, new ChildResults(e, hsName));
        childsResults.add(axis);
    }
    
    public void addPCEdge(StackTree t, String hsName) { 
        Axis<ChildResults> axis = new Axis(Constantes.PC_EDGE, new ChildResults(t, hsName));
        childsResults.add(axis);
    }

    public void addADEdge(DocElement e, String hsName) {
        Axis<ChildResults> axis = new Axis(Constantes.AD_EDGE, new ChildResults(e, hsName));
        childsResults.add(axis);
    }

    public void addADEdge(StackTree t, String hsName) { 
        Axis<ChildResults> axis = new Axis(Constantes.AD_EDGE, new ChildResults(t, hsName));
        System.out.println("DataClass.DocElement.addADEdge() ******\n******"+ t.toString());
        childsResults.add(axis);
    }

    public void addAPNEdge(DocElement e, String hsName) {
        Axis<ChildResults> axis = new Axis(Constantes.APN_EDGE, new ChildResults(e, hsName));
        childsResults.add(axis);
    }

    public ArrayList<Axis<ChildResults>> getChildsResults() {
        return childsResults;
    }

    @Override
    public String toString() {
        return this.tag;
    }
    
    public String print() {
        //return this.tag +"["+leftPos+","+rigthPos+"]";
        return this.toString(); 
    }

}
