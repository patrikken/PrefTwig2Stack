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
public class Axis<T> {
    private String label;
    private T target;
    private boolean isOptional;

    public Axis(String label, T target) {
        this.label = label;
        this.target = target;
        isOptional = false;
    }

    public String getLabel() {
        return label;
    }

    public T getTarget() {
        return target;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public void setTarget(T target) {
        this.target = target;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void isOptional(boolean isOptional) {
        this.isOptional = isOptional;
    }
     
    
}
