package com.jefferson.musicloft.retrofit;

import com.jefferson.musicloft.common.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicLoftClient {


    private static MusicLoftClient instance= null;
    private MusicLoftService musicLoftService;
    private Retrofit retrofit;

    public MusicLoftClient() {
        //baseurl de nuestra api
        //usamos un converson de gson y creamos una instancia
        //creamos nuestro objeto retrofit
        retrofit = new Retrofit.Builder()
            .baseUrl(Constantes.API_MUISCLOFT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        //instanciamos nuestro servicio
        musicLoftService = retrofit.create(MusicLoftService.class);
    }

        //Patrón Singleton en cuestion de memoria es más eficiente
        public static MusicLoftClient getInstance(){
            if(instance == null){
                instance = new MusicLoftClient();
            }
        return instance;
        }

        //getservice para realizar la peticiones a la api
        public MusicLoftService getMusicLoftService(){

        return musicLoftService;
        }

}
