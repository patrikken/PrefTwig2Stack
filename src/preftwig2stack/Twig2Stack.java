/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preftwig2stack;

import DataClass.Axis;
import DataClass.ChildResults;
import DataClass.DocElement;
import DataClass.GTPResult;
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
public class Twig2Stack {

    public boolean merge(HierachicalStack HS, DocElement e, String axis) {
        boolean satisfied = false;
        ArrayList<StackTree> STS = new ArrayList<>();
        for (StackTree st : HS.getStackTrees()) {
            //  System.out.println(st.getRigthPos()+" < "+e.getLeftPos());
            if (st.getRigthPos() < e.getLeftPos()) {
                break;
            }
            if (axis.equals(Constantes.PC_EDGE) && st.getLevel() == e.getLevel() + 1) {
                satisfied = true;
                if (!st.isTopEmpty()) {
                    e.addPCEdge(st.top(), HS.getName());
                } else {
                    e.addPCEdge(st, HS.getName());
                }
                //  System.out.println("AddPCEdge");
            } else if (axis.equals(Constantes.AD_EDGE)) {
                satisfied = true;
                if (!st.isTopEmpty()) {
                    e.addADEdge(st.top(), HS.getName());
                } else {
                    e.addADEdge(st, HS.getName());
                }
                //  System.out.println("AddADEdge");
            }
            STS.add(st);
        }
        createMergedStackTree(HS, STS);
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
    
    public void twig2Stack(Stack<DocElement> stack, HierachicalStack hs) {
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
            //  System.out.println("pointPC() off " + e.print() + " In " + ax.getTarget().getElement().print() + " = " + sot);
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
                DocElement etarget = ax.getTarget().getElement();
                if (etarget != null) {
                    StackTree st = Hs.getStackTreeContaint(etarget);
                    // Make Sot à partir de l'objet st et de l'index de e
                    if (st != null) {
                        int i = st.indexOf(etarget);
                        if (i > -1) {
                            //  System.out.println("preftwig2stack.MainFunctions.pointAD() nothing"); 
                            sot.addTree(st.toSOT(i));
                        } else {
                            sot = null;
                        }
                    }
                } else {
                    StackTree stTarget = ax.getTarget().getStackTree();
                    // System.out.println("Pointe sur stack = " + stTarget.toSOT(0));
                    sot.addTree(stTarget.toSOT(0));
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
        //  System.out.println("--------ToTal Effect of " + eSOT + " In " + hs.getName());
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
                    for (int i = 0; i < childElements.size(); i++) {
                        e = childElements.get(i);
                        if (e.getRigthPos() < m.getLeftPos()) {
                            childElements.remove(i);
                            resultSOT.addTree(computeTotalEffects(new SOT(new Tree(e)), axis, hs));
                        }
                    }
                    subSOT = new SOT();
                    for (int i = 0; i < childElements.size(); i++) {
                        e = childElements.get(i);
                        if (m.getLeftPos() < e.getLeftPos() && m.getRigthPos() > e.getRigthPos()) {
                            childElements.remove(i);
                            subSOT.addTree(computeTotalEffects(new SOT(new Tree(e)), axis, hs));
                        }
                    }
                    Tree paren = new Tree(m);
                    paren.setChilds(subSOT.getTrees());
                    resultSOT.addTree(paren);
                }

                for (int i = 0; i < childElements.size(); i++) {
                    e = childElements.get(i);
                    //  System.out.println("*** Trosieme While true e: = " + e.print());
                    childElements.remove(i);
                    resultSOT.addTree(computeTotalEffects(new SOT(new Tree(e)), axis, hs));
                }
            }
        }
        //System.out.println("--------Result Effect of " + eSOT + " In " + hs.getName() + " = " + resultSOT);
        return resultSOT;
    }

    public GTPResult enumTiwg2Stack(HierachicalStack hs, SOT eSOT) {
        GTPResult totalResult = new GTPResult(), branchResult;
        SOT mSOT;
        if (!hs.hasReturnNodeBelow()) {
            // Convert all elment in SOT with key HS name
            return eSOT.convert2Tuple(hs.getName());
        } else {
            if (hs.isReturnNode()) {
                for (DocElement e : eSOT.getElements()) {
                    branchResult = new GTPResult();
                    for (Axis<HierachicalStack> ax : hs.getChilds()) {
                        HierachicalStack childHS = ax.getTarget();
                        if (!childHS.isExistingCheckinNode()) {
                            mSOT = computeTotalEffects(new SOT(new Tree(e)), ax.getLabel(), childHS);
                            GTPResult iterm = enumTiwg2Stack(childHS, mSOT);
                            branchResult.cartesianProduct(iterm);
                        }
                    }
                    branchResult.setColumn(hs.getName(), e.print());
                    totalResult.addTuples(branchResult.getTupes());
                }
                return totalResult;
            } else {
                for (Axis<HierachicalStack> ax : hs.getChilds()) {
                    HierachicalStack childHS = ax.getTarget();
                    if (!childHS.isExistingCheckinNode()) {
                        mSOT = computeTotalEffects(eSOT, ax.getLabel(), childHS);
                        return enumTiwg2Stack(childHS, mSOT);
                    }
                }
            }
        }
        //  System.out.println("----- +Total from catesian result "+totalResult);
        return totalResult;
    }

    public GTPResult enumTiwg2Stacklast(HierachicalStack hs, SOT eSOT) {
        GTPResult totalResult = new GTPResult(), branchResult;
        SOT mSOT;
        if (!hs.hasReturnNodeBelow()) {
            // Convert all elment in SOT with key HS name
            return eSOT.convert2Tuple(hs.getName());
        } else {
            if (hs.isReturnNode()) {
                for (DocElement e : eSOT.getElements()) {
                    branchResult = new GTPResult();
                    for (Axis<HierachicalStack> ax : hs.getChilds()) {
                        HierachicalStack childHS = ax.getTarget();
                        if (!childHS.isExistingCheckinNode()) {
                            mSOT = computeTotalEffects(new SOT(new Tree(e)), ax.getLabel(), childHS);
                            GTPResult iterm = enumTiwg2Stack(childHS, mSOT);
                            branchResult.cartesianProduct(iterm);
                        }
                    }
                    branchResult.setColumn(hs.getName(), e.print());
                    totalResult.addTuples(branchResult.getTupes());
                }
                return totalResult;
            } else {
                for (Axis<HierachicalStack> ax : hs.getChilds()) {
                    HierachicalStack childHS = ax.getTarget();
                    if (!childHS.isExistingCheckinNode()) {
                        mSOT = computeTotalEffects(eSOT, ax.getLabel(), childHS);
                        return enumTiwg2Stack(childHS, mSOT);
                    }
                }
            }
        }
        //  System.out.println("----- +Total from catesian result "+totalResult);
        return totalResult;
    }
}
