/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataClass;
 
/**
 *
 * @author patrik
 */
public class Query {
    private QueryNode parent;
    private String top_Edge;

    public QueryNode getParent() {
        return parent;
    }

    public void setParent(QueryNode parent) {
        this.parent = parent;
    }

    public String getTop_Edge() {
        return top_Edge;
    }

    public void setTop_Edge(String top_Edge) {
        this.top_Edge = top_Edge;
    }
    
    
}
