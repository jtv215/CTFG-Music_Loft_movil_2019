
package com.jefferson.musicloft.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSignup {

    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("contrasena")
    @Expose
    private String contrasena;
    @SerializedName("sexo")
    @Expose
    private String sexo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequestSignup() {
    }

    /**
     * 
     * @param nombre
     * @param sexo
     * @param contrasena
     * @param correo
     */
    public RequestSignup(String nombre, String correo, String contrasena, String sexo) {
        super();
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
