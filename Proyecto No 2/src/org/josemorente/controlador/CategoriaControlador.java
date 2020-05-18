/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.josemorente.bean.Categoria;
import org.josemorente.bean.Obra;
import org.josemorente.bean.Usuario;
import static org.josemorente.controlador.UsuarioControlador.M;
import org.josemorente.interfaces.Comparador;

/**
 *
 * @author josem
 */
public class CategoriaControlador {
    private Categoria raiz;
    private int idCategoria;
    private ObservableList<Categoria> observableListCategoria;
    private ObservableList<Obra> observableListObra;
    
    private CategoriaControlador() {
        observableListCategoria = FXCollections.observableArrayList();
        observableListObra = FXCollections.observableArrayList();
        raiz = null;
        idCategoria = 0;
    }
    
    public static CategoriaControlador getInstance() {
        return CategoriaControladorHolder.INSTANCE;
    }
    
    private static class CategoriaControladorHolder {
        private static final CategoriaControlador INSTANCE = new CategoriaControlador();
    }

    public Categoria getRaiz() {
        return raiz;
    }

    private Categoria rotacionII(Categoria n, Categoria n1) {
        n.setIzquierda(n1.getDerecha());
        n1.setDerecha(n);
        // actualización de los factores de equilibrio
        if (n1.getFactorEquilibrio() == -1) {// se cumple en la inserción
            n.setFactorEquilibrio(0);
            n1.setFactorEquilibrio(0);
        }
        else {
            n.setFactorEquilibrio(-1);
            n1.setFactorEquilibrio(1);
        }
        return n1;
    }
    
    private Categoria rotacionDD(Categoria n, Categoria n1) {
        n.setDerecha(n1.getIzquierda());
        n1.setIzquierda(n);
        // actualización de los factores de equilibrio
        if (n1.getFactorEquilibrio() == +1) {// se cumple en la inserción
            n.setFactorEquilibrio(0);
            n1.setFactorEquilibrio(0);
        }
        else {
            n.setFactorEquilibrio(+1);
            n1.setFactorEquilibrio(-1);
        }
        return n1;
    }
    
    private Categoria rotacionID(Categoria n, Categoria n1) {
        Categoria n2;
        n2 = (Categoria) n1.getDerecha();
        n.setIzquierda(n2.getDerecha());
        n2.setDerecha(n);
        n1.setDerecha(n2.getIzquierda());
        n2.setIzquierda(n1);
        // actualización de los factores de equilibrio
        if (n2.getFactorEquilibrio() == +1) {
            n1.setFactorEquilibrio(-1);
        } else {
            n1.setFactorEquilibrio(0);
        }
            
        if (n2.getFactorEquilibrio() == -1) {
            n.setFactorEquilibrio(1);
        } else {
            n.setFactorEquilibrio(0);
        }            
        n2.setFactorEquilibrio(0);
        return n2;
    }
    
    private Categoria rotacionDI(Categoria n, Categoria n1) {
        Categoria n2;
        n2 = (Categoria)n1.getIzquierda();
        n.setDerecha(n2.getIzquierda());
        n2.setIzquierda(n);
        n1.setIzquierda(n2.getDerecha());
        n2.setDerecha(n1);
        // actualización de los factores de equilibrio
        if (n2.getFactorEquilibrio() == +1) {
            n.setFactorEquilibrio(-1);
        } else {
            n.setFactorEquilibrio(0);
        }
        if (n2.getFactorEquilibrio() == -1) {
            n1.setFactorEquilibrio(1);
        } else {
            n1.setFactorEquilibrio(0);
        }
        n2.setFactorEquilibrio(0);
        return n2;
    }
    
    public void agregar(int carnetUsuario, String nombre) {
        idCategoria++;
        Logical h = new Logical(false);
        raiz = insertarAvl(raiz, carnetUsuario, nombre, h);
    }
    
