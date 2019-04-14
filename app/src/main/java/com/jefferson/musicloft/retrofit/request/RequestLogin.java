
package com.jefferson.musicloft.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestLogin {

    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("contrasena")
    @Expose
    private String contrasena;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequestLogin() {
    }

    /**
     * 
     * @param contrasena
     * @param correo
     */
    public RequestLogin(String correo, String contrasena) {
        super();
        this.correo = correo;
        this.contrasena = contrasena;
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

}
