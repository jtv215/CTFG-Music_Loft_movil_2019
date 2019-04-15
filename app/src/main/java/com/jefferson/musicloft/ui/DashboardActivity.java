package com.jefferson.musicloft.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.jefferson.musicloft.R;
import com.jefferson.musicloft.data.MusicLoftViewModel;
import com.jefferson.musicloft.retrofit.response.ResponseMonedas;

public class DashboardActivity extends AppCompatActivity {

    TextView nombreLocal,puntos;
    Toolbar toolbar;
    ImageView fotoPerfil;
    MusicLoftViewModel musicLoftViewModel;
    ResponseMonedas responseMonedas;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.codigoqr:


                    return  true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        musicLoftViewModel = ViewModelProviders.of(this)
                .get(MusicLoftViewModel.class);
        getSupportActionBar().hide();
        findViewById();
        cargardatos();

    }

    private void findViewById() {
        toolbar = findViewById(R.id.toolbar);
        nombreLocal = findViewById(R.id.nombreLocalID);
        puntos = findViewById(R.id.puntosID);
        fotoPerfil = findViewById(R.id.fotoPerfilID);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void cargardatos() {
        nombreLocal.setText("Clasica");
        //llama al cancion listFragment para cargar la lista de canciones
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer,new CancionListFragment())
                .commit();

        responseMonedas=  musicLoftViewModel.getMonedasUsuario("1", puntos);



    }


}
