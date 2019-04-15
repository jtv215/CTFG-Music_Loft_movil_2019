package com.jefferson.musicloft.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jefferson.musicloft.R;
import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.retrofit.AuthMusicLoftClient;
import com.jefferson.musicloft.retrofit.AuthTMusicLoftService;
import com.jefferson.musicloft.retrofit.request.RequestEstablecimiento;
import com.jefferson.musicloft.retrofit.request.RequestVotarCancion;
import com.jefferson.musicloft.retrofit.response.ResponseCancion;
import com.jefferson.musicloft.retrofit.response.ResponseMonedas;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicLoftRepository {


    AuthTMusicLoftService authTMusicLoftService;
    AuthMusicLoftClient authMusicLoftClient;
    //esta lista notificará al view model que hay un cambio.
    MutableLiveData<List<ResponseCancion>> allCanciones;


    //mutable nos permite hacer cambios.
    public MusicLoftRepository(){
        authMusicLoftClient = AuthMusicLoftClient.getInstance();
        authTMusicLoftService = authMusicLoftClient.getAuthMusicLoftService();
        allCanciones = getAllCanciones(); //si se modifica la lista cuando se invoca este metodo **

    }


    /*Devuelve una lista de canciones*/
    public MutableLiveData<List<ResponseCancion >> getAllCanciones(){
        //MutableLiveData es una variable que se puede ir modificando en el tiempo
            if(allCanciones == null){
                allCanciones= new MutableLiveData<>();
            }


        Call<List<ResponseCancion>> call = authTMusicLoftService.cargarCanciones();
        call.enqueue(new Callback<List<ResponseCancion>>() {
            @Override
            public void onResponse(Call<List<ResponseCancion>> call, Response<List<ResponseCancion>> response) {
                if(response.isSuccessful()){
                    //allCanciones = response.body();
                    allCanciones.setValue(response.body());

                }else{
                    Toast.makeText(MyApp.geContext(), "Algo va mal no se ha cargado la lista", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseCancion>> call, Throwable t) {
                Toast.makeText(MyApp.geContext(),"Problemas de Conexión, Inténtelo de nuevo",Toast.LENGTH_SHORT).show();

            }
        });

        return allCanciones;

    }



    public void votarCancion(String idLocal, String idCancion){

        RequestVotarCancion requestVotarCancion = new RequestVotarCancion(idLocal,idCancion);

        Call<ResponseCancion> call = authTMusicLoftService.votarCancion(requestVotarCancion);
        call.request().body().toString();

        call.enqueue(new Callback<ResponseCancion>() {
            @Override
            public void onResponse(Call<ResponseCancion> call, Response<ResponseCancion> response) {


                if(response.isSuccessful()){
                   getAllCanciones();

                }else{
                    Toast.makeText(MyApp.geContext(), "No tienes suficientes puntos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCancion> call, Throwable t) {
              //  Toast.makeText(MyApp.geContext(),"Problemas de Conexión, Inténtelo de nuevo",Toast.LENGTH_SHORT).show();
                getAllCanciones();

            }
        });

    }


    public ResponseMonedas getMonedasUsuario(String idEstablecimiento){
final  ResponseMonedas responseMonedas= new ResponseMonedas();
        RequestEstablecimiento requestEstablecimiento = new RequestEstablecimiento(idEstablecimiento);

      Call<ResponseMonedas> call = authTMusicLoftService.getMonedasUsuario(requestEstablecimiento);
      call.request().body().toString();

        call.enqueue(new Callback<ResponseMonedas>() {
            @Override
            public void onResponse(Call<ResponseMonedas> call, Response<ResponseMonedas> response) {

                if(response.isSuccessful()){
                    Toast.makeText(MyApp.geContext(), "11111", Toast.LENGTH_SHORT).show();

                    responseMonedas.setCode(response.body().getCode());
                    responseMonedas.setStatus(response.body().getStatus());
                    responseMonedas.setMonedas(response.body().getMonedas());


                    Log.e("errrrrrrrorrr22222 ","establecimiento444: "+responseMonedas.getMonedas());

                }else{
                    Toast.makeText(MyApp.geContext(), "No tienes cuenta en el local", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ResponseMonedas> call, Throwable t) {
                Toast.makeText(MyApp.geContext(),"Problemas de Conexión, Inténtelo de nuevo",Toast.LENGTH_SHORT).show();
            }


        });
        return   responseMonedas;

    }

}
