
package com.jefferson.musicloft.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestVotarCancion {

    @SerializedName("idEstablecimiento")
    @Expose
    private String idEstablecimiento;
    @SerializedName("idCancion")
    @Expose
    private String idCancion;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequestVotarCancion() {
    }

    /**
     * 
     * @param idCancion
     * @param idEstablecimiento
     */
    public RequestVotarCancion(String idEstablecimiento, String idCancion) {
        super();
        this.idEstablecimiento = idEstablecimiento;
        this.idCancion = idCancion;
    }

    public String getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(String idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(String idCancion) {
        this.idCancion = idCancion;
    }

}
