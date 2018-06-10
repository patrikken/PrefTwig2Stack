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
public class Query {
    private String node_label;
    private ArrayList<Axis<Query>> childs_nodes;
    private Query parent;
    private HierachicalStack hierachicalStack;
    
    public Query() {
        parent = null;
        childs_nodes = new ArrayList<>();
        node_label = "";
        hierachicalStack = new HierachicalStack();
    }
    
    

    public String getNode_label() {
        return node_label;
    }

    public void setNode_label(String node_label) {
        this.node_label = node_label;
    }

    public ArrayList<Axis<Query>> getChilds_nodes() {
        return childs_nodes;
    }

    public void setChilds_nodes(ArrayList<Axis<Query>> childs_nodes) {
        this.childs_nodes = childs_nodes;
    }

    public Query getParent() {
        return parent;
    }

    public void setParent(Query parent) {
        this.parent = parent;
    }

    
}
