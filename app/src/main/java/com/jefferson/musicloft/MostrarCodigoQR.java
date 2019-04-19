package com.jefferson.musicloft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jefferson.musicloft.common.MyApp;

public class MostrarCodigoQR extends AppCompatActivity {

    ImageView image;
    Button btnVolverAtras;

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
