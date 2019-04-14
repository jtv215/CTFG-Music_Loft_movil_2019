package com.jefferson.musicloft.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jefferson.musicloft.retrofit.response.ResponseCancion;

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

    public  void votarCancion(String idLocal, String idCancion){
        musicLoftRepository.votarCancion(idLocal, idCancion);
    }


}


