/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.josemorente.bean.Escrito;
import org.josemorente.bean.Obra;
import org.josemorente.bean.Ruptura;

/**
 *
 * @author josem
 */
public class EscritoControlador {
    private Escrito raiz = null;
    private int tamano = 2;
    private int altura = 0;
    private ArrayList arrayListLlave = new ArrayList();
    private ArrayList arrayListLibro = new ArrayList();
    
    /**
     * CONSTRUCTOR 
     */
    public EscritoControlador() {
    }
    
    /**
     * CONSTRUCTOR 
     */
    public EscritoControlador(int tamano) {
        this.tamano = tamano;
    }
    
    /**
     * CONSTRUCTOR 
     */
    public EscritoControlador(Escrito raiz) {
        this.tamano = raiz.getTamano();
        this.raiz = raiz;
        this.altura = 1;
    }
    
    public static EscritoControlador getInstance() {
        return EscritoControladorHolder.INSTANCE;
    }
    
    private static class EscritoControladorHolder {
        private static final EscritoControlador INSTANCE = new EscritoControlador();
    }
       
    /**
     * AGREGAR
     * @param contadorLlave to llave
     * @param objectLibro to libro
     */
    public void agregar(int contadorLlave, Object objectLibro) {
        getArrayListLibro().add(objectLibro);
        getArrayListLlave().add(contadorLlave);
        if (this.getAltura() == 0) {
            this.setRaiz(new Escrito(this.getTamano(), contadorLlave, objectLibro));
            this.setAltura(1);
            return;
        }

        Ruptura ruptura = agregar(this.getRaiz(), contadorLlave, objectLibro, 1);

        if (ruptura != null) {
            Escrito puntero = this.getRaiz();
            this.setRaiz(new Escrito(this.getTamano(), ruptura.getLlave(), ruptura.getObject()));
            this.raiz.punteros[0] = puntero;
            this.raiz.punteros[1] = ruptura.getPuntero();
            this.setAltura(this.getAltura() + 1);
        }
    }
    
    public Ruptura agregar(Escrito escrito, int ISBN, Object objectLibro, int nivelB) {
        Ruptura ruptura = null;
        Escrito puntero = null;
        int contadorLlave = 0;
        
        while ((contadorLlave < escrito.getHojas()) && (ISBN > escrito.llaves[contadorLlave]))
            contadorLlave++;

        if ((contadorLlave < escrito.getHojas()) && ISBN == escrito.llaves[contadorLlave]) {
            escrito.obras[contadorLlave] = objectLibro;
            return null;
        }

        if (nivelB < this.getAltura()) {

            ruptura = agregar(escrito.punteros[contadorLlave], ISBN, objectLibro, nivelB + 1);

            if (ruptura == null)
                return null;
            else {
                ISBN = ruptura.getLlave();
                objectLibro = ruptura.getObject();
                puntero = ruptura.getPuntero();
            }
        }

        contadorLlave = escrito.getHojas() - 1;
        while ((contadorLlave >= 0) && (escrito.llaves[contadorLlave] == 0 || ISBN < escrito.llaves[contadorLlave] )) {
            escrito.llaves[contadorLlave + 1] = escrito.llaves[contadorLlave];
            escrito.obras[contadorLlave + 1] = escrito.obras[contadorLlave];
            escrito.punteros[contadorLlave + 2] = escrito.punteros[contadorLlave + 1];
            contadorLlave--;
        }

        escrito.llaves[contadorLlave + 1] = ISBN;
        escrito.obras[contadorLlave + 1] = objectLibro;
        
        if (ruptura != null) {
            escrito.punteros[contadorLlave + 2] = ruptura.getPuntero();
        }
        escrito.setHojas(escrito.getHojas() + 1);

        if (escrito.getHojas() > 2 * getTamano()) {
            Escrito escritoNuevo = new Escrito(this.getTamano());
            escritoNuevo.punteros[this.getTamano()] = escrito.punteros[escrito.getHojas()];
            escrito.punteros[escrito.getHojas()] = null;
            escrito.setHojas(this.getTamano() + 1);
            
            for (contadorLlave = 0; contadorLlave < this.getTamano(); contadorLlave++) {
                escritoNuevo.llaves[contadorLlave] = escrito.llaves[contadorLlave + escrito.getHojas()];
                escrito.llaves[contadorLlave + escrito.getHojas()] = 0;
                escritoNuevo.obras[contadorLlave] = escrito.obras[contadorLlave + escrito.getHojas()];
                escrito.obras[contadorLlave + escrito.getHojas()] = null;
                escritoNuevo.punteros[contadorLlave] = escrito.punteros[contadorLlave + escrito.getHojas()];
                escrito.punteros[contadorLlave + escrito.getHojas()] = null;
            }
            
            escrito.setHojas(escrito.getHojas() - 1);
            ruptura = new Ruptura(escritoNuevo, escrito.llaves[escrito.getHojas()], escrito.obras[escrito.getHojas()]);
            escrito.llaves[escrito.getHojas()] = 0;
            escrito.obras[escrito.getHojas()] = null;
            escritoNuevo.setHojas(this.getTamano());
            escrito.setHojas(this.getTamano());
            return ruptura;
        }
        return null;
    }
     
