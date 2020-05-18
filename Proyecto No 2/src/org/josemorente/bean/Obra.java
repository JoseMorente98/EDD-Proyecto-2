/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.bean;

import java.io.Serializable;

/**
 *
 * @author josem
 */
public class Obra implements Serializable {
    private int ISBN;
    private String titulo;
    private String autor;
    private String editorial;
    private String ano;
    private String edicion;
    private String categoria;
    private String idioma;
    private int carnetUsuario;

    public Obra() {
    }

    public Obra(int ISBN, String titulo, String autor, String editorial, String ano, String edicion, String categoria, String idioma, int carnetUsuario) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.ano = ano;
        this.edicion = edicion;
        this.categoria = categoria;
        this.idioma = idioma;
        this.carnetUsuario = carnetUsuario;
    }
    
    public Obra(int ISBN, String categoria) {
        this.ISBN = ISBN;
        this.categoria = categoria;
    }

    /**
     * @return the ISBN
     */
    public int getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return the edicion
     */
    public String getEdicion() {
        return edicion;
    }

    /**
     * @param edicion the edicion to set
     */
    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * @return the carnetUsuario
     */
    public int getCarnetUsuario() {
        return carnetUsuario;
    }

    /**
     * @param carnetUsuario the carnetUsuario to set
     */
    public void setCarnetUsuario(int carnetUsuario) {
        this.carnetUsuario = carnetUsuario;
    }
    
    @Override
    public String toString() {
        return "LIBRO {\n"+
            "\tisbn: " + ISBN + "\n"+
            "\tusuario: " + carnetUsuario + "\n"+
            "\ttitulo: " + titulo + "\n"+
            "\tautor: " + autor + "\n"+
            "\teditorial: " + editorial + "\n"+
            "\taño: " + ano + "\n"+
            "\tedicion: " + edicion + "\n"+
            "\tcategoria: " + categoria + "\n"+
            "\tidioma: " + idioma + "\n"+
            "}";
    }  
    
}
