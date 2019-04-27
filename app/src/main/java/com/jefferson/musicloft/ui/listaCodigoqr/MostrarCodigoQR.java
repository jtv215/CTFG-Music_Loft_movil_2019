package com.jefferson.musicloft.ui.listaCodigoqr;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.jefferson.musicloft.R;
import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.data.CodigoQRRepository;
import com.jefferson.musicloft.data.CodigoQRViewModel;
import com.jefferson.musicloft.retrofit.AuthMusicLoftClient;
import com.jefferson.musicloft.retrofit.AuthTMusicLoftService;
import com.jefferson.musicloft.ui.inicio.MainActivity;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.security.SecureRandom;
import java.math.BigInteger;

public class MostrarCodigoQR extends AppCompatActivity {

    ImageView image;
    Button btnVolverAtras;
    Toolbar toolbar;
    ImageView fotoPerfil,salir;
    TextView nombreLocal,puntos,tituloPuntos;
    CodigoQRViewModel codigoQRViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_codigo_qr);
        getSupportActionBar().hide();
        findViewById();
        codigoQRViewModel = ViewModelProviders.of(this).get(CodigoQRViewModel.class);
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
        String puntos =  b.getString("puntos");
        String idcodigoQR =  b.getString("idcodigoQR");
        String texto = generateRandomText();

        codigoQRViewModel.actualizarQR(idcodigoQR,texto);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(texto,BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder  barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);

        }catch (WriterException e){
            e.printStackTrace();
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


    public static String generateRandomText() {

        SecureRandom random = new SecureRandom();
        String text = new BigInteger(130, random).toString(32);
        return text;
    }


}
