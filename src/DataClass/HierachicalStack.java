/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataClass;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author patrik
 */
public class HierachicalStack {

    private ArrayList<StackTree> stackTrees;

    // private ArrayList<HierachicalStack> childs;
    private ArrayList<Axis<HierachicalStack>> childs;

    private String name;

    private boolean isReturnNode;

    private ArrayList<String> contraints;

    ;

    public HierachicalStack(String name) {
        stackTrees = new ArrayList<>();
        childs = new ArrayList<>();
        this.name = name;
        isReturnNode = false;
        contraints = new ArrayList<>();
    }

    public HierachicalStack(String name, boolean isReturnNode) {
        this.stackTrees = new ArrayList<>();
        this.childs = new ArrayList<>();
        this.name = name;
        this.isReturnNode = isReturnNode;
        contraints = new ArrayList<>();
    }

    public HierachicalStack() {
        stackTrees = new ArrayList<>();
        childs = new ArrayList<>();
        this.name = "";
        contraints = new ArrayList<>();
    }

    public HierachicalStack getMatchingQueryNode(String query_node) {
        if (name.equals(query_node)) {
            return this;
        }
        /* if (name.equalsIgnoreCase("" + query_node.charAt(0))) {
            return this;
        }*/
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

    public HierachicalStack getMatchingQueryNode2(String query_tag, String query_value) {
        /*if (name.equals(query_node)) {
            return this;
        }*/ 
        if (name.equalsIgnoreCase("" + query_tag.charAt(0))) {
            if (!this.contraints.isEmpty()) { 
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                boolean b;
                for (String t : this.contraints) {
                    String toEval = query_value + t;
                    System.out.println("Contrainte : " + t);
                    if (query_value.equals("")) {
                        return null;
                    }

                    try {
                        b = (boolean) engine.eval(toEval);
                        if (!b) {
                            return null;
                        }
                    } catch (ScriptException ex) {
                        Logger.getLogger(HierachicalStack.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                }

            }
            return this;
        }
        if (childs.isEmpty()) {
            return null;
        }
        for (Axis<HierachicalStack> ax : childs) {
            HierachicalStack hs = ax.getTarget().getMatchingQueryNode2(query_tag, query_value);
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

    public void removeStackTree(int i) {
        this.stackTrees.remove(i);
    }

    public void addStackTree(StackTree st) {

        this.stackTrees.add(0, st);
    }

    @Override
    public String toString() {
        String s = "\n HS[" + name + "] : ";
        for (StackTree st : stackTrees) {
            s += st.toString() + "|\n";
        }
        for (Axis<HierachicalStack> ax : childs) {
            s += ax.getTarget().toString();
            // s += "Parent : "+ " HS["+name+"] : "; 
        }
        return s; //To change body of generated methods, choose Tools | Templates.
    }

    public String printTop() {
        String s = "\n HS[" + name + "] : ";
        for (StackTree st : stackTrees) {
            s += st.toString() + "|\n";
        }
        return s; //To change body of generated methods, choose Tools | Templates.
    }

    public String printWithPref() {
        String s = "\n HS[" + name + "] : ";
        for (StackTree st : stackTrees) {
            s += st.toString() + "|\n";
        }
        for (Axis<HierachicalStack> ax : childs) {
            s += ax.getTarget().toString();
            // s += "Parent : "+ " HS["+name+"] : "; 
        }
        return s; //To change body of generated methods, choose Tools | Templates.
    }

    public void addChildsHS(HierachicalStack child, String node_type) {
        Axis<HierachicalStack> axis = new Axis<>(node_type, child);
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
        if (st == null) {
            st = new StackTree();
            st.setLefPos(e.getLeftPos());
            st.setRigthPos(e.getRigthPos());
            st.setLevel(e.getLevel());
            st.getRacine().push(e);
            addStackTree(st);
        } else {
            st.setLefPos(e.getLeftPos());
            st.setRigthPos(e.getRigthPos());
            st.setLevel(e.getLevel());
            st.getRacine().push(e);
        }
    }

    public String getName() {
        return name;
    }

    public StackTree getStackTreeContaint(DocElement e) {
        StackTree toRet = null;
        for (StackTree st : stackTrees) {
            toRet = st.getSTContaints(e);
            if (toRet != null) {
                break;
            }
        }

        return toRet;
    }

    public SOT makeSOT() {
        SOT sot = new SOT();
        for (StackTree st : stackTrees) {
            sot.addTree(st.toSOT(0));
        }
        return sot;
    }

    public boolean isReturnNode() {
        return isReturnNode;
    }

    public void setIsReturnNode(boolean isReturnNode) {
        this.isReturnNode = isReturnNode;
    }

    public boolean hasReturnNodeBelow() {
        //if(isReturnNode) return true;
        if (childs.isEmpty()) {
            return false;
        }
        boolean toRet = false;
        for (Axis<HierachicalStack> ax : childs) {
            HierachicalStack target = ax.getTarget();
            if (target.isReturnNode()) {
                toRet = true;
                break;
            } else {
                toRet = ax.getTarget().hasReturnNodeBelow();
            }
        }
        return toRet;
    }

    public boolean isExistingCheckinNode() {
        if (!isReturnNode && !hasReturnNodeBelow()) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getContraint() {
        return contraints;
    }

    public void setContraint(ArrayList<String> contraint) {
        this.contraints = contraint;
    }

    public void addContraint(String contraint) {
        this.contraints.add(contraint);
    }

    public boolean isLeaf() {
        return childs.isEmpty();
    }

}
