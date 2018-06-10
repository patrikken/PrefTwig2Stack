/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataGen;

import DataClass.DocElement;
import DataClass.HierachicalStack;
import DataClass.Stack; 
import DataClass.StackTree;
import Helpers.Constantes;

/**
 *
 * @author patrik
 */
public class GenData {
    public static Stack<DocElement> generateStack(int numberOfElement){
        Stack<DocElement> st = new Stack<>();
        RandomString randomString = new RandomString(numberOfElement);
        for(int i=0; i<=3; i++){
            st.push(new DocElement(randomString.nextString()));
        }
        return st;
    } 
    
    public static StackTree generateStackTree(int depth){
        if(depth == 0)
            return null;
        StackTree stackTree = new StackTree();
        stackTree.setRacine(generateStack(depth));
        stackTree.setLefPos(depth); 
        stackTree.top().setLevel(depth);
        stackTree.setRigthPos(depth+1);  
        //stackTree.addChild(generateStackTree(depth--));
      /*  StackTree childstackTree = new StackTree();
        childstackTree.setRacine(generateStack(1)); 
        stackTree.addChild(childstackTree);*/
        return stackTree;
    }
    
    public static HierachicalStack generateHierachicalStack(){
        StackTree st1 = GenData.generateStackTree(2);        
        StackTree st2 = GenData.generateStackTree(4); 
        HierachicalStack Hs = new HierachicalStack("name");
        Hs.addStackTree(st2);        
        Hs.addStackTree(st1);
        return Hs;
    }
    
    public static HierachicalStack generateHierachicalStackForEmpleDB(){ 
        HierachicalStack Hs = new HierachicalStack("personne");
        
        HierachicalStack childHS = new HierachicalStack("profile");
        Hs.addChildsHS(childHS, Constantes.APN_EDGE); 
        
        HierachicalStack childHS3 = new HierachicalStack("nom");
        childHS.addChildsHS(childHS3, Constantes.AD_EDGE); 
        
        HierachicalStack childHS2 = new HierachicalStack("competances");
        Hs.addChildsHS(childHS2, Constantes.PC_EDGE);
        
        HierachicalStack childHS4 = new HierachicalStack("PHP");
        childHS2.addChildsHS(childHS4, Constantes.PN_EDGE);
        return Hs;
    }
}
