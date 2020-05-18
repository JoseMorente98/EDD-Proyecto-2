/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.codec.binary.Hex;
import org.josemorente.bean.CadenaBloque;
import org.josemorente.bean.Cliente;
import org.josemorente.bean.Ordenador;

/**
 *
 * @author josem
 */
public class CadenaBloqueControlador {
    private CadenaBloque primero;
    private CadenaBloque ultimo;
    private static String hash;
    private static BigInteger nonce;
    private ObservableList<CadenaBloque> observableList;
    
    private CadenaBloqueControlador() {
        observableList = FXCollections.observableArrayList();
        this.primero = null;
        this.ultimo = null;
    }
    
    public static CadenaBloqueControlador getInstance() {
        return CadenaBloqueControladorHolder.INSTANCE;
    }
    
    private static class CadenaBloqueControladorHolder {
        private static final CadenaBloqueControlador INSTANCE = new CadenaBloqueControlador();
    }
    
    private boolean esVacio() {
        return primero == null;
    }
    
    public void agregar() {
        CadenaBloque cadenaBloque = new CadenaBloque(this.getFechaActual(), new ArrayList(), Cliente.getIp());
        String strHash = "";
        if (esVacio()) {
            primero = cadenaBloque;
            cadenaBloque.setPreviousHash("0000");
            cadenaBloque.setIndex(0);
        } else {
            cadenaBloque.setAnterior(ultimo);
            cadenaBloque.setIndex(cadenaBloque.getAnterior().getIndex() + 1);
            cadenaBloque.setPreviousHash(ultimo.getHash());
            ultimo.setSiguiente(cadenaBloque);
        }
        ultimo = cadenaBloque;
        strHash += cadenaBloque.getIndex();
        strHash += cadenaBloque.getTimeStamp();
        strHash += cadenaBloque.getPreviousHash();
        strHash += cadenaBloque.getData();
        strHash += cadenaBloque.getNonce();
        cadenaBloque.setHash(this.getHash(strHash));
        cadenaBloque.setNonce(this.getNonce().toString());
    }
    
    /**
     * @return the SHA
     */
    private static String getSHA(String input) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return new String(Hex.encodeHex(hash));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CadenaBloqueControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @return the hash
     */
    public String getHash(String data) {
        nonce = new BigInteger("0");
        String aux = getSHA(data);

        while (true) {
            if (aux.startsWith("0000")){
                break;
            }
            this.setNonce(nonce.add(BigInteger.ONE));
            aux = getSHA(data+nonce);
        }
        hash = aux;
        return hash;
    }

    /**
     * @param aHash the hash to set
     */
    public void setHash(String aHash) {
        hash = aHash;
    }

    /**
     * @return the nonce
     */
    public BigInteger getNonce() {
        return nonce;
    }

    /**
     * @param aNonce the nonce to set
     */
    public void setNonce(BigInteger aNonce) {
        nonce = aNonce;
    }
    
    /**
     * @return the date
     */
    public String getFechaActual() {
        String strFechaActual = "";
        LocalDateTime myDateObj = LocalDateTime.now();  
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yy::HH:mm:ss");  
        strFechaActual = myDateObj.format(myFormatObj);  
        return strFechaActual;
    }
    
    /**
     * @return graphviz 
     */
    public void generarGraphviz()  {
        String strGraphviz = "";
        int contador = 0;
        strGraphviz += "digraph Blockchain {\n" + "graph[label=\"Lista Doble Enlazada\", labelloc=t, fontsize=20, compound=true];";
        strGraphviz += "\nrankdir = LR;\nnode [shape=folder, fontcolor = black, style = filled, color = bisque3];\nsplines=false; ";
        
        CadenaBloque aux = primero;
        while(aux != null) {
            strGraphviz += "Blockchain" + contador + " [label =\"" + "INDEX: " + aux.getIndex()+ 
                    "\\nTIMESTAMP: "+aux.getTimeStamp()+
                    "\\nDATA: "+aux.getData() +
                    "\\nNONCE: "+aux.getNonce()+
                    "\\nPREVIOUSHASH: "+aux.getPreviousHash()+
                    "\\nHASH: "+aux.getHash()+
                    " \"]\n";
            aux = aux.getSiguiente();
            contador++;
        }
        
        for (int i = 0; i < contador - 1; i++)
	{
            strGraphviz += "Blockchain" + i + "->Blockchain" + (i+1) + "\n";
            strGraphviz += "Blockchain" + (i+1) + "->Blockchain" + i + "\n";
	}         
        strGraphviz += "}";
        
        File file = new File("ListaDoble.dot");
        if(file.exists() && !file.isDirectory()) {
            file.delete();
        }
        
        try {
            FileWriter fileWriter = new FileWriter("ListaDoble.dot");
            fileWriter.write(strGraphviz);
            fileWriter.close();
            Runtime.getRuntime().exec("dot -Tjpg -o ListaDoble.png ListaDoble.dot");
        } catch (IOException ex) {
            Logger.getLogger(OrdenadorControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the observableList
     */
    public ObservableList<CadenaBloque> getObservableList() {
        observableList.clear();
        CadenaBloque aux = primero;
        while(aux!=null) {
            observableList.add(aux);
            aux = aux.getSiguiente();
        }
        return observableList;
    }

    /**
     * @param observableList the observableList to set
     */
    public void setObservableList(ObservableList<CadenaBloque> observableList) {
        this.observableList = observableList;
    }
}
