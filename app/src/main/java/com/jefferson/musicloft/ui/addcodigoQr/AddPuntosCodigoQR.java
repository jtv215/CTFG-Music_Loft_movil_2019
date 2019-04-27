package com.jefferson.musicloft.ui.addcodigoQr;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
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
import com.jefferson.musicloft.ui.principal.DashboardActivity;
import com.jefferson.musicloft.ui.principal.LiveDataUserViewModel;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class AddPuntosCodigoQR extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    EditText editTextPuntos;
    Button btnEnviar, btnEscanear;
    UsuLocalViewModel usuLocalViewModel;
    LiveDataUserViewModel liveDataUserViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_qr);

        getSupportActionBar().hide();
        findViewById();
        cargarModelView();
        onclick();
     //  ocultarbotones();
      // mostrarCodigoqr();


    }

    private void mostrarCodigoqr() {
        mScannerView = new ZXingScannerView(MyApp.geContext());
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    private void ocultarbotones() {
        editTextPuntos.setVisibility(View.INVISIBLE);
        btnEnviar.setVisibility(View.INVISIBLE);
        btnEscanear.setVisibility(View.INVISIBLE);

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
              // mScannerView.setResultHandler(this);
                mScannerView.startCamera();
            }
        });
    }



    private void addNuevosPuntosyActualizar() {


        liveDataUserViewModel = ViewModelProviders.of(this).get(LiveDataUserViewModel.class);


        //*Funciona a medias
        usuLocalViewModel.addPuntos(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"),
                editTextPuntos.getText().toString());

        usuLocalViewModel.getPuntosUsuario(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"));



       // (TextView)((Activity)getActivity()).findViewById(R.id.puntosID);
        //usuLocalViewModel.getPuntosUsuario2(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"),textViewPuntos);

    }



    private void volverAtras() {
        Intent intent = new Intent (AddPuntosCodigoQR.this, DashboardActivity.class);
        startActivity(intent);

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
