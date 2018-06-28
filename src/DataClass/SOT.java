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

    public SOT(Tree t) {
        this.trees = new ArrayList<>();
        this.trees.add(t);
    }

    public SOT(ArrayList<Tree> trees) {
        this.trees = trees;
    }

    public SOT(SOT sot) {
        this.trees = sot.getTrees();
    }

    public void addTree(Tree t) {
        trees.add(t);
    }

    public void addTree(SOT sot) {
        if (!sot.getTrees().isEmpty()) {
            trees.addAll(sot.getTrees());
        }
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }
    
    public ArrayList<DocElement> getElements(){
        ArrayList<DocElement> toRet = new ArrayList<>();
        for(Tree t:trees){
            toRet.addAll(t.getElements());
        }
        return toRet;
    }

    @Override
    public String toString() {
        String s = "";
        for (Tree t : trees) { 
            s += t.toString() + "|";
        }
        return s;
    }

    public boolean isEmpty() {
        return trees.isEmpty();
    }

    public DocElement next() {
        DocElement toRet = trees.get(0).getRoot();
        trees.remove(0);
        return toRet;
    }

    public GTPResult convert2Tuple(String key) {
        GTPResult toRet = new GTPResult();
        for (Tree t : trees) {
            toRet.addTuples(t.toTuples(key));
        }
        return toRet;
    }

}
