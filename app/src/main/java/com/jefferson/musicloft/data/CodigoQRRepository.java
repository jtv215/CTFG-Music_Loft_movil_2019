package com.jefferson.musicloft.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.widget.Toast;

import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.retrofit.AuthMusicLoftClient;
import com.jefferson.musicloft.retrofit.AuthTMusicLoftService;
import com.jefferson.musicloft.retrofit.response.ResponseCodigoQR;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodigoQRRepository {

    AuthMusicLoftClient authMusicLoftClient;
    AuthTMusicLoftService authTMusicLoftService;
    LiveData<List<ResponseCodigoQR>> allCodigoQR;

    CodigoQRRepository(){
        authMusicLoftClient = AuthMusicLoftClient.getInstance();
        authTMusicLoftService = authMusicLoftClient.getAuthMusicLoftService();
        allCodigoQR = getAllCodigoQR();

    }

    public LiveData<List<ResponseCodigoQR>> getAllCodigoQR(){
        final MutableLiveData<List<ResponseCodigoQR>> data = new MutableLiveData<>();

        Call<List<ResponseCodigoQR>> call = authTMusicLoftService.cargarlistaCodigoQR(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"));
        call.enqueue(new Callback<List<ResponseCodigoQR>>() {
            @Override
            public void onResponse(Call<List<ResponseCodigoQR>> call, Response<List<ResponseCodigoQR>> response) {
                if(response.isSuccessful()){
                   data.setValue(response.body());


                }else{
                    Toast.makeText(MyApp.geContext(), "Algo va mal no se ha cargado la lista", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseCodigoQR>> call, Throwable t) {
                Toast.makeText(MyApp.geContext(),"Problemas de Conexión, Inténtelo de nuevo",Toast.LENGTH_SHORT).show();

            }
        });

        return data;
    }


    public void  actualizarQR(String idCodigoQR,String codigoQr){

        Call<ResponseCodigoQR> call = authTMusicLoftService.actualizarCodigoQR(idCodigoQR,codigoQr);
        call.enqueue(new Callback<ResponseCodigoQR>() {
            @Override
            public void onResponse(Call<ResponseCodigoQR> call, Response<ResponseCodigoQR> response) {
                if(response.isSuccessful()){

                }else{
                    Toast.makeText(MyApp.geContext(), "Algo va mal no se ha cargado la lista", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCodigoQR> call, Throwable t) {
                Toast.makeText(MyApp.geContext(),"Problemas de Conexión, Inténtelo de nuevo",Toast.LENGTH_SHORT).show();

            }
        });

    }

}
