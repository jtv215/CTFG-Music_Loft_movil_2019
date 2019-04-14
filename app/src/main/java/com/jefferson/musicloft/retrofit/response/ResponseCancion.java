
package com.jefferson.musicloft.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCancion {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idLocal")
    @Expose
    private String idLocal;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("artista")
    @Expose
    private String artista;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("precio")
    @Expose
    private String precio;
    @SerializedName("precioTotal")
    @Expose
    private String precioTotal;
    @SerializedName("cantidadSeleccionada")
    @Expose
    private String cantidadSeleccionada;
    @SerializedName("escuchado")
    @Expose
    private String escuchado;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("nombreFichero")
    @Expose
    private String nombreFichero;
    @SerializedName("contador")
    @Expose
    private String contador;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseCancion() {
    }

    /**
     * 
     * @param contador
     * @param id
     * @param precioTotal
     * @param titulo
     * @param precio
     * @param cantidadSeleccionada
     * @param tipo
     * @param nombreFichero
     * @param escuchado
     * @param foto
     * @param idLocal
     * @param artista
     */
    public ResponseCancion(String id, String idLocal, String titulo, String artista, String foto, String precio, String precioTotal, String cantidadSeleccionada, String escuchado, String tipo, String nombreFichero, String contador) {
        super();
        this.id = id;
        this.idLocal = idLocal;
        this.titulo = titulo;
        this.artista = artista;
        this.foto = foto;
        this.precio = precio;
        this.precioTotal = precioTotal;
        this.cantidadSeleccionada = cantidadSeleccionada;
        this.escuchado = escuchado;
        this.tipo = tipo;
        this.nombreFichero = nombreFichero;
        this.contador = contador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }

    public void setCantidadSeleccionada(String cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
    }

    public String getEscuchado() {
        return escuchado;
    }

    public void setEscuchado(String escuchado) {
        this.escuchado = escuchado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreFichero() {
        return nombreFichero;
    }

    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

}
