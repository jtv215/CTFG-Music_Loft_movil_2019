package com.jefferson.musicloft.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.jefferson.musicloft.R;
import com.jefferson.musicloft.common.Constantes;
import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.data.MusicLoftViewModel;
import com.jefferson.musicloft.data.UsuLocalViewModel;
import com.jefferson.musicloft.retrofit.response.ResponseMonedas;
import com.jefferson.musicloft.ui.codigoQr.CodigoQR;
import com.jefferson.musicloft.ui.listaCanciones.CancionListFragment;

public class DashboardActivity extends AppCompatActivity {

    TextView nombreLocal,puntos;
    Toolbar toolbar;
    ImageView fotoPerfil;
    MusicLoftViewModel musicLoftViewModel;

    UsuLocalViewModel usuLocalViewModel;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment f= null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    f = new CancionListFragment();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, f)
                            .commit();

                    return true;
                case R.id.codigoqr:

                    Intent intent = new Intent (MyApp.geContext(), CodigoQR.class);
                    startActivityForResult(intent, 0);
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
        usuLocalViewModel = ViewModelProviders.of(this)
                .get(UsuLocalViewModel.class);
        //Cuando se tenga la pantalla de seleccionar local:



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

        //llama al cancion listFragment para cargar la lista de canciones
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer,new CancionListFragment())
                .commit();


        //obtener putnos
        usuLocalViewModel.getPuntosUsuario2(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"),puntos);

        nombreLocal.setText(SharedPreferencedManager.getSomeStringValue("PREF_NOMBRELOCAL"));
    }

    public void cambiar (String mTextView) {

        puntos.setText (mTextView);
    }

}
