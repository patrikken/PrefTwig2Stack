/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
 
/**
 *
 * @author patrik
 */
public class DocElementPref extends DocElement {

    private Map<String, Integer> prefTable;

    public DocElementPref(String tag) {
        super(tag);
        this.prefTable = new HashMap<>();
    }

    public DocElementPref() {
        this.prefTable = new HashMap<>();
    }

    DocElementPref(DocElement root) {
        super(root.getTag());
        super.setLeftPos(root.getLeftPos());
        super.setRigthPos(root.getRigthPos());
        super.setLevel(root.getLevel());
        super.setValue(root.getValue());
        this.prefTable = new HashMap<>();  
    }

    public void addPrefEntry(String key, int val) {
        prefTable.put(key, val);
    } 

    public void updatePreferenceTable(Map<String, Integer> table) {
        for (Map.Entry<String, Integer> entry : table.entrySet()) {
            if (this.prefTable.containsKey(entry.getKey())) {
                if (prefTable.get(entry.getKey()) > entry.getValue() && entry.getValue() > 0) {
                    prefTable.replace(entry.getKey(), entry.getValue());
                }
            } else {
                prefTable.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Map<String, Integer> getPrefTable() {
        return prefTable;
    }

    @Override
    public String toString() {
        return super.toString()+" "+prefTable;
    }
    
    public ArrayList<String> dim(){
        ArrayList<String> toRet = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : prefTable.entrySet()) {
            if (entry.getValue() == 1) {
               toRet.add(entry.getKey());
            }
        }
        return toRet;
    }

}
