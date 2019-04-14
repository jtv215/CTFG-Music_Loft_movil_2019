package com.jefferson.musicloft.common;

import android.app.Application;
import android.content.Context;


//Nos devuelve el contexto de cualquier sitio que queramos invocarlo

public class MyApp extends Application {

    private static  MyApp instance;

    public  static MyApp getInstace(){

        return  instance;
    }

    public static Context geContext(){
    return instance;

    }

    //Se crea una vez cuando se abre la aplicación
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }


}
