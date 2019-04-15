package com.jefferson.musicloft.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jefferson.musicloft.retrofit.response.ResponseCancion;
import com.jefferson.musicloft.retrofit.response.ResponseMonedas;

import java.util.List;

public class MusicLoftViewModel extends AndroidViewModel {

    private  MusicLoftRepository musicLoftRepository;
    //Live DATa nos permite observar si hay un cambio
    private LiveData<List<ResponseCancion>> canciones;


    public MusicLoftViewModel(@NonNull Application application) {
        super(application);
        musicLoftRepository = new MusicLoftRepository();
        //aqui devolvemos la lista de cancione a view model
        canciones =musicLoftRepository.getAllCanciones();


    }

    public LiveData<List<ResponseCancion>> getCanciones(){
        return canciones;
    }

    public LiveData<List<ResponseCancion>> getNewCanciones(){
        canciones = musicLoftRepository.getAllCanciones();

        return canciones;
    }


    public  void votarCancion(String idLocal, String idCancion){
        canciones =musicLoftRepository.getAllCanciones();
        musicLoftRepository.votarCancion(idLocal, idCancion);
    }

    public ResponseMonedas getMonedasUsuario(String idEstablecimiento) {
        return  musicLoftRepository.getMonedasUsuario(idEstablecimiento);


    }

}


