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

    private DocElement element;

    protected StackTree stackTree;

    protected int index;

    private String hsName;

    public ChildResults(DocElement target, String hsName) {
        this.element = target;
        this.hsName = hsName;
        this.stackTree = null;
    }

    public ChildResults(StackTree stackTree, String hsName) {
        this.stackTree = stackTree;
        this.hsName = hsName;
        this.element = null;
    }

    public DocElement getElement() {
        return element;
    }

    public void setElement(DocElement element) {
        this.element = element;
    }

    public String getHsName() {
        return hsName;
    }

    public void setHsName(String hsName) {
        this.hsName = hsName;
    }

    public StackTree getStackTree() {
        return stackTree;
    }

    public String getType() {
        String toRet = "";
        if (element == null) {
            toRet = "Element NULL";
        }
        if (stackTree == null) {
            toRet += "ST NULL";
        }
        return toRet;
    }

}
