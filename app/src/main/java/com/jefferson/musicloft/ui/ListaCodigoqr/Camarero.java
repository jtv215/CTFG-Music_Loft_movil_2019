package com.jefferson.musicloft.ui.ListaCodigoqr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jefferson.musicloft.R;
import com.jefferson.musicloft.ui.ListaCodigoqr.CodigoListFragment;

public class Camarero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camarero);
        getSupportActionBar().hide();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainerCodigoQR, new CodigoListFragment())
                .commit();



    }
}
