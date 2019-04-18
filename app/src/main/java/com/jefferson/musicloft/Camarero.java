package com.jefferson.musicloft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
