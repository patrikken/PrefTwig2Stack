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
public class StackTree {
    
    private Stack<DocElement> racine;
    
    private ArrayList<StackTree> childs;
    
    private int lefPos;
    
    private int rigthPos;
    
    private int level;

    public StackTree() {
        this.racine = new  Stack<>();
        this.childs = new ArrayList<>();
    }

    public Stack<DocElement> getRacine() {
        return racine;
    }

    public void setRacine(Stack<DocElement> racine) {
        this.racine = racine;
    }

    public ArrayList<StackTree> getChilds() {
        return childs;
    }
    
    public void addChild(StackTree st){
        childs.add(st);
    }
    
    public boolean isLeaf(){
        return childs.isEmpty();
    }

    public void setChilds(ArrayList<StackTree> childs) {
        this.childs = childs;
    }

    public int getLefPos() {
        return lefPos;
    }

    public void setLefPos(int lefPos) {
        this.lefPos = lefPos;
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
    
    public DocElement top(){
        return racine.top();
    }
    
    public StackTree getSTWitchContaints(DocElement e){
        StackTree toRet = null;
        if(racine.containt(e))
            return this;
        if(!isLeaf())
            for(StackTree st:childs){
                st = st.getSTWitchContaints(e);
                    if(st != null) break;
            }
        return toRet;
    }

    @Override
    public String toString() {
        String s = "Stack [";
        for(DocElement e:racine.all()){
            s += e.toString()+",";
        }
        s+="]=>{";
        for(StackTree st:childs){ 
            s+=st.toString(); 
            s+=";";
        }
        s+="}";
        return s; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
