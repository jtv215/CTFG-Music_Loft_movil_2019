
package com.jefferson.musicloft.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLoginEmpleado {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idLocal")
    @Expose
    private String idLocal;
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
    public ResponseLoginEmpleado() {
    }

    /**
     * 
     * @param nombre
     * @param id
     * @param status
     * @param sexo
     * @param idLocal
     * @param code
     * @param contrasena
     * @param correo
     */
    public ResponseLoginEmpleado(String status, Integer code, String id, String idLocal, String nombre, String correo, String contrasena, String sexo) {
        super();
        this.status = status;
        this.code = code;
        this.id = id;
        this.idLocal = idLocal;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.sexo = sexo;
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
