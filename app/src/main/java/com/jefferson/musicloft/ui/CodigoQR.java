package com.jefferson.musicloft.ui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;
import com.jefferson.musicloft.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CodigoQR extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_qr);
    }


    public void btnEscanear(View v){
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

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
    }



}
