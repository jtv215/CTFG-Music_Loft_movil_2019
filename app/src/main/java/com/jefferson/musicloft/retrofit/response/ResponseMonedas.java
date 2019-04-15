
package com.jefferson.musicloft.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMonedas {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("monedas")
    @Expose
    private String monedas;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseMonedas() {
    }

    /**
     * 
     * @param status
     * @param monedas
     * @param code
     */
    public ResponseMonedas(String status, Integer code, String monedas) {
        super();
        this.status = status;
        this.code = code;
        this.monedas = monedas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMonedas() {
        return monedas;
    }

    public void setMonedas(String monedas) {
        this.monedas = monedas;
    }

}
