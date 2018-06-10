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
public class SOT {
    ArrayList<Tree> trees;

    public SOT() {
        this.trees = new ArrayList<>();
    }
    
    public void addTree(Tree t){
        trees.add(t);
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }
    
    
    
}