    private Categoria insertarAvl(Categoria raiz, int carnetUsuario, String nombre, Logical h)
    {
        Categoria n1;
        if (raiz == null) {
            raiz = new Categoria(idCategoria, carnetUsuario, nombre);
            h.setLogical(true);
        } else if (nombre.compareTo(raiz.getNombre()) < 0) {
            Categoria iz;
            iz = insertarAvl((Categoria) raiz.getIzquierda(), carnetUsuario, nombre, h);
            raiz.setIzquierda(iz);
            // regreso por los nodos del camino de búsqueda
            if (h.booleanValue()) {
            // decrementa el fe por aumentar la altura de rama izquierda
                switch (raiz.getFactorEquilibrio())
                {
                    case 1:
                        raiz.setFactorEquilibrio(0);
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.setFactorEquilibrio(-1);
                        break;
                    case -1: // aplicar rotación a la izquierda
                        n1 = (Categoria)raiz.getIzquierda();
                        if (n1.getFactorEquilibrio() == -1)
                            raiz = rotacionII(raiz, n1);
                        else
                            raiz = rotacionID(raiz, n1);
                        h.setLogical(false);
                }
            }
        } else if (nombre.compareTo(raiz.getNombre()) > 0) {
            Categoria dr;
            dr = insertarAvl((Categoria) raiz.getDerecha(), carnetUsuario, nombre, h);
            raiz.setDerecha(dr);
            // regreso por los nodos del camino de búsqueda
            if (h.booleanValue()) {
            // decrementa el fe por aumentar la altura de rama izquierda
                switch (raiz.getFactorEquilibrio())
                {
                    case 1:
                        n1 = (Categoria)raiz.getDerecha();
                        if (n1.getFactorEquilibrio() == +1)
                            raiz = rotacionDD(raiz, n1);
                        else
                            raiz = rotacionDI(raiz, n1);
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.setFactorEquilibrio(+1);
                        break;
                    case -1: // aplicar rotación a la izquierda
                        raiz.setFactorEquilibrio(0);
                        h.setLogical(false);
                }
            }
        } else {
            System.out.println("No se puede ingresar repetidos.");
        }
        return raiz;
    }
    
    public void eliminar (String categoria) throws Exception
    {
        Logical flag = new Logical(false);
        raiz = borrarAvl(raiz, categoria, flag);
    }
    
    private Categoria borrarAvl(Categoria r, String clave, Logical cambiaAltura) throws Exception {
        //System.out.println("BUSCANDO" + clave);
        ///System.out.println("RAIZ"+ r.getNombre());
        //System.out.println(clave+ "==" + r.getNombre() +(clave.compareTo(raiz.getNombre()) < 0));
        //System.out.println(clave+ "==" + r.getNombre() + (clave.compareTo(raiz.getNombre()) > 0));
        if (r == null)
        {
            System.out.println("NO ENCONTRADO D:");
        }
        else if (clave.compareTo(r.getNombre()) < 0)
        {
            Categoria iz;
            iz = borrarAvl((Categoria)r.getIzquierda(), clave, cambiaAltura);
            r.setIzquierda(iz);
            if (cambiaAltura.booleanValue()) {
                r = equilibrar1(r, cambiaAltura);
            }
        } else if (clave.compareTo(r.getNombre()) > 0) {
            Categoria dr;
            dr = borrarAvl((Categoria)r.getDerecha(), clave, cambiaAltura);
            r.setDerecha(dr);
            if (cambiaAltura.booleanValue()) {
                r = equilibrar2(r, cambiaAltura);
            }
        } else {// Nodo encontrado
            Categoria q;
            q = r; // nodo a quitar del árbol
            if (q.getIzquierda()== null) {
                r = (Categoria) q.getDerecha();
                cambiaAltura.setLogical(true);
            } else if (q.getDerecha()== null) {
                r = (Categoria) q.getIzquierda();
                cambiaAltura.setLogical(true);
            } else {
                // tiene rama izquierda y derecha
                Categoria iz;
                iz = reemplazar(r, (Categoria)r.getIzquierda(), cambiaAltura);
                r.setIzquierda(iz);
                if (cambiaAltura.booleanValue()) {
                    r = equilibrar1(r, cambiaAltura);
                }
            }
            q = null;
        }
        return r;
    }
    
    public void actualizar(String nombre, int carnet, String nombreActualizado) throws Exception {
        this.eliminar(nombre);
        this.agregar(carnet, nombreActualizado);
    }
    
    private Categoria reemplazar(Categoria n, Categoria act, Logical cambiaAltura)
    {
        if (act.getDerecha()!= null)
        {
            Categoria d;
            d = reemplazar(n, (Categoria)act.getDerecha(), cambiaAltura);
            act.setDerecha(d);
            if (cambiaAltura.booleanValue()) {
                act = equilibrar2(act, cambiaAltura);
            }
        } else {
            n.setCarnetUsuario(act.getCarnetUsuario());
            n.setNombre(act.getNombre());
            n = act;
            act = (Categoria)act.getIzquierda();
            n = null;
            cambiaAltura.setLogical(true);
        }
        return act;
    } 
    
    private Categoria equilibrar1(Categoria n, Logical cambiaAltura)
    {
        Categoria n1;
        switch (n.getFactorEquilibrio())
        {
            case -1:
                n.setFactorEquilibrio(0);
                break;
            case 0: 
                n.setFactorEquilibrio(1);
                cambiaAltura.setLogical(false);
                break;
            case +1 : //se aplicar un tipo de rotación derecha
                n1 = (Categoria)n.getDerecha();
                if (n1.getFactorEquilibrio() >= 0) {
                    if (n1.getFactorEquilibrio() == 0) { //la altura no vuelve a disminuir
                        cambiaAltura.setLogical(false);
                    }
                    n = rotacionDD(n, n1);
                }
                else{
                    n = rotacionDI(n, n1);
                }
                break;
        }
        return n;
    }
    
