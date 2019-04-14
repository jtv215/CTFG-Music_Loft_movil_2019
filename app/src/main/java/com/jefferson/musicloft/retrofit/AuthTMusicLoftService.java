package com.jefferson.musicloft.retrofit;

import com.jefferson.musicloft.retrofit.request.RequestSignup;
import com.jefferson.musicloft.retrofit.request.RequestVotarCancion;
import com.jefferson.musicloft.retrofit.response.ResponseCancion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthTMusicLoftService {


    @GET("cargarCanciones")
    Call<List<ResponseCancion>> cargarCanciones();

    @POST("votarCancion")
    Call<ResponseCancion> votarCancion(@Body RequestVotarCancion requestVotarCancion);
}
