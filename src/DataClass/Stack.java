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
 * @param <T>
 */
public class Stack<T> {
    private final ArrayList<T> list;
    
    public Stack() {
        this.list = new ArrayList<>();
    }
    
    public void push(T element){
        list.add(0, element);
    }
    
    public T pop(){
        T toRet = list.get(0);
        list.remove(0);
        return toRet;
    }
    
    public T get(int index){
        T toRet = list.get(index); 
        return toRet;
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    public T top(){
        return list.get(0);
    }
    
    public int size(){
        return list.size();
    }
    
    public boolean containt(T t){
        return list.contains(t);
    }
    
    public int getIndex(T t){
        return list.indexOf(t);
    }
    
    // Need to be remove after
    public ArrayList<T> all(){
        return list;
    }

    public void removeAll() {
        list.clear();
    }
     
     
}
