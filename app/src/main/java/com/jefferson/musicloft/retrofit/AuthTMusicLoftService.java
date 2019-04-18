package com.jefferson.musicloft.retrofit;

import com.jefferson.musicloft.retrofit.request.RequestEstablecimiento;
import com.jefferson.musicloft.retrofit.request.RequestSignup;
import com.jefferson.musicloft.retrofit.request.RequestVotarCancion;
import com.jefferson.musicloft.retrofit.response.EstablecimientoRespuesta;
import com.jefferson.musicloft.retrofit.response.ResponseCancion;
import com.jefferson.musicloft.retrofit.response.ResponseEstablecimiento;
import com.jefferson.musicloft.retrofit.response.ResponseMonedas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthTMusicLoftService {


    @GET("cargarCanciones/{idEstablecimiento}")
    Call<List<ResponseCancion>> cargarCanciones(@Path("idEstablecimiento") String idEstablecimiento);

    @GET("votarCancion/{idEstablecimiento}/{idCancion}")
    Call<ResponseCancion> votarCancion(@Path("idEstablecimiento") String idEstablecimiento, @Path("idCancion") String idCancion);

    @GET("getPuntosUsuario/{idEstablecimiento}")
    Call<ResponseMonedas> getPuntosUsuario(@Path("idEstablecimiento") String idEstablecimiento);

    @GET("addPuntos/{idEstablecimiento}/{codigoQR}")
    Call<ResponseMonedas> addPuntos(@Path("idEstablecimiento") String idEstablecimiento,@Path("codigoQR") String codigoQR);

    @GET("cargarEstablecimientos")
    Call<List<ResponseEstablecimiento>> cargarEstablecimientos();
}
