package com.jefferson.musicloft.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jefferson.musicloft.R;
import com.jefferson.musicloft.common.Constantes;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.retrofit.MusicLoftClient;
import com.jefferson.musicloft.retrofit.MusicLoftService;
import com.jefferson.musicloft.retrofit.request.RequestLogin;
import com.jefferson.musicloft.retrofit.response.ResponseAuth;
import com.jefferson.musicloft.ui.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    TextView tvGosignUp;
    EditText etEmail, etPassword;
    MusicLoftClient musicLoftClient;
    MusicLoftService musicLoftService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);
        getSupportActionBar().hide();
        retrofitInit();
        findViewById();
        events();


    }

    private void retrofitInit() {
        musicLoftClient= MusicLoftClient.getInstance();
        musicLoftService= musicLoftClient.getMusicLoftService();
    }


    private void findViewById() {
        btnLogin = findViewById(R.id.buttonLogin);
        tvGosignUp = findViewById(R.id.textViewGoSign);
        etEmail= findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
    }

    private void events() {
        btnLogin.setOnClickListener(this);
        tvGosignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){
            case R.id.buttonLogin:
                goToLogin();
                break;
            case R.id.textViewGoSign:
                goToSignUp();
                break;

        }


    }

    private void goToLogin() {
        final String email= etEmail.getText().toString();
        final String password= etPassword.getText().toString();

        if(email.isEmpty()){
            etEmail.setError("El email es requerido");
        }else if(password.isEmpty()){
            etPassword.setError("La contraseña es requerida");
        }else{
            RequestLogin requestLogin = new RequestLogin(email,password);


            Call<ResponseAuth> call = musicLoftService.doLogin(requestLogin);
            call.request().body().toString();



            //Petición asincronada
            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {


                        if(response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Sesion iniciada Correctamente", Toast.LENGTH_SHORT).show();


                            //Se Guarda en un fichero de preferencias la variable.
                            //String tokenUsuario = response.headers().get("auth-token");
                            SharedPreferencedManager.setSomeStringValue(Constantes.PREF_TOKEN,response.headers().get("auth-token"));
                            SharedPreferencedManager.setSomeStringValue(Constantes.PREF_ID,response.body().getId());
                            SharedPreferencedManager.setSomeStringValue(Constantes.PREF_NOMBRE,response.body().getNombre());
                            SharedPreferencedManager.setSomeStringValue(Constantes.PREF_CORREO,response.body().getCorreo());
                            SharedPreferencedManager.setSomeStringValue(Constantes.PREF_CONTRASENA,response.body().getContrasena());
                            SharedPreferencedManager.setSomeStringValue(Constantes.PREF_SEXO,response.body().getSexo());


                            Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                            String hola= response.headers().toString();
                            startActivity(i);
                            //Destruimos este Activity para que no se pueda volver.
                           //finish();

                        } else {
                            Toast.makeText(MainActivity.this, "Correo o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }



                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Problemas de Conexión, Inténtelo de nuevo",Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private void goToSignUp() {

        Intent i= new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(i);
        finish();
    }


}
