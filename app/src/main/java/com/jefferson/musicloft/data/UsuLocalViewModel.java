package com.jefferson.musicloft.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.jefferson.musicloft.common.Constantes;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.retrofit.response.ResponseCancion;
import com.jefferson.musicloft.retrofit.response.ResponseMonedas;

import java.util.List;

public class UsuLocalViewModel extends AndroidViewModel {

    private  UsuLocalRepository usuLocalRepository;
   String puntos;


    public UsuLocalViewModel(@NonNull Application application) {
        super(application);
        usuLocalRepository = new UsuLocalRepository();
      //  puntos = usuLocalRepository.getPuntosUsuario(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"));

    }

    public String getPuntos() {
        return puntos;
    }

    public void getPuntosUsuario(String idEstablecimiento) {
         usuLocalRepository.getPuntosUsuario(idEstablecimiento);
    }


    public void  getPuntosUsuario2(String idEstablecimiento, final TextView puntos){
        usuLocalRepository.getPuntosUsuario2(idEstablecimiento,puntos);
    }

    public void addPuntos(String idEstablecimiento, String codigoQR ) {
         usuLocalRepository.addPuntos(idEstablecimiento, codigoQR);
    }

}


