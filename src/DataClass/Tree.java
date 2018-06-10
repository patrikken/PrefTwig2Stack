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
public class Tree {
    private DocElement root;

    private ArrayList<Tree> childs;

    public DocElement getRoot() {
        return root;
    }

    public void setRoot(DocElement root) {
        this.root = root;
    }

    public ArrayList<Tree> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Tree> childs) {
        this.childs = childs;
    }

    public Tree(DocElement root) {
        this.root = root;
        this.childs = new ArrayList<>();
    }
    
    
}
