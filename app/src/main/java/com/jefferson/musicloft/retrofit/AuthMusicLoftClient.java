package com.jefferson.musicloft.retrofit;

import com.jefferson.musicloft.common.Constantes;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthMusicLoftClient {


    private static AuthMusicLoftClient instance= null;
    private AuthTMusicLoftService musicLoftService;
    private Retrofit retrofit;

    public AuthMusicLoftClient() {

        //incluir en la cabecera de la peticion el token que autoriza al usuario
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.addInterceptor(new AuthInterceptor());
        OkHttpClient cliente  = okBuilder.build();


        retrofit = new Retrofit.Builder()
            .baseUrl(Constantes.API_MUISCLOFT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build();

        musicLoftService = retrofit.create(AuthTMusicLoftService.class);
    }

        //Patrón Singleton en cuestion de memoria es más eficiente
        public static AuthMusicLoftClient getInstance(){
            if(instance == null){
                instance = new AuthMusicLoftClient();
            }
        return instance;
        }

        public AuthTMusicLoftService getAuthMusicLoftService(){
        return musicLoftService;
        }

}
