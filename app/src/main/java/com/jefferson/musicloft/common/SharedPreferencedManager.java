package com.jefferson.musicloft.common;

import android.content.Context;
import android.content.SharedPreferences;

//Nos permite guarda variable de sesion, incluso si se cierra la aplicación,
//se guarda en el dispositivo.
//se crean fichero para guardar los datos .

public class SharedPreferencedManager {

    //El nombre del fichero
    private static final String APP_SETTING_FILE = "APP_SETTINGS";

    private SharedPreferencedManager(){

    }

    private static SharedPreferences getSharePreferences(){

        return  MyApp.geContext()
               .getSharedPreferences("APP_SETTING_FILE", Context.MODE_PRIVATE);
    }

    //para escribir en un archivo de preferencias compartido hay llamar al método edit
    public static void setSomeStringValue(String dataclave, String dataValue){
        SharedPreferences.Editor editor = getSharePreferences().edit();
        editor.putString(dataclave,dataValue);
        editor.commit();
    }


    public static String getSomeStringValue(String dataclave){
        return getSharePreferences().getString(dataclave,null);

    }
    /*No tengo datos booleanos
    public static boolean getSomeBooleanValue(String dataclave){
        return getSharePreferences().getBoolean(dataclave,false);

    }
    */
}
