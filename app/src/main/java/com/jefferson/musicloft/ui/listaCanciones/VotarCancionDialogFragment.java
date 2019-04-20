package com.jefferson.musicloft.ui.listaCanciones;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jefferson.musicloft.R;
import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.data.MusicLoftViewModel;
import com.jefferson.musicloft.data.UsuLocalViewModel;


public class VotarCancionDialogFragment extends DialogFragment {

    TextView titulo,artista,precio,cantidadSeleccionada,puntosTotales;
    ImageView foto;
    Button cerrar,botonVotar;
    MusicLoftViewModel musicLoftViewModel;
    String idCancion2,idLocal2;
    UsuLocalViewModel usuLocalViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         super.onCreateView(inflater, container, savedInstanceState);
        musicLoftViewModel = ViewModelProviders.of(getActivity())
                .get(MusicLoftViewModel.class);
        usuLocalViewModel = ViewModelProviders.of(this)
                .get(UsuLocalViewModel.class);
         View view = inflater.inflate(R.layout.votar_dialog,container,false);
         titulo = view.findViewById(R.id.DtituloCancionID);
         artista = view.findViewById(R.id.DartistaID);
         precio = view.findViewById(R.id.precio);

        foto = view.findViewById(R.id.fotoID);
        cantidadSeleccionada = view.findViewById(R.id.cantidadSeleccionadaID);
        puntosTotales = view.findViewById(R.id.DpuntosTotalesID);
        cerrar =(Button)  view.findViewById(R.id.cerrarID);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarVentana();
            }


        });
        botonVotar =(Button)  view.findViewById(R.id.DbuttonVotarID);
        botonVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                votar();
            }


        });



         //Recibir datos
        String titulo2 = this.getArguments().getString("titulo");
        String artista2 = this.getArguments().getString("artista");
        String foto2 = this.getArguments().getString("foto");
        String precio2 = this.getArguments().getString("precio");
        String cantidadSeleccionada2 = this.getArguments().getString("cantidadSeleccionada");
        String puntosTotales2 = this.getArguments().getString("puntosTotales");
        idCancion2 = this.getArguments().getString("idCancion");
        idLocal2 = this.getArguments().getString("idLocal");

        //Bind Data
        titulo.setText(titulo2);
        artista.setText(artista2);
        precio.setText(precio2);
        cantidadSeleccionada.setText(cantidadSeleccionada2);
        puntosTotales.setText(puntosTotales2+"-P");
        if (!foto2.equals("")) {
            Glide.with(MyApp.geContext())
                    .load("" + foto2).into(foto);
        }

         return  view;
    }

    private void cerrarVentana() {
        getDialog().dismiss();
    }

    private void votar() {


       musicLoftViewModel.votarCancion(idLocal2,idCancion2,cantidadSeleccionada,puntosTotales);

       //LLAMAS Al textview de otra activity
        TextView puntos= (TextView)((Activity)getActivity()).findViewById(R.id.puntosID);
        String valorpuntos =((TextView)((Activity)getActivity()).findViewById(R.id.puntosID)).getText().toString();
        int n= Integer.parseInt(valorpuntos);
        int valorCancion= Integer.parseInt(precio.getText().toString());

        int resta= n-valorCancion;
        String solucion = String.valueOf(resta);
        puntos.setText(solucion);

     /*   funciona pero al segundo click
       usuLocalViewModel.getPuntosUsuario2(SharedPreferencedManager.getSomeStringValue("PREF_ESTABLECIMIENTO"),
                (TextView)((Activity)getActivity()).findViewById(R.id.puntosID)
                );


*/



    }



}
