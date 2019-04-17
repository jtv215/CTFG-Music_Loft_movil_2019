package com.jefferson.musicloft.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jefferson.musicloft.common.Constantes;
import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.retrofit.AuthMusicLoftClient;
import com.jefferson.musicloft.retrofit.AuthTMusicLoftService;
import com.jefferson.musicloft.retrofit.request.RequestVotarCancion;
import com.jefferson.musicloft.retrofit.response.ResponseCancion;
import com.jefferson.musicloft.retrofit.response.ResponseEstablecimiento;
import com.jefferson.musicloft.retrofit.response.ResponseMonedas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuLocalRepository {


    AuthTMusicLoftService authTMusicLoftService;
    AuthMusicLoftClient authMusicLoftClient;



    //mutable nos permite hacer cambios.
    public UsuLocalRepository(){
        authMusicLoftClient = AuthMusicLoftClient.getInstance();
        authTMusicLoftService = authMusicLoftClient.getAuthMusicLoftService();
    }

    //correcto
    public void getPuntosUsuario(String idEstablecimiento){

        Call<ResponseMonedas> call = authTMusicLoftService.getPuntosUsuario(idEstablecimiento);

        call.enqueue(new Callback<ResponseMonedas>() {
            @Override
            public void onResponse(Call<ResponseMonedas> call, Response<ResponseMonedas> response) {

                if(response.isSuccessful()){
                    SharedPreferencedManager.setSomeStringValue(Constantes.PREF_PUNTOS,response.body().getMonedas());
                }else{
                    Toast.makeText(MyApp.geContext(), "No tienes cuenta en el local", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMonedas> call, Throwable t) {
                Toast.makeText(MyApp.geContext(),"Problemas de Conexión, Inténtelo de nuevoAAAAAAA",Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void getPuntosUsuario2(String idEstablecimiento, final TextView puntos){

        Call<ResponseMonedas> call = authTMusicLoftService.getPuntosUsuario(idEstablecimiento);

        call.enqueue(new Callback<ResponseMonedas>() {
            @Override
            public void onResponse(Call<ResponseMonedas> call, Response<ResponseMonedas> response) {

                if(response.isSuccessful()){
                    SharedPreferencedManager.setSomeStringValue(Constantes.PREF_PUNTOS,response.body().getMonedas());
                    puntos.setText(response.body().getMonedas());
                }else{
                    Toast.makeText(MyApp.geContext(), "No tienes cuenta en el local", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMonedas> call, Throwable t) {
                Toast.makeText(MyApp.geContext(),"Problemas de Conexión, Inténtelo de nuevo",Toast.LENGTH_SHORT).show();
            }

        });

    }



    //Correcto
    public void addPuntos(String idEstablecimiento, String codigoQR ){

        final  ResponseMonedas responseMonedas= new ResponseMonedas();

        Call<ResponseMonedas> call = authTMusicLoftService.addPuntos(idEstablecimiento, codigoQR);
        call.enqueue(new Callback<ResponseMonedas>() {
            @Override
            public void onResponse(Call<ResponseMonedas> call, Response<ResponseMonedas> response) {
                if(response.isSuccessful()){
                    SharedPreferencedManager.setSomeStringValue(Constantes.PREF_PUNTOS,response.body().getMonedas());
                }else{
                    Toast.makeText(MyApp.geContext(), "El código Qr Es incorrecto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMonedas> call, Throwable t) {
                Toast.makeText(MyApp.geContext(),"Problemas de Conexión, Inténtelo de nuevo",Toast.LENGTH_SHORT).show();

            }
        });

    }




}
