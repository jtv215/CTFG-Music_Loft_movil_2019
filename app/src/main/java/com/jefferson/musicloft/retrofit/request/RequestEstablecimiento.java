
package com.jefferson.musicloft.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestEstablecimiento {

    @SerializedName("idEstablecimiento")
    @Expose
    private String idEstablecimiento;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequestEstablecimiento() {
    }

    /**
     * 
     * @param idEstablecimiento
     */
    public RequestEstablecimiento(String idEstablecimiento) {
        super();
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(String idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

}