    private Categoria equilibrar2(Categoria n, Logical cambiaAltura){
        Categoria n1;
        switch (n.getFactorEquilibrio()) {
            case -1: // Se aplica un tipo de rotación izquierda
                n1 = (Categoria)n.getIzquierda();
                if (n1.getFactorEquilibrio() <= 0) {
                    if (n1.getFactorEquilibrio() == 0) {
                        cambiaAltura.setLogical(false);
                    }
                    n = rotacionII(n, n1);
                } else {
                    n = rotacionID(n,n1);
                }
                break;
            case 0:
                n.setFactorEquilibrio(-1);
                cambiaAltura.setLogical(false);
                break;
            case +1:
                n.setFactorEquilibrio(0);
                break;
        }
        return n;
    }
    
    public void inOrder() {
        this.inOrder(raiz);
    }
    
    public void posOrder() {
        this.posOrder(raiz);
    }
    
    public void preOrder() {
        this.preOrder(raiz);
    }
    
    public void inOrder(Categoria categoria) {
        if (categoria != null) {
            inOrder(categoria.getIzquierda());
	    System.out.println(categoria);
            inOrder(categoria.getDerecha());
	}
    }
    
    public void posOrder(Categoria categoria) {
        if (categoria != null) {
            posOrder(categoria.getIzquierda());
            posOrder(categoria.getDerecha());
	    System.out.println(categoria);            
	}
    }
    
    public void preOrder(Categoria categoria) {
        if (categoria != null) {
            System.out.println(categoria);
            preOrder(categoria.getIzquierda());
            preOrder(categoria.getDerecha());
	}
    }
    
    public Categoria buscar(String nombre) {
        return buscar(nombre, raiz);
    }
    
    public Categoria buscar(String nombre, Categoria categoriaAux) {
        if(categoriaAux != null) {
            if(categoriaAux.getNombre().equals(nombre)) {
                System.out.println("ENCONTRAD"+nombre);
                return categoriaAux;
            } else {
                buscar(nombre, categoriaAux.getIzquierda());
                buscar(nombre, categoriaAux.getDerecha());
            }
        }
        return null;
    }
    
    public void agregarLibro(String categoria, Obra obra){
        //System.out.println("BUSCANDO AGREGAR: " + categoria);
        this.agregarLibro(categoria, obra, this.raiz);
    }
    
    public void agregarLibro(String categoria, Obra obra, Categoria nodoCategoria) {
        if(nodoCategoria != null) {
            //System.out.println("BUSCANDO AGREGAR: " + categoria);
            if(nodoCategoria.getNombre().equals(categoria)) {
                //System.out.println("ENCONTRADO: " + categoria);
                nodoCategoria.getEscritoControlador().agregar(obra.getISBN(), obra);
                //nodoCategoria.getLibroControlador().agregar(obra.getISBN(), obra);
            } else {
                this.agregarLibro(categoria, obra, nodoCategoria.getIzquierda());
                this.agregarLibro(categoria, obra, nodoCategoria.getDerecha());
            }
        }
    }
    
    public boolean buscarLibro(int ISBN) {
        return buscarLibro(ISBN, this.raiz);
    }
    
    public boolean buscarLibro(int ISBN, Categoria categoria) {
        if (categoria != null) {
            if (categoria.getEscritoControlador().getRaiz() != null) {
                if (categoria.getEscritoControlador().getRaiz().searchKey(M)) {
                    return true;
                }
            } 
            if (buscarLibro(ISBN, categoria.getIzquierda())) {
                return true;
            }
            if (buscarLibro(ISBN, categoria.getDerecha())) {
                return true;
            }
        }
        return false; 
    }
    
    public void eliminarLibro(int ISBN, String nombre) {
        eliminarLibro(ISBN, nombre, this.raiz);
    }
    
    public void eliminarLibro(int ISBN, String nombre, Categoria categoria) {
        System.out.println("BUSCAR: "+nombre );
        if(categoria != null) {
            if(categoria.getNombre().equals(nombre)) {
                if(categoria.getEscritoControlador().getRaiz() != null) {
                    System.out.println("ISBN: " + ISBN);
                    categoria.getEscritoControlador().eliminar(ISBN);
                }
            } else {
                System.out.println("SI ENTRO AL ELSE");
                eliminarLibro(ISBN, nombre, categoria.getIzquierda());
                eliminarLibro(ISBN, nombre, categoria.getDerecha());
            }
        }
    }
        
