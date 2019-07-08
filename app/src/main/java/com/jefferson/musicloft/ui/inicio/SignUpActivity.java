package com.jefferson.musicloft.ui.inicio;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jefferson.musicloft.R;
import com.jefferson.musicloft.retrofit.MusicLoftClient;
import com.jefferson.musicloft.retrofit.MusicLoftService;
import com.jefferson.musicloft.retrofit.request.RequestSignup;
import com.jefferson.musicloft.retrofit.response.ResponseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    Button btnSignUP;
    TextView tvGoLogin;
    EditText etUsername, etEmail,etPassword;
    MusicLoftClient musicLoftClient;
    MusicLoftService musicLoftService;
    String sexo;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        findViews();
        retrofitInit();
        addListenerOnSpinnerItemSelection();
        events();

    }
    private void findViews() {
        btnSignUP = findViewById(R.id.buttonSignUp);
        tvGoLogin = findViewById(R.id.textViewGoLogin);

        etUsername =findViewById(R.id.editTextUsername);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        tvGoLogin.setText(Html.fromHtml("Si ya dispones de una cuenta " + "<font><b>" + "Inicia Sesión Aquí" + "</b></font>" ));

    }

    private void retrofitInit() {
        musicLoftClient= MusicLoftClient.getInstance();
        musicLoftService= musicLoftClient.getMusicLoftService();
    }

    public void addListenerOnSpinnerItemSelection() {

        spinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.eligeSexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void events() {
        btnSignUP.setOnClickListener(this);
        tvGoLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.buttonSignUp:
                gotToSignUp();
                break;
            case R.id.textViewGoLogin:
                goToLogin();
                break;

        }
    }


    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        sexo = parent.getItemAtPosition(position).toString();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void gotToSignUp() {
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();


        if (username.isEmpty()) {
            etPassword.setError("El nombre de usuario es requerido");
        } else if (email.isEmpty()) {
            etPassword.setError("La contraseña es requerida");
        } else if (password.isEmpty() || password.length() < 4) {
            etPassword.setError("La contraseña es requerida  y debe tener al menos 4 caracteres");
        } else {
           // Toast.makeText(this,"hola___"+sexo,Toast.LENGTH_SHORT).show();
            RequestSignup  requestSignup= new RequestSignup(username,email,password,sexo);
            Call<ResponseAuth> call = musicLoftService.doSignUp(requestSignup);

            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                        goToLogin();
                    }else{
                        Toast.makeText(SignUpActivity.this, "Error, revise los datos, o correo ya existe", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this,"Problemas de Conexión, Inténtelo de nuevo",Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private void goToLogin() {
       Intent i = new Intent(SignUpActivity.this, MainActivity.class);
       startActivity(i);
       finish();

    }


}
