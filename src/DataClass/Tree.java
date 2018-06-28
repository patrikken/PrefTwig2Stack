/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
 
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

    public ArrayList<DocElement> getChildsElement() {
        ArrayList<DocElement> toRet = new ArrayList<>();
        childs.forEach((t) -> {
            toRet.add(t.getRoot());
        });
        return toRet;
    }

    public void addChild(Tree t) {
        childs.add(t);
    }

    public void setChilds(ArrayList<Tree> childs) {
        this.childs = childs;
    }

    public Tree(DocElement root) {
        this.root = root;
        this.childs = new ArrayList<>();
    }

    @Override
    public String toString() {
        String s = "";
        s = root.toString() + "(" + root.getLeftPos() + "," + root.getRigthPos() + ")" + "[";
        for (Tree t : childs) {
            s += t.toString();
        }
        s += "]";
        return s;
    }

    public ArrayList<DocElement> getElements() {
        ArrayList<DocElement> toRet = new ArrayList<>();
        toRet.add(root);
        for (Tree t : childs) {
            toRet.addAll(t.getElements());
        }
        return toRet;
    }

    public boolean isEmpty() {
        return root == null && childs.isEmpty();
    }

    public ArrayList<Map<String, String>> toTuples(String key) {
        ArrayList<Map<String, String>> toRet = new ArrayList<>();
        Map<String, String> ht = new HashMap<>();
        ht.put(key, root.print());
        toRet.add(ht);
        for (Tree t : childs) {
            toRet.addAll(t.toTuples(key));
        }
        return toRet;
    }

}
