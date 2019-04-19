package com.jefferson.musicloft.ui.ListaCodigoqr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jefferson.musicloft.R;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.ui.inicio.MainActivity;

public class MostrarCodigoQR extends AppCompatActivity {

    ImageView image;
    Button btnVolverAtras;
    Toolbar toolbar;
    ImageView fotoPerfil,salir;
    TextView nombreLocal,puntos,tituloPuntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_codigo_qr);
        getSupportActionBar().hide();
        findViewById();
        cargardatos();
        events();
    }


    private void findViewById() {
        image = findViewById(R.id.imageCodigoQRID);
        btnVolverAtras = findViewById(R.id.btnVolver);
        toolbar = findViewById(R.id.toolbar);
        nombreLocal = findViewById(R.id.nombreLocalID);
        puntos = findViewById(R.id.puntosID);
        fotoPerfil = findViewById(R.id.fotoPerfilID);
        tituloPuntos = findViewById(R.id.tituloPuntosID);

        puntos.setVisibility(View.INVISIBLE);
        tituloPuntos.setVisibility(View.INVISIBLE);

        salir = findViewById(R.id.btnSalir);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MostrarCodigoQR.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        nombreLocal.setText("Camarero");
    }

    private void cargardatos() {
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b !=null){
            String url =  b.getString("IMG");
            Glide.with(this)
                    .load(url)
                    .into(image);
        }
    }

    private void events() {
        btnVolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

}
