/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.josemorente.bean.Libro;
import org.josemorente.bean.Obra;
import org.josemorente.bean.Separador;

/**
 *
 * @author josem
 */
public class LibroControlador {
    private Libro raiz = null;
    private int tamano = 2;
    private int altura = 0;
    private ArrayList arrayListLlave = new ArrayList();
    private ArrayList<Obra> arrayListLibros = new ArrayList();
    
    public LibroControlador() {
    }
    
    public LibroControlador(int tamano) {
        this.tamano = tamano;
    }
    
    public LibroControlador(Libro raiz) {
        this.tamano = raiz.getTamano();
        this.raiz = raiz;
        this.altura = 1;
    }
    
    public static LibroControlador getInstance() {
        return LibroControladorHolder.INSTANCE;
    }
    
    private static class LibroControladorHolder {
        private static final LibroControlador INSTANCE = new LibroControlador();
    }
    
    public void agregar(int i, Obra obra) {
        this.arrayListLibros.add(obra);
        this.arrayListLlave.add(i);
        
        if (this.altura == 0) {
            this.raiz = new Libro(this.getTamano(), i, obra);
            this.altura = 1;
            return;
        }
        
        Separador separador = this.agregar(this.raiz, i, obra, 1); 
        
        if (separador != null) {
            Libro libro = this.raiz;
            this.raiz = new Libro(this.getTamano(), separador.getLlave(), separador.getObra());
            this.raiz.punteros[0] = libro;
            this.raiz.punteros[1] = separador.getPuntero();
            this.altura++;
        }
    }
    
   /* public void insert(int i, Object obj) {
        dataList.add(obj);
        keysList.add(i);
        if (this.rootHeight == 0) {
            this.root = new BTreeNode(this.mK, i, obj);
            this.rootHeight = 1;
            return;
        }

        Split splitted = insert(this.root, i, obj, 1);

        if (splitted != null) {
            BTreeNode ptr = this.root;
            this.root = new BTreeNode(this.mK, splitted.getKey(), splitted.getData());
            this.root.pointers[0] = ptr;
            this.root.pointers[1] = splitted.getPointer();
            this.rootHeight++;
        }
    }
    */
    /*


        if (node.mB > 2 * mK) {

            BTreeNode newnode = new BTreeNode(this.mK);
            newnode.pointers[this.mK] = node.pointers[node.mB];
            node.pointers[node.mB] = null;
            node.mB = this.mK + 1;
            for (i = 0; i < this.mK; i++) {
                newnode.keys[i] = node.keys[i + node.mB];
                node.keys[i + node.mB] = 0;
                newnode.datas[i] = node.datas[i + node.mB];
                node.datas[i + node.mB] = null;
                newnode.pointers[i] = node.pointers[i + node.mB];
                node.pointers[i + node.mB] = null;
            }
            node.mB--;

            splitted = new Split(newnode, node.keys[node.mB], node.datas[node.mB]);
            node.keys[node.mB] = 0;
            node.datas[node.mB] = null;
            newnode.mB = this.mK;
            node.mB = this.mK;

            return splitted;
        }

        return null;
    }
*/
    
