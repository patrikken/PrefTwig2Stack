/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preftwig2stack;

import DataClass.Axis;
import DataClass.ChildResults;
import DataClass.DocElement;
import DataClass.HierachicalStack;
import DataClass.SOT;
import DataClass.Stack;
import DataClass.StackTree;
import DataClass.Tree;
import DataClass.XmlDocument;
import Helpers.Constantes;
import Helpers.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import sun.util.logging.resources.logging;

/**
 *
 * @author patrik
 */
public abstract class MainFunctions {

    public boolean merge(HierachicalStack HS, DocElement e, String axis) {
        boolean satisfied = false;
        ArrayList<StackTree> STS = new ArrayList<>();
        for (StackTree st : HS.getStackTrees()) {
            //  System.out.println(st.getRigthPos()+" < "+e.getLeftPos());
            if (st.getRigthPos() < e.getLeftPos()) {
                break;
            }
            if (axis.equals(Constantes.PC_EDGE) && st.top().getLevel() == e.getLevel() + 1) {
                satisfied = true;
                e.addPCEdge(st.top(), HS.getName());
                //  System.out.println("AddPCEdge");
            } else if (axis.equals(Constantes.AD_EDGE)) {
                satisfied = true;
                e.addADEdge(st.top(), HS.getName());
                System.out.println("AddADEdge");
            } else if (axis.equals(Constantes.APN_EDGE) || axis.equals(Constantes.PP_EDGE)) {
                satisfied = true;
                e.addAPNEdge(st.top(), HS.getName());
            } else if ((axis.equals(Constantes.PN_EDGE) && st.top().getLevel() == e.getLevel() + 1)) {
                satisfied = true;
                e.addPNEdge(st.top(), HS.getName());
            }
            STS.add(st);
        }
        createMergedStackTree(HS, STS);
        if (!satisfied && (axis.equals(Constantes.PN_EDGE) || axis.equals(Constantes.APN_EDGE))) {
            satisfied = true;
            for (Iterator<Axis<HierachicalStack>> it = HS.getChilds().iterator(); !satisfied && it.hasNext();) {
                Axis<HierachicalStack> ax = it.next();
                satisfied = merge(ax.getTarget(), e, ax.getLabel());
            }
        }
        return satisfied;
    }

    public boolean Prefmerge(HierachicalStack HS, DocElement e, String axis) {
        boolean satisfied = false;
        ArrayList<StackTree> STS = new ArrayList<>();
        for (StackTree st : HS.getStackTrees()) {
            //  System.out.println(st.getRigthPos()+" < "+e.getLeftPos());
            if (st.getRigthPos() < e.getLeftPos()) {
                break;
            }
            if (axis.equals(Constantes.PC_EDGE) && st.top().getLevel() == e.getLevel() + 1) {
                satisfied = true;
                e.addPCEdge(st.top(), HS.getName());
                //  System.out.println("AddPCEdge");
            } else if (axis.equals(Constantes.AD_EDGE)) {
                satisfied = true;
                e.addADEdge(st.top(), HS.getName());
                System.out.println("AddADEdge");
            } else if (axis.equals(Constantes.APN_EDGE) || axis.equals(Constantes.PP_EDGE)) {
                satisfied = true;
                e.addAPNEdge(st.top(), HS.getName());
            } else if ((axis.equals(Constantes.PN_EDGE) && st.top().getLevel() == e.getLevel() + 1)) {
                satisfied = true;
                e.addPNEdge(st.top(), HS.getName());
            }
            STS.add(st);
        }
        createMergedStackTree(HS, STS);
        if (!satisfied && (axis.equals(Constantes.PN_EDGE) || axis.equals(Constantes.APN_EDGE))) {
            satisfied = true;
            for (Iterator<Axis<HierachicalStack>> it = HS.getChilds().iterator(); !satisfied && it.hasNext();) {
                Axis<HierachicalStack> ax = it.next();
                satisfied = merge(ax.getTarget(), e, ax.getLabel());
            }
        }
        return satisfied;
    }

    public void createMergedStackTree(HierachicalStack Hs, ArrayList<StackTree> STS) {
        if (STS.size() < 2) {
            return;
        }
        StackTree newst = new StackTree();
        int minPos = Integer.MAX_VALUE;
        int maxPos = Integer.MIN_VALUE;
        for (StackTree st : STS) {
            if (st.getLefPos() < minPos) {
                minPos = st.getLefPos();
            }
            if (st.getRigthPos() > maxPos) {
                maxPos = st.getRigthPos();
            }
            Hs.removeStackTree(st);
            newst.addChild(st);
        }
        newst.setLefPos(minPos);
        newst.setRigthPos(maxPos);
        Hs.addStackTree(newst);
    }

    ;
    
    public void Twig2Stack(Stack<DocElement> stack, HierachicalStack hs) {
        DocElement currentElem;
        HierachicalStack currentElemHS;
        while (!stack.isEmpty()) {
            currentElem = stack.pop();
            currentElemHS = hs.getMatchingQueryNode(currentElem.getTag());
            if (currentElemHS != null) {
                MatchOneNode(currentElem, currentElemHS);
            }
        }
    }

    public void MatchOneNode(DocElement currentElem, HierachicalStack HS) {
        boolean satisfied = true;
        for (Iterator<Axis<HierachicalStack>> it = HS.getChilds().iterator(); satisfied && it.hasNext();) {
            Axis<HierachicalStack> ax = it.next();
            satisfied = merge(ax.getTarget(), currentElem, ax.getLabel());
        }
        if (satisfied) {
            merge(HS, currentElem, "");
            HS.push(currentElem);
        }
    }

    /*
    * Computing PointPC Of And Element
     */
    public SOT pointPC(DocElement e, HierachicalStack Hs) {
        SOT sot = new SOT();
        String hsName;
        for (Axis<ChildResults> ax : e.getChildsResults()) {
            hsName = ax.getTarget().getHsName();
            if (Constantes.isPCedge(ax.getLabel()) && hsName.equals(Hs.getName())) {
                sot.addTree(new Tree(ax.getTarget().getElement()));
            }
        }
        return sot;
    }

    public SOT pointAD(DocElement e, HierachicalStack Hs) {
        SOT sot = new SOT();
        // DocElement target = e.getChildsResults();
        String hsName;
        for (Axis<ChildResults> ax : e.getChildsResults()) {
            hsName = ax.getTarget().getHsName();
            if (Constantes.isADedge(ax.getLabel()) && hsName.equals(Hs.getName())) {
                DocElement target = ax.getTarget().getElement();
                StackTree st = Hs.getStackTreeContaint(target);
                // Make Sot à partir de l'objet st et de l'index de e
                if (st != null) {
                    int i = st.indexOf(target);
                    if (i > -1) {
                        //  System.out.println("preftwig2stack.MainFunctions.pointAD() nothing");
                        sot = new SOT(st.toTree(i));
                    } else {
                        sot = null;
                    }
                }
            }
        }
        return sot;
    }

    /*
    * Compute total effect algorithme
     */
    public SOT computeTotalEffects(SOT eSOT, String axis, HierachicalStack hs) {
        SOT resultSOT = new SOT(), mSOT = new SOT(), subSOT = new SOT();
        ArrayList<DocElement> childElements;
        DocElement e, m;
        for (Tree t : eSOT.getTrees()) {
            if (Constantes.isADedge(axis)) {
                resultSOT.addTree(pointAD(t.getRoot(), hs));
            } else {
                mSOT = pointPC(t.getRoot(), hs);
                childElements = t.getChildsElement();
                while (!mSOT.isEmpty()) {
                    m = mSOT.next();
                    if (!childElements.isEmpty()) {
                        e = childElements.get(0);
                        while (!childElements.isEmpty() && e.getRigthPos() < m.getLeftPos()) {
                            e = childElements.get(0);
                            resultSOT.addTree(computeTotalEffects(new SOT(new Tree(e)), axis, hs));
                            childElements.remove(0);
                        }
                    }
                    subSOT = new SOT();
                    if (!childElements.isEmpty()) {
                        e = childElements.get(0);
                        while (!childElements.isEmpty() && m.getLeftPos() < e.getLeftPos() && m.getRigthPos() > e.getRigthPos()) {
                            e = childElements.get(0);
                            subSOT.addTree(computeTotalEffects(new SOT(new Tree(e)), axis, hs));
                            childElements.remove(0);
                        }
                    }
                    SOT parent = new SOT(new Tree(m));
                    parent.addTree(subSOT);
                    resultSOT.addTree(parent);
                }
                while (!childElements.isEmpty()) {
                    e = childElements.get(0);
                    resultSOT.addTree(computeTotalEffects(new SOT(new Tree(e)), axis, hs));
                    childElements.remove(0);
                }
            }
        }
        return resultSOT;
    }

}
