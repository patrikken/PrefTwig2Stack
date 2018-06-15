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
    
    private String hsName;

    public ChildResults(DocElement target, String hsName) {
        this.element = target;
        this.hsName = hsName;
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
    
    
 
    
}
