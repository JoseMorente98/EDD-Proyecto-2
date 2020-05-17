/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.bean;

/**
 *
 * @author josem
 */
public class Cliente {
    private static String ip;
    private static String ipServidor;
    private static String puerto;
    private static String puertoServidor;

    /**
     * @return the ip
     */
    public static String getIp() {
        return ip;
    }

    /**
     * @param aIp the ip to set
     */
    public static void setIp(String aIp) {
        ip = aIp;
    }

    /**
     * @return the ipServidor
     */
    public static String getIpServidor() {
        return ipServidor;
    }

    /**
     * @param aIpServidor the ipServidor to set
     */
    public static void setIpServidor(String aIpServidor) {
        ipServidor = aIpServidor;
    }

    /**
     * @return the puerto
     */
    public static String getPuerto() {
        return puerto;
    }

    /**
     * @param aPuerto the puerto to set
     */
    public static void setPuerto(String aPuerto) {
        puerto = aPuerto;
    }

    /**
     * @return the puertoServidor
     */
    public static String getPuertoServidor() {
        return puertoServidor;
    }

    /**
     * @param aPuertoServidor the puertoServidor to set
     */
    public static void setPuertoServidor(String aPuertoServidor) {
        puertoServidor = aPuertoServidor;
    }
    
}
