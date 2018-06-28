/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataClass;
 
import java.util.ArrayList;

/**
 *
 * @author patrik
 */
public class QueryNode {
    private String label;
    private ArrayList<Axis<QueryNode>> childs;
    private QueryNode node;
    private HierachicalStack hierachicalStack;
    
    public QueryNode() {
        node = null;
        childs = new ArrayList<>();
        label = "";
        hierachicalStack = new HierachicalStack();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<Axis<QueryNode>> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Axis<QueryNode>> childs) {
        this.childs = childs;
    }

    public QueryNode getNode() {
        return node;
    }

    public void setNode(QueryNode node) {
        this.node = node;
    }

    public HierachicalStack getHierachicalStack() {
        return hierachicalStack;
    }

    public void setHierachicalStack(HierachicalStack hierachicalStack) {
        this.hierachicalStack = hierachicalStack;
    } 
    
}
