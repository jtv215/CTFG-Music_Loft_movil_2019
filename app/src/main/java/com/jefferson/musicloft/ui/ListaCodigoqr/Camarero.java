package com.jefferson.musicloft.ui.ListaCodigoqr;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jefferson.musicloft.R;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.ui.DashboardActivity;
import com.jefferson.musicloft.ui.ListaCodigoqr.CodigoListFragment;
import com.jefferson.musicloft.ui.inicio.MainActivity;

public class Camarero extends AppCompatActivity {

    Toolbar toolbar;
    ImageView fotoPerfil,salir;
    TextView nombreLocal,puntos,tituloPuntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camarero);
        getSupportActionBar().hide();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainerCodigoQR, new CodigoListFragment())
                .commit();

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
                Intent i = new Intent(Camarero.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        nombreLocal.setText("Camarero");

    }
}
