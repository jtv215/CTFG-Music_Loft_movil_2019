package com.jefferson.musicloft.ui.codigoQr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.Result;
import com.jefferson.musicloft.R;
import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.data.UsuLocalViewModel;
import com.jefferson.musicloft.ui.DashboardActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CodigoQR extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    EditText editTextPuntos;
    Button btnEnviar, btnEscanear;
    UsuLocalViewModel usuLocalViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_qr);

        getSupportActionBar().hide();
        findViewById();
        cargarModelView();
        onclick();


    }

    private void findViewById() {
        editTextPuntos = findViewById(R.id.editTextPuntos);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnEscanear = findViewById(R.id.btnEscanear);

    }

    private void cargarModelView() {
        usuLocalViewModel = ViewModelProviders.of(this)
                .get(UsuLocalViewModel.class);
    }

    private void onclick() {

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                addNuevosPuntosyActualizar();
                volverAtras();
            }
        });

        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScannerView = new ZXingScannerView(MyApp.geContext());
                setContentView(mScannerView);
              //  mScannerView.setResultHandler(this);
                mScannerView.startCamera();
            }
        });
    }



    private void addNuevosPuntosyActualizar() {
        usuLocalViewModel.addPuntos(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"),
                editTextPuntos.getText().toString());

        usuLocalViewModel.getPuntosUsuario(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"));
       // usuLocalViewModel.getPuntosUsuario2(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"),tv);


    }

    private void volverAtras() {
        Intent intent = new Intent (MyApp.geContext(), DashboardActivity.class);
        startActivity(intent);
        //onBackPressed();
    }


    @Override
    public void handleResult(Result result) {

        Log.e("Error",result.getText());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado de Código");
       // builder.setMessage(result.getText());
        builder.setMessage("Tus puntos se han añadido Correctamente");
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        //continuar escaneando despues del primer resultado
       // mScannerView.resumeCameraPreview(this);

        Intent intent = new Intent (MyApp.geContext(), DashboardActivity.class);
        startActivityForResult(intent, 0);
    }





}
