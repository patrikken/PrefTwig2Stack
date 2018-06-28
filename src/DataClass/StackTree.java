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
        this.racine = new Stack<>();
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

    public void addChild(StackTree st) {
        childs.add(st);
    }

    public boolean isLeaf() {
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

    public DocElement top() {
        return racine.top();
    }

    public StackTree getSTContaints(DocElement e) {
        StackTree toRet = null;
        if (racine.containt(e)) {
            return this;
        }
        if (!isLeaf()) {
            for (StackTree st : childs) {
                toRet = st.getSTContaints(e);
                if (toRet != null) {
                    break;
                }
            }
        }
        return toRet;
    }

    public Tree toTree(int index) {
        DocElement elem = null;
        if (!racine.isEmpty()) {
            elem = racine.get(index);
        }
        Tree t = new Tree(elem);
        Tree t3, t4;
        t3 = t;
        for (int i = index + 1; i < racine.size(); i++) {
            t4 = new Tree(racine.get(i));
            t3.addChild(t4);
            t3 = t4;
        }
        for (StackTree st : childs) {
            t3.addChild(st.toTree(0));
        }

        return t;
    }

    public SOT toSOT(int index) {
        SOT sot = new SOT();
        Tree t = toTree(index); 
        if (t.getRoot() == null) {
            for (Tree tree : t.getChilds()) {
                if (!tree.isEmpty()) {
                    sot.addTree(tree);
                }
            }
        } else {
            sot = new SOT(t);
        }

        return sot;
    }

    public int indexOf(DocElement e) {
        return racine.getIndex(e);
    }

    @Override
    public String toString() {
        String s = "Stack [";
        for (DocElement e : racine.all()) {
            s += e.toString() + ",";
        }
        s += "]=>{";
        for (StackTree st : childs) {
            s += st.toString();
            s += ";";
        }
        s += "}";
        return s; //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isTopEmpty() {
        return racine.isEmpty();
    }

    public boolean isEmpty() {
        boolean b = true;
        if (!racine.isEmpty()) {
            return false;
        }
        for (StackTree st : childs) {
            b = st.isEmpty();
            if (!b) {
                break;
            }
        }
        return b;
    }

}
