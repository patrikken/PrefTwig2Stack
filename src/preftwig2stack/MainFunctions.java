/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preftwig2stack;

import DataClass.Axis;
import DataClass.DocElement;
import DataClass.HierachicalStack;
import DataClass.SOT;
import DataClass.Stack;
import DataClass.StackTree;
import DataClass.Tree;
import DataClass.XmlDocument;
import Helpers.Constantes;
import java.util.ArrayList;
import java.util.Iterator;
import sun.util.logging.resources.logging;

/**
 *
 * @author patrik
 */
public abstract class MainFunctions {
    public boolean merge(HierachicalStack HS, DocElement e, String axis){
        boolean satisfied = false;
        ArrayList<StackTree> STS = new ArrayList<>(); 
        for(StackTree st:HS.getStackTrees()){
          //  System.out.println(st.getRigthPos()+" < "+e.getLeftPos());
           if(st.getRigthPos() < e.getLeftPos()){ 
               break;
           } 
           if(axis.equals(Constantes.PC_EDGE) && st.top().getLevel() == e.getLevel() + 1){
               satisfied = true;
               e.addPCEdge(st.top());
             //  System.out.println("AddPCEdge");
           }else if(axis.equals(Constantes.AD_EDGE)){
               satisfied = true;
               e.addADEdge(st.top());
               System.out.println("AddADEdge");
        }else if(axis.equals(Constantes.APN_EDGE) ||  axis.equals(Constantes.PP_EDGE)){
              satisfied = true;
              e.addAPNEdge(st.top());
        }else if((axis.equals(Constantes.PN_EDGE) && st.top().getLevel() == e.getLevel() +1)){
              satisfied = true;
              e.addPNEdge(st.top());
        }
           STS.add(st);
        }
        createMergedStackTree(HS,STS);
        if(!satisfied && (axis.equals(Constantes.PN_EDGE) || axis.equals(Constantes.APN_EDGE))){
            satisfied = true;
            for (Iterator<Axis<HierachicalStack>> it = HS.getChilds().iterator(); !satisfied && it.hasNext();) {
                Axis<HierachicalStack> ax = it.next();
                satisfied = merge(ax.getTarget(), e, ax.getLabel()); 
            }
        }
        return satisfied;
    }
    
    
    public boolean Prefmerge(HierachicalStack HS, DocElement e, String axis){
        boolean satisfied = false;
        ArrayList<StackTree> STS = new ArrayList<>(); 
        for(StackTree st:HS.getStackTrees()){
          //  System.out.println(st.getRigthPos()+" < "+e.getLeftPos());
           if(st.getRigthPos() < e.getLeftPos()){ 
               break;
           } 
           if(axis.equals(Constantes.PC_EDGE) && st.top().getLevel() == e.getLevel() + 1){
               satisfied = true;
               e.addPCEdge(st.top());
             //  System.out.println("AddPCEdge");
           }else if(axis.equals(Constantes.AD_EDGE)){
               satisfied = true;
               e.addADEdge(st.top());
               System.out.println("AddADEdge");
        }else if(axis.equals(Constantes.APN_EDGE) ||  axis.equals(Constantes.PP_EDGE)){
              satisfied = true;
              e.addAPNEdge(st.top());
        }else if((axis.equals(Constantes.PN_EDGE) && st.top().getLevel() == e.getLevel() +1)){
              satisfied = true;
              e.addPNEdge(st.top());
        }
           STS.add(st);
        }
        createMergedStackTree(HS,STS);
        if(!satisfied && (axis.equals(Constantes.PN_EDGE) || axis.equals(Constantes.APN_EDGE))){
            satisfied = true;
            for (Iterator<Axis<HierachicalStack>> it = HS.getChilds().iterator(); !satisfied && it.hasNext();) {
                Axis<HierachicalStack> ax = it.next();
                satisfied = merge(ax.getTarget(), e, ax.getLabel()); 
            }
        }
        return satisfied;
    }
    
    public void createMergedStackTree(HierachicalStack Hs, ArrayList<StackTree> STS){
        if(STS.size()<2)
            return;
        StackTree newst = new StackTree();
        int minPos = Integer.MIN_VALUE;
        int maxPos = Integer.MAX_VALUE;
        for(StackTree st:STS){  
            if(st.getLefPos() < minPos) minPos = st.getLefPos();
            if(st.getRigthPos() > maxPos) maxPos = st.getRigthPos();
            Hs.removeStackTree(st);
            newst.addChild(st);
        }
        Hs.addStackTree(newst);
    };
    
    public void Twig2Stack(Stack<DocElement> stack, HierachicalStack hs){
        DocElement currentElem;
        HierachicalStack currentElemHS;
        while(!stack.isEmpty()){
            currentElem = stack.pop();
            currentElemHS = hs.getMatchingQueryNode(currentElem.getTag());
            if(currentElemHS != null){
                MatchOneNode(currentElem,currentElemHS);
            }
        }
    }

    public void MatchOneNode(DocElement currentElem, HierachicalStack HS) {
        boolean satisfied = true;
        for (Iterator<Axis<HierachicalStack>> it = HS.getChilds().iterator(); satisfied && it.hasNext();) {
                Axis<HierachicalStack> ax = it.next();
                satisfied = merge(ax.getTarget(), currentElem, ax.getLabel()); 
            }
        if(satisfied){
            merge(HS, currentElem, "");
            HS.push(currentElem);
        }
    }
    
    public SOT pointPC(DocElement e,  HierachicalStack Hs){
        SOT sot = new SOT();
        for(Axis<DocElement> ax:e.getChildsResults()){
            if(ax.getLabel().equals(Constantes.PC_EDGE)){
                sot.addTree(new Tree(ax.getTarget()));
            }
        }
        return sot;
    }
    
    public SOT pointAD(DocElement e,  HierachicalStack Hs){
        SOT sot = new SOT();
        DocElement target = e.getChildsResults();
        for(Axis<DocElement> ax:e.getChildsResults()){
            if(ax.getLabel().equals(Constantes.AD_EDGE)){
                sot.addTree(new Tree(ax.getTarget()));
            }
        }
        return sot;
    }
    
}
