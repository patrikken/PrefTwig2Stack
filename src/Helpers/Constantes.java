/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

/**
 *
 * @author patrik
 */
public abstract class Constantes {
    public final static String PC_EDGE = "PC"; 
    public final static String AD_EDGE = "AD"; 
    public static String PN_EDGE = "PN";
    public static String APN_EDGE = "APN";
    public static String PP_EDGE = "PP";
    public static boolean  isADedge(String axis){
         return (axis.equals(AD_EDGE) || axis.equals(APN_EDGE) || axis.equals(PP_EDGE));
     }
     
    public static boolean isPCedge(String axis){
         return (axis.equals(PC_EDGE) || axis.equals(PN_EDGE));
     }
    public static boolean isPreference(String axis){
        return (axis.equals(PN_EDGE) || axis.equals(APN_EDGE) || axis.equals(PP_EDGE));
    }
}
