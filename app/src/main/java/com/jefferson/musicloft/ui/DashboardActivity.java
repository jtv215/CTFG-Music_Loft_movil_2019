package com.jefferson.musicloft.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jefferson.musicloft.R;
import com.jefferson.musicloft.SeleccionarEstablecimiento;
import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.data.MusicLoftViewModel;
import com.jefferson.musicloft.data.UsuLocalViewModel;

import com.jefferson.musicloft.ui.codigoQr.CodigoQR;
import com.jefferson.musicloft.ui.inicio.MainActivity;
import com.jefferson.musicloft.ui.listaCanciones.CancionListFragment;

public class DashboardActivity extends AppCompatActivity {

    TextView nombreLocal,puntos;
    Toolbar toolbar;
    ImageView fotoPerfil,salir;
    MusicLoftViewModel musicLoftViewModel;
    UsuLocalViewModel usuLocalViewModel;

    private Toolbar mTopToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        findViewById();
        cargarViewModel();
        cargardatos();

    }




    private void findViewById() {
        toolbar = findViewById(R.id.toolbar);
        nombreLocal = findViewById(R.id.nombreLocalID);
        puntos = findViewById(R.id.puntosID);
        fotoPerfil = findViewById(R.id.fotoPerfilID);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        salir = findViewById(R.id.btnSalir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void cargarViewModel() {
        musicLoftViewModel = ViewModelProviders.of(this)
                .get(MusicLoftViewModel.class);
        getSupportActionBar().hide();
        usuLocalViewModel = ViewModelProviders.of(this)
                .get(UsuLocalViewModel.class);
    }


    private void cargardatos() {

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer,new CancionListFragment())
                .commit();

        nombreLocal.setText(SharedPreferencedManager.getSomeStringValue("PREF_NOMBRELOCAL"));
        usuLocalViewModel.getPuntosUsuario2(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"),puntos);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment f= null;
            switch (item.getItemId()) {
                case R.id.volverSeleccionarEstablecimiento:
                    cambiarSeleccionarEstablecimiento();
                    return  true;

                case R.id.navigation_home:
                    f = new CancionListFragment();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, f)
                            .commit();
                    return true;

                case R.id.codigoqr:
                    cambiarAddCodigoQR();
                    return  true;
            }
            return false;
        }
    };




    private void cambiarSeleccionarEstablecimiento() {
        Intent intent2 = new Intent (MyApp.geContext(), SeleccionarEstablecimiento.class);
        startActivityForResult(intent2, 0);
    }

    private void cambiarAddCodigoQR() {
        Intent intent = new Intent (MyApp.geContext(), CodigoQR.class);
        startActivityForResult(intent, 0);
        finish();
    }
    public TextView getTextView()
    {

       // TextView txtView = (TextView)findViewById(R.id.puntosID);
        return this.puntos;
    }

}
