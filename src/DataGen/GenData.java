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

    public static Stack<DocElement> generateStack(int numberOfElement) {
        Stack<DocElement> st = new Stack<>();
        RandomString randomString = new RandomString(numberOfElement);
        for (int i = 0; i <= 3; i++) {
            st.push(new DocElement(randomString.nextString()));
        }
        return st;
    }

    public static StackTree generateStackTree(int depth) {
        if (depth == 0) {
            return null;
        }
        StackTree stackTree = new StackTree();
        stackTree.setRacine(generateStack(depth));
        stackTree.setLefPos(depth);
        stackTree.top().setLevel(depth);
        stackTree.setRigthPos(depth + 1);
        //stackTree.addChild(generateStackTree(depth--));
        /*  StackTree childstackTree = new StackTree();
        childstackTree.setRacine(generateStack(1)); 
        stackTree.addChild(childstackTree);*/
        return stackTree;
    }

    public static HierachicalStack generateHierachicalStack() {
        StackTree st1 = GenData.generateStackTree(2);
        StackTree st2 = GenData.generateStackTree(4);
        HierachicalStack Hs = new HierachicalStack("name");
        Hs.addStackTree(st2);
        Hs.addStackTree(st1);
        return Hs;
    }

    public static HierachicalStack generateHierachicalStackForEmpleDB() {
        HierachicalStack Hs = new HierachicalStack("personne", true);

        HierachicalStack childHS = new HierachicalStack("profile");
        Hs.addChildsHS(childHS, Constantes.AD_EDGE);

        HierachicalStack childHS3 = new HierachicalStack("nom", true);
       // childHS3.addContraint(" == 'Farel Ken' ");
        childHS.addChildsHS(childHS3, Constantes.PN_EDGE);

        HierachicalStack childHS2 = new HierachicalStack("competances",true);
        Hs.addChildsHS(childHS2, Constantes.PC_EDGE);

        HierachicalStack childHS4 = new HierachicalStack("PHP");
        childHS4.addContraint(" > 2 "); 
        childHS2.addChildsHS(childHS4, Constantes.PC_EDGE);
        HierachicalStack childHS5 = new HierachicalStack("java");
        childHS2.addChildsHS(childHS5, Constantes.PN_EDGE);
        return Hs;
    }

    public static HierachicalStack generateHierachicalStackForDataTestDB() {
        HierachicalStack Hs = new HierachicalStack("A", true);

        HierachicalStack childHS = new HierachicalStack("B", true);
        Hs.addChildsHS(childHS, Constantes.PC_EDGE);

        HierachicalStack childHS3 = new HierachicalStack("D");
        childHS.addChildsHS(childHS3, Constantes.AD_EDGE);

        HierachicalStack childHS2 = new HierachicalStack("C", true);
        childHS.addChildsHS(childHS2, Constantes.PC_EDGE);
        return Hs;
    }

    public static HierachicalStack generateHierachicalStackForDataTest2DB() {
        HierachicalStack Hs = new HierachicalStack("A",true);

        HierachicalStack childHS = new HierachicalStack("B", true);
        Hs.addChildsHS(childHS, Constantes.PC_EDGE);

        HierachicalStack childHS3 = new HierachicalStack("D");
        childHS.addChildsHS(childHS3, Constantes.PN_EDGE);

        HierachicalStack childHS31 = new HierachicalStack("F");
        childHS31.addContraint(" > 20 ");
        childHS3.addChildsHS(childHS31, Constantes.AD_EDGE);

        HierachicalStack childHS2 = new HierachicalStack("C", true);
        childHS2.addContraint(" == 'Cameroun' ");
        childHS.addChildsHS(childHS2, Constantes.PC_EDGE);

        HierachicalStack childHS21 = new HierachicalStack("E");
        childHS21.addContraint(" == 'Dschang'");
        childHS2.addChildsHS(childHS21, Constantes.APN_EDGE);
        return Hs;
    }

    public static HierachicalStack generateHierachicalStackForYahoo() {
        HierachicalStack Hs = new HierachicalStack("listing", true);

        HierachicalStack childHS1 = new HierachicalStack("seller_info", true);
        Hs.addChildsHS(childHS1, Constantes.PC_EDGE);

        HierachicalStack childHS3 = new HierachicalStack("seller_name");
        childHS1.addChildsHS(childHS3, Constantes.PC_EDGE);

        HierachicalStack childHS2 = new HierachicalStack("auction_info", true);
        Hs.addChildsHS(childHS2, Constantes.PC_EDGE);

        HierachicalStack childHS21 = new HierachicalStack("current_bid", true);
        childHS2.addChildsHS(childHS21, Constantes.AD_EDGE);
        /*HierachicalStack childHS21 = new HierachicalStack("VBN",true);
        childHS2.addChildsHS(childHS21, Constantes.PC_EDGE); */
        return Hs;
    }

    public static HierachicalStack generateHierachicalStackForSIGMOD() {
        HierachicalStack Hs = new HierachicalStack("issue", true);

        HierachicalStack childHS1 = new HierachicalStack("article", true);
        Hs.addChildsHS(childHS1, Constantes.AD_EDGE);

        HierachicalStack childHS3 = new HierachicalStack("title");
        childHS1.addChildsHS(childHS3, Constantes.PC_EDGE);

        HierachicalStack childHS2 = new HierachicalStack("volume", true);
        Hs.addChildsHS(childHS2, Constantes.PC_EDGE);

        /*HierachicalStack childHS21 = new HierachicalStack("VBN",true);
        childHS2.addChildsHS(childHS21, Constantes.PC_EDGE); */
        return Hs;
    }

    public static HierachicalStack generateHierachicalStackForTreeBank() {
        HierachicalStack Hs = new HierachicalStack("S", true);

        HierachicalStack childHS = new HierachicalStack("VP", true);
        Hs.addChildsHS(childHS, Constantes.PC_EDGE);

        HierachicalStack childHS3 = new HierachicalStack("PP", true);
        childHS.addChildsHS(childHS3, Constantes.PC_EDGE);
        HierachicalStack childHS31 = new HierachicalStack("IN", true);
        childHS3.addChildsHS(childHS31, Constantes.PC_EDGE);

        HierachicalStack childHS2 = new HierachicalStack("NP", true);
        childHS3.addChildsHS(childHS2, Constantes.PC_EDGE);
        HierachicalStack childHS21 = new HierachicalStack("VBN", true);
        childHS.addChildsHS(childHS21, Constantes.PC_EDGE);
        return Hs;
    }
}
