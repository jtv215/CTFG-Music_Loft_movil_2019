package com.jefferson.musicloft.retrofit;

import com.jefferson.musicloft.retrofit.request.RequestLogin;
import com.jefferson.musicloft.retrofit.request.RequestSignup;
import com.jefferson.musicloft.retrofit.response.ResponseAuth;
import com.jefferson.musicloft.retrofit.response.ResponseCancion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MusicLoftService {

    //HACES UNA LLAMADA EN LA QUE RECIBES UNA RESPUESTA DE TIPO Responseauth
    //lo que le paso por parametro es un jason de tipo body requestlOGIN

    @POST("login")
    Call<ResponseAuth> doLogin(@Body RequestLogin requestLogin);

    @POST("registrarse")
    Call<ResponseAuth> doSignUp(@Body RequestSignup requestSignup);


}
