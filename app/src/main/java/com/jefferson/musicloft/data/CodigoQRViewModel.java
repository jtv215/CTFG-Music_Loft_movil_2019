package com.jefferson.musicloft.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.jefferson.musicloft.retrofit.response.ResponseCodigoQR;

import java.util.List;

public class CodigoQRViewModel extends AndroidViewModel {

    private CodigoQRRepository codigoQRRepository;
    private LiveData<List<ResponseCodigoQR>> codigoQR;

    public CodigoQRViewModel(@NonNull Application application) {
        super(application);
        codigoQRRepository = new CodigoQRRepository();
        codigoQR = codigoQRRepository.getAllCodigoQR();



    }

    public LiveData<List<ResponseCodigoQR>> getCodigoQR(){
        return codigoQR;
    }

    public void actualizarQR(String idCodigoQR,String codigoQr){
        codigoQRRepository.actualizarQR(idCodigoQR,codigoQr);


    }

}
