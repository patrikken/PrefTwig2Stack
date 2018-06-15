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
    
    public ArrayList<DocElement> getChildsElement() {
        ArrayList<DocElement> toRet = new ArrayList<>();
        childs.forEach((t) -> {
            toRet.add(t.getRoot());
        });
        return toRet;
    }
    
    public void addChild(Tree t){
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
        String s = root.toString() +"[";
        for(Tree t: childs){
            s+= t.toString();
        }
        s+="]";
        return s;
    }
     
}
