
package com.jefferson.musicloft.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCodigoQR {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idlocal")
    @Expose
    private String idlocal;
    @SerializedName("codQR")
    @Expose
    private String codQR;
    @SerializedName("precio")
    @Expose
    private String precio;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseCodigoQR() {
    }

    /**
     * 
     * @param id
     * @param precio
     * @param codQR
     * @param idlocal
     * @param url
     */
    public ResponseCodigoQR(String id, String idlocal, String codQR, String precio, String url) {
        super();
        this.id = id;
        this.idlocal = idlocal;
        this.codQR = codQR;
        this.precio = precio;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(String idlocal) {
        this.idlocal = idlocal;
    }

    public String getCodQR() {
        return codQR;
    }

    public void setCodQR(String codQR) {
        this.codQR = codQR;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
