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
public class HierachicalStack {

    private ArrayList<StackTree> stackTrees;

    // private ArrayList<HierachicalStack> childs;
    private ArrayList<Axis<HierachicalStack>> childs;

    private String name;

    public HierachicalStack(String name) {
        stackTrees = new ArrayList<>();
        childs = new ArrayList<>();
        this.name = name;
    }

    public HierachicalStack() {
        stackTrees = new ArrayList<>();
        childs = new ArrayList<>();
        this.name = "";
    }

    public HierachicalStack getMatchingQueryNode(String query_node) {
        if (name.equals(query_node)) {
            return this;
        }
        if (childs.isEmpty()) {
            return null;
        }
        for (Axis<HierachicalStack> ax : childs) {
            HierachicalStack hs = ax.getTarget().getMatchingQueryNode(query_node);
            if (hs != null) {
                return hs;
            }
        }
        return null;
    }

    public ArrayList<StackTree> getStackTrees() {
        return stackTrees;
    }

    public ArrayList<Axis<HierachicalStack>> getChilds() {
        return childs;
    }

    public void setStackTrees(ArrayList<StackTree> stackTrees) {
        this.stackTrees = stackTrees;
    }

    public void setChilds(ArrayList<Axis<HierachicalStack>> childs) {
        this.childs = childs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void removeStackTree(StackTree st) {
        this.stackTrees.remove(st);
    }

    public void addStackTree(StackTree st) {
        
        this.stackTrees.add(0, st);
    }

    @Override
    public String toString() {
        String s = "\n HS["+name+"] : ";
        for (StackTree st : stackTrees) {
            s += st.toString() + "|\n";
        }
        for(Axis<HierachicalStack> ax:childs){
            s += ax.getTarget().toString();
           // s += "Parent : "+ " HS["+name+"] : "; 
        }
        return s; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addChildsHS(HierachicalStack child, String node_type){
        Axis<HierachicalStack> axis = new Axis<>(node_type,child);
        childs.add(axis);
    }

    public void push(DocElement e) {
        StackTree st = null;
        for (StackTree s : stackTrees) {
            if (e.getLeftPos() < s.getLefPos() && s.getRigthPos() < e.getRigthPos()) {
                st = s;
                break;
            }
        }
        if(st == null){
            st = new StackTree();
            st.setLefPos(e.getLeftPos());
            st.setRigthPos(e.getRigthPos());
            st.setLevel(e.getLevel());
            st.getRacine().push(e);
            addStackTree(st); 
        }else{
            st.setLefPos(e.getLeftPos());
            st.setRigthPos(e.getRigthPos());
            st.setLevel(e.getLevel());
            st.getRacine().push(e);  
        }
    }

    public String getName() {
        return name;
    }
    
    public StackTree getStackTreeContaint(DocElement e){
        StackTree toRet = null;
        for(StackTree st:stackTrees){
            toRet = st.getSTContaints(e);
            if(toRet != null) break;
        }
        
        return toRet;
    }
    
    public SOT makeSOT(){
        SOT sot = new SOT();
         for(StackTree st:stackTrees){
             sot.addTree(st.toTree(0));
         }
         return sot;
    }

}
