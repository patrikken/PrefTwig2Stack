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
public class ChildResults {
    private StackTree stackTree;
    
    private int index;

    public ChildResults(StackTree stackTree, int index) {
        this.stackTree = stackTree;
        this.index = index;
    }
    
    

    public StackTree getStackTree() {
        return stackTree;
    }

    public void setStackTree(StackTree stackTree) {
        this.stackTree = stackTree;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    
}
