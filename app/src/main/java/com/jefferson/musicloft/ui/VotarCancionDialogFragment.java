package com.jefferson.musicloft.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jefferson.musicloft.R;
import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.data.MusicLoftViewModel;
import com.jefferson.musicloft.retrofit.response.ResponseCancion;

import java.util.List;

public class VotarCancionDialogFragment extends DialogFragment {

    TextView titulo,artista,precio,cantidadSeleccionada,puntosTotales;
    ImageView foto;
    Button cerrar,botonVotar;
    MusicLoftViewModel musicLoftViewModel;
    String idCancion2,idLocal2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         super.onCreateView(inflater, container, savedInstanceState);
        musicLoftViewModel = ViewModelProviders.of(getActivity())
                .get(MusicLoftViewModel.class);

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
        MusicLoftViewModel musicLoftViewModel = ViewModelProviders
            .of(getActivity()).get(MusicLoftViewModel.class);
        //Toast.makeText(MyApp.geContext(), "idLocal"+idLocal2+ "idCANCION"+idCancion2, Toast.LENGTH_SHORT).show();
        musicLoftViewModel.votarCancion(idLocal2,idCancion2);
    }

}
