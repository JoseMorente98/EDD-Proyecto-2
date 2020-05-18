/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import org.josemorente.bean.Categoria;
import org.josemorente.bean.Obra;
import org.josemorente.bean.Usuario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author josem
 */
public class JSONControlador {
    private JSONObject jsonBody;
    private JSONObject jsonObject;
    private JSONArray jSONArray;
    
    private JSONControlador() {
    }
    
    public static JSONControlador getInstance() {
        return JSONControladorHolder.INSTANCE;
    }
    
    private static class JSONControladorHolder {

        private static final JSONControlador INSTANCE = new JSONControlador();
    }
    
    public void generarJSON(String tipo, String opcion, Object object) {
        switch(tipo) {
            case "Usuario":
                Usuario usuario = (Usuario)object; 
                this.getJsonBody().put("Carnet", usuario.getCarnet());
                this.getJsonBody().put("Nombre", usuario.getNombre());
                this.getJsonBody().put("Apellido", usuario.getApellido());
                this.getJsonBody().put("Carrera", usuario.getCarrera());
                this.getJsonBody().put("Password", MD5Controlador.getInstance().Encriptar(usuario.getPassword()));
                switch(opcion) {
                    case "1": 
                        this.getJsonObject().put("CREAR_USUARIO", this.getJsonBody());
                        this.getjSONArray().add(this.getJsonObject());
                        break;
                    case "2": 
                        this.getJsonObject().put("EDITAR_USUARIO", this.getJsonBody());
                        this.getjSONArray().add(this.getJsonObject());
                        break;
                }
                break;
            case "Categoria":
                Categoria categoria = (Categoria)object; 
                this.getJsonBody().put("Nombre", categoria.getNombre());
                switch(opcion) {
                    case "1": 
                        this.getJsonObject().put("CREAR_CATEGORIA", this.getJsonBody());
                        this.getjSONArray().add(this.getJsonObject());
                        break;
                    case "0": 
                        this.getJsonObject().put("ELIMINAR_CATEGORIA", this.getJsonBody());
                        this.getjSONArray().add(this.getJsonObject());
                        break;
                }
                break;
            case "Libro":
                Obra obra = (Obra)object; 
                this.getJsonBody().put("ISBN", obra.getISBN());
                this.getJsonBody().put("Año", obra.getAno());
                this.getJsonBody().put("Idioma", obra.getIdioma());
                this.getJsonBody().put("Titulo", obra.getTitulo());
                this.getJsonBody().put("Editorial", obra.getEditorial());
                this.getJsonBody().put("Autor", obra.getAutor());
                this.getJsonBody().put("Edicion", obra.getEdicion());
                this.getJsonBody().put("Categoria", obra.getCategoria());
                switch(opcion) {
                    case "1": 
                        this.getJsonObject().put("CREAR_LIBRO", this.getJsonBody());
                        this.getjSONArray().add(this.getJsonObject());
                        break;
                    case "0": 
                        this.getJsonObject().put("ELIMINAR_LIBRO", this.getJsonBody());
                        this.getjSONArray().add(this.getJsonObject());
                        break;
                }
                break;
        }
    }
    
    /**
     * @return the jsonBody
     */
    public JSONObject getJsonBody() {
        return jsonBody;
    }

    /**
     * @param jsonBody the jsonBody to set
     */
    public void setJsonBody(JSONObject jsonBody) {
        this.jsonBody = jsonBody;
    }

    /**
     * @return the jsonObject
     */
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    /**
     * @param jsonObject the jsonObject to set
     */
    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * @return the jSONArray
     */
    public JSONArray getjSONArray() {
        return jSONArray;
    }

    /**
     * @param jSONArray the jSONArray to set
     */
    public void setjSONArray(JSONArray jSONArray) {
        this.jSONArray = jSONArray;
    }
}
