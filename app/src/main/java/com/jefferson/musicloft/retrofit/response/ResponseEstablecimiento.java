
package com.jefferson.musicloft.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseEstablecimiento {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("contrasena")
    @Expose
    private String contrasena;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("informacion")
    @Expose
    private String informacion;
    @SerializedName("hombres")
    @Expose
    private String hombres;
    @SerializedName("mujeres")
    @Expose
    private String mujeres;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseEstablecimiento() {
    }

    /**
     * 
     * @param nombre
     * @param id
     * @param informacion
     * @param hombres
     * @param contrasena
     * @param correo
     * @param mujeres
     */
    public ResponseEstablecimiento(String id, String nombre, String contrasena, String correo, String informacion, String hombres, String mujeres) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.informacion = informacion;
        this.hombres = hombres;
        this.mujeres = mujeres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getHombres() {
        return hombres;
    }

    public void setHombres(String hombres) {
        this.hombres = hombres;
    }

    public String getMujeres() {
        return mujeres;
    }

    public void setMujeres(String mujeres) {
        this.mujeres = mujeres;
    }

}