    public void inOrderCategoria(Categoria categoria) {
        if (categoria != null) {
            inOrderCategoria(categoria.getIzquierda());
            //System.out.println(categoria);
	    this.observableListCategoria.add(categoria);
            inOrderCategoria(categoria.getDerecha());
	}
    }   
    
    public void preOrderLibro(Categoria categoria){
        if (categoria != null) {
            //System.out.println("CATEGORIA"+ categoria);
            if (categoria.getEscritoControlador().getRaiz() != null) {
                //categoria.getLibroControlador().
                //ArrayList ar = node.getBtree().getRoot().getArray();
                categoria.getEscritoControlador().getRaiz().getArrayList().clear();
                categoria.getEscritoControlador().obtenerTodo();
                ArrayList<Obra> arrayList = categoria.getEscritoControlador().getRaiz().getArrayList();
                if (arrayList.size() > 0) {                    
                    for (Obra obra: arrayList) {
                        this.observableListObra.add(obra);
                        //if(!this.)
                        /*if (!this.tempBook.contains(object)) {
                            this.tempBook.add((Book)object);    
                        }*/
                    }
                }
            }
            preOrderLibro(categoria.getIzquierda());
            preOrderLibro(categoria.getDerecha());
            //getBObjects(x, node.getLeft());
            //getBObjects(x, node.getRight());

        } 
    }
    
    public void insertarLibro(int ISBN, String titulo, String autor, String editorial, String ano, String edicion, String categoria, String idioma, int carnetUsuario){
        if (buscar(categoria, this.raiz) == null) {
            this.agregar(carnetUsuario, categoria);
        }
        this.inOrder();
        Obra obra = new Obra(ISBN, titulo, autor, editorial, ano, edicion, categoria, idioma, carnetUsuario);
        this.agregarLibro(categoria, obra);
    }
    
    public void generarGraphvizLibro(String categoria){
        generarGraphvizLibro(categoria, this.raiz);
    }
    
    public void generarGraphvizLibro(String nombre, Categoria node){
        if (node != null)
	{
            if (node.getNombre().equals(nombre)) {
                if (node.getEscritoControlador().getRaiz() != null) {
                    //node.getLibroControlador().generarGraphviz(nombre);
                    node.getEscritoControlador().generarGraphviz(nombre);
                }
            } else {
                generarGraphvizLibro(nombre, node.getIzquierda());
                generarGraphvizLibro(nombre, node.getDerecha());
            }
		
	}
    }
    
    public void generarGraphviz() {
        String cuerpoGraphiz;
        
        cuerpoGraphiz = "digraph ArbolAVL {\n" +
        "\trankdir = TB; \n" + 
        "\tnode[shape = ellipse, fontcolor = black, style = filled, color = lightsteelblue1];\n" +
        "\tgraph[label = \"Arbol AVL\", labelloc = t, fontsize = 20];\n";
        

        cuerpoGraphiz = cuerpoGraphiz + this.raiz.getArbol()+"}";
        
        try {
            File file = new File("ArbolAVL.dot");
            if(file.exists() && !file.isDirectory()) { 
                file.delete();
            }
            File file2 = new File("ArbolAVL.png");
            if(file2.exists() && !file2.isDirectory()) { 
                file2.delete();
            }
            FileWriter fileWriter;
            fileWriter = new FileWriter("ArbolAVL.dot");
            fileWriter.write(cuerpoGraphiz);
            fileWriter.close();
            Runtime.getRuntime().exec("dot -Tjpg -o ArbolAVL.png ArbolAVL.dot");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    /**
     * @return the observableListCategoria
     */
    public ObservableList<Categoria> getObservableListCategoria() {
        observableListCategoria.clear();
        this.inOrderCategoria(this.raiz);
        return observableListCategoria;
    }

    /**
     * @param observableListCategoria the observableListCategoria to set
     */
    public void setObservableListCategoria(ObservableList<Categoria> observableListCategoria) {
        this.observableListCategoria = observableListCategoria;
    }

    /**
     * @return the observableListObra
     */
    public ObservableList<Obra> getObservableListObra() {
        observableListObra.clear();
        this.preOrderLibro(this.raiz);
        return observableListObra;
    }

    /**
     * @param observableListObra the observableListObra to set
     */
    public void setObservableListObra(ObservableList<Obra> observableListObra) {
        this.observableListObra = observableListObra;
    }
    
}

class Logical {
    boolean v;
    
    public Logical (boolean f)
    {
        v = f;
    }
    
    public void setLogical(boolean f)
    {
        v = f;
    }
    
    public boolean booleanValue()
    {
        return v;
    }
}
