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
public class GTPResult {

    private ArrayList<Map<String, String>> result;

    public GTPResult() {
        this.result = new ArrayList<>();
    }

    public void addTupe(String key, String val) {
        Map<String, String> ht = new HashMap<>();
        ht.put(key, val);
        result.add(ht);
    }

    public void addTuple(Map<String, String> ht) {
        result.add(ht);
    }

    public void addTuples(ArrayList<Map<String, String>> tuples) {
        result.addAll(tuples);
    }

    public void setColumn(String key, String val) {
        if (result.isEmpty()) {
            addTupe(key, val);
            return;
        }
        for (Map<String, String> tuple : result) {
            tuple.put(key, val);
        }
    }

    public ArrayList<Map<String, String>> getTupes() {
        return result;
    }

    public void cartesianProduct(GTPResult gTPResult) { 
        if (!this.result.isEmpty()) {
            GTPResult toRet = new GTPResult();
            for (Map<String, String> tuple1 : result) {
                for (Map<String, String> tuple2 : gTPResult.getResult()) {
                    tuple1.putAll(tuple2);
                    //toRet.addTuple(tuple1);
                }

            }
            //this.addTuples(toRet.getResult());
            //System.out.println("Ici là return " + gTPResult);
        } else {
            result.addAll(gTPResult.getTupes()); 
            //System.out.println("Ici return " + gTPResult);
        }
    }

    @Override
    public String toString() {
        String toRet = "";
        for (Map<String, String> tuple : result) {
            toRet += "[";
            for (Map.Entry<String, String> entry : tuple.entrySet()) {
                toRet += "(" + entry.getKey() + "," + entry.getValue() + ")";
            }
            toRet += "],";
        }
        return toRet; //To change body of generated methods, choose Tools | Templates.
    }
    
    public String printTuples() {
        String toRet = "";
        for (Map<String, String> tuple : result) {
            toRet += "(";
            for (Map.Entry<String, String> entry : tuple.entrySet()) {
                toRet += entry.getValue() +",";
            }
            toRet += ")\n";
        }
        return toRet; //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Map<String, String>> getResult() {
        return result;
    }

}
