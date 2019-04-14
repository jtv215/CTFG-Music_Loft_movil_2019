package com.jefferson.musicloft.retrofit;

import com.jefferson.musicloft.common.Constantes;
import com.jefferson.musicloft.common.SharedPreferencedManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {


    //Este método va a ser invocado, cuando queramos hacer un peticion interceptarlo y aplicar lo que haya en el método
    @Override
    public Response intercept(Chain chain) throws IOException {
        //recibimos un objeto chain que nos permite enlazar o añadir lo que queremos mandar

        String token = SharedPreferencedManager.getSomeStringValue(Constantes.PREF_TOKEN);

        //añadimos a request lo que recibimos pero además se va a añadir una cabecera
        Request request = chain.request().newBuilder().addHeader("Authorization",""+token).build();
        return chain.proceed(request);
    }
}