    public Separador agregar(Libro libro, int llave, Obra obra, int nivel) {
        Separador separador = null;
        Libro libroAux = null;
        
        int i = 0;
        while ((i < libro.getHojas()) && (llave > libro.llave[i])) {
            i++;
        }
            
        if ((i < libro.getHojas()) && llave == libro.llave[i]) {
            libro.libro[i] = obra;
            return null;
        }

        if (nivel < this.getAltura()) {
            separador = agregar(libro.punteros[i], llave, obra, nivel + 1);
            if (separador == null)
                return null;
            else {
                llave = separador.getLlave();
                obra = separador.getObra();
                libroAux = separador.getPuntero();
            }
        }
        i = libro.getHojas() - 1;
        while ((i >= 0) && (libro.llave[i] == 0 || llave < libro.llave[i] )) {
            libro.llave[i + 1] = libro.llave[i];
            libro.libro[i + 1] = libro.libro[i];
            libro.punteros[i + 2] = libro.punteros[i + 1];
            i--;
        }
                
        libro.llave[i + 1] = llave;
        libro.libro[i + 1] = obra;
        if (separador != null) {
            libro.punteros[i + 2] = separador.getPuntero();
        }
        libro.hojas++;

        
        if (libro.getHojas() > 2 * this.getTamano()) {

            Libro newnode = new Libro(this.getTamano());
            newnode.punteros[this.getTamano()] = libro.punteros[libro.getHojas()];
            libro.punteros[libro.getHojas()] = null;
            libro.hojas = this.getAltura()+1;
            libro.setHojas(this.getTamano() + 1);
            for (i = 0; i < this.getTamano(); i++) {
                newnode.llave[i] = libro.llave[i + libro.getHojas()];
                libro.llave[i + libro.getHojas()] = 0;
                newnode.libro[i] = libro.libro[i + libro.getHojas()];
                libro.libro[i + libro.getHojas()] = null;
                newnode.punteros[i] = libro.punteros[i + libro.getHojas()];
                libro.punteros[i + libro.getHojas()] = null;
            }
            
            separador = new Separador(newnode, libro.llave[libro.getHojas()], libro.libro[libro.getHojas()]);
            libro.llave[libro.getHojas()] = 0;
            libro.libro[libro.getHojas()] = null;
            newnode.setHojas(this.getTamano());
            libro.setHojas(this.getTamano());
            return separador;
        }
        return null;
    }
        
    
    public boolean eliminar(int ISBN) {
        if (this.arrayListLlave.contains(ISBN)) {
            ArrayList<Obra> arrayList = this.arrayListLibros;
            this.raiz = null;
            this.tamano = 2;
            this.altura = 0;
            this.arrayListLlave.clear();
            this.arrayListLibros.clear();
            
            for (Obra obra : arrayList) {
                if(obra.getISBN()!= ISBN)
                    agregar(obra.getISBN(), obra);
            }
            return true;
        }
        return false;
    }
    
    public Obra buscar(int llave) {
        return buscar(llave, this.raiz);
    }

    public Obra buscar(int llave, Libro libro) {
        if ((libro == null) || (libro.getHojas() < 1)) {
            System.err.println("error");
            return null;
        }

        if (llave < libro.llave[0]) {
            return buscar(llave, libro.punteros[0]);
        }

        if (llave > libro.llave[libro.getHojas() - 1]) {
            return buscar(llave, libro.punteros[libro.getHojas()]);
        }

        int i = 0;
        while ((i < libro.getHojas() - 1) && (llave > libro.llave[i])) {
            i++;
        }

        if (llave == libro.llave[i]) {
            return libro.libro[i];
        }
            
        return buscar(llave, libro.punteros[i]);
    }
    
    /**
     * @return the raiz
     */
    public Libro getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(Libro raiz) {
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
     * @return the arrayListLibros
     */
    public ArrayList getArrayListLibros() {
        return arrayListLibros;
    }

    /**
     * @param arrayListLibros the arrayListLibros to set
     */
    public void setArrayListLibros(ArrayList arrayListLibros) {
        this.arrayListLibros = arrayListLibros;
    }
    
    public String getPunto(String categoria) {
        String strGraphviz = "";
        strGraphviz += "digraph ArbolB { \n graph[label=\"" + "Arbol B Categoria: " + categoria  + "\", labelloc=t, fontsize=20, compound=true]; libro [shape=record];\n";
        //b.append("digraph g { \n graph[label=\"" + "Arbol B Categoria: " + categoria  + "\", labelloc=t, fontsize=20, compound=true]; libro [shape=record];\n");
        //b.append(raiz.getPunto());
        ///b.append("}");
        strGraphviz += raiz.getPunto();
        strGraphviz += "}";
        return strGraphviz;
    }
    
    public void generarGraphviz(String categoria) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("LibroArbolAVL.dot");
            fileWriter.write(this.getPunto(categoria));
            fileWriter.close();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Runtime.getRuntime().exec("dot -Tjpg -o LibroArbolAVL.png LibroArbolAVL.dot");
    }
    
}
