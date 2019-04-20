package com.jefferson.musicloft.ui.principal;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class LiveDataUserViewModel extends ViewModel {

    private  MutableLiveData<String> liveData;


    public LiveData<String> getData(){
        if(liveData==null){
            liveData = new MutableLiveData<>();
            liveData.setValue("0");
        }
        return liveData;
    }

    public void setDatos(String aux){
         liveData.setValue("hola");
    }


    public void resetScore() {
        liveData.setValue("200");
    }

}