    /**
     * ELIMINAR
     * @param isbn to isbn
     */
    public boolean eliminar(int isbn) {
        if (getArrayListLlave().contains(isbn)) {
            ArrayList temp = getArrayListLibro();
            setRaiz(null);
            setTamano(2);
            setAltura(0);
            setArrayListLlave(new ArrayList());
            setArrayListLibro(new ArrayList());
            
            for (Object object : temp) {
                Obra obra = (Obra)object;
                if (obra.getISBN() != isbn) {
                    agregar(obra.getISBN(), object);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Generar Grafico
     * @param ISBN 
     */
    public Object buscar(int ISBN) {
        return buscar(ISBN, getRaiz());
    }

    public Object buscar(int ISBN, Escrito escrito) {
        int contadorLlave = 0;
        if ((escrito == null) || (escrito.getHojas() < 1)) {
            return null;
        }

        if (escrito.llaves[0] > ISBN) {
            return this.buscar(ISBN, escrito.punteros[0]);
        }

        if (escrito.llaves[escrito.getHojas() - 1] < ISBN) {
            return this.buscar(ISBN, escrito.punteros[escrito.getHojas()]);
        }
        
        while ((ISBN > escrito.llaves[contadorLlave]) && (contadorLlave < escrito.getHojas() - 1)) {
            contadorLlave++;
        }

        if (escrito.llaves[contadorLlave] == ISBN) {
            return escrito.obras[contadorLlave];
        }
            
        return this.buscar(ISBN, escrito.punteros[contadorLlave]);
    }
    
    /**
     * OBTENER TODOS
     */
    public void obtenerTodo() {
        this.getRaiz().obtenerTodo();
    }
    
    /**
     * @param carnet to carnet
     */
    public void obtenerPorCarnet(int carnet) {
        this.getRaiz().obtenerPorCarnet(carnet);
    }
    
    /**
     * @param carnet to carnet
     */
    public boolean buscarPorCarnet(int carnet){
        return this.getRaiz().searchKey(carnet);
    }

    /**
     * Generar Grafico
     * @param nombre 
     */
    public void generarGraphviz(String nombre) {
        FileWriter fileWriter = null;
        String strGraphviz = "";
        strGraphviz += "\tdigraph g { \n graph[label=\"" + nombre  + "\", labelloc=t, fontsize=20, compound=true]; escrito [shape=record];\n";
        strGraphviz += getRaiz().toDot();
        strGraphviz += "}";
        try {
            fileWriter = new FileWriter("ArbolB.dot");
            fileWriter.write(strGraphviz);
            fileWriter.close();
            Thread.sleep(1000);
            Runtime.getRuntime().exec("dot -Tjpg -o ArbolB.png ArbolB.dot");
        } catch (IOException ex) {
            Logger.getLogger(EscritoControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(EscritoControlador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(EscritoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the raiz
     */
    public Escrito getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(Escrito raiz) {
        this.raiz = raiz;
    }

    /**
     * @return the tamano
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * @param tamano the tamano to set
     */
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    
    /**
     * @return the altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * @return the arrayListLlave
     */
    public ArrayList getArrayListLlave() {
        return arrayListLlave;
    }

    /**
     * @param arrayListLlave the arrayListLlave to set
     */
    public void setArrayListLlave(ArrayList arrayListLlave) {
        this.arrayListLlave = arrayListLlave;
    }

    /**
     * @return the arrayListLibro
     */
    public ArrayList getArrayListLibro() {
        return arrayListLibro;
    }

    /**
     * @param arrayListLibro the arrayListLibro to set
     */
    public void setArrayListLibro(ArrayList arrayListLibro) {
        this.arrayListLibro = arrayListLibro;
    }
    
}