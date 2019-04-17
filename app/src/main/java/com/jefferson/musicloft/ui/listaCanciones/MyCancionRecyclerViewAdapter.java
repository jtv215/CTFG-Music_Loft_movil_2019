package com.jefferson.musicloft.ui.listaCanciones;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.jefferson.musicloft.R;
import com.jefferson.musicloft.retrofit.response.ResponseCancion;

import java.util.List;


public class MyCancionRecyclerViewAdapter extends RecyclerView.Adapter<MyCancionRecyclerViewAdapter.ViewHolder> {

    private List<ResponseCancion> mValues;
    private Context ctx;
    FragmentManager fm;


    public MyCancionRecyclerViewAdapter(Context contexto, List<ResponseCancion> items,FragmentManager fm) {
        mValues = items;
       ctx = contexto;
       this.fm = fm;
    }

    //Carga el layout sobre el elemento de la lista
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cancion, parent, false);

               return new ViewHolder(view);


    }

    //Se encargar de dibujar cada elemento de la lista
    //lo hace como un bucle
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(mValues!= null) {

            holder.mItem = mValues.get(position);

            holder.titulo.setText(holder.mItem.getTitulo());
            holder.artista.setText(holder.mItem.getArtista());
            holder.precio2.setText(holder.mItem.getPrecio());
            holder.cantidadSeleccionada.setText(holder.mItem.getCantidadSeleccionada());
            holder.puntosTotales.setText((holder.mItem.getPrecioTotal()+"-P"));

            String foto = holder.mItem.getFoto();
            if (!foto.equals("")) {
                Glide.with(ctx)
                        .load("" + holder.mItem.getFoto()).into(holder.foto);
            }

            holder.BotonVotar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDialogFragment((String) holder.titulo.getText(),
                            holder.mItem.getArtista(),
                            holder.mItem.getFoto(),
                            holder.mItem.getCantidadSeleccionada(),
                            holder.mItem.getPrecio(),
                            holder.mItem.getPrecioTotal(),
                            holder.mItem.getId(),
                            holder.mItem.getIdLocal()

                    );
                }
            });


        }

    }

    public void setData(List<ResponseCancion> listaCanciones){
       this.mValues = listaCanciones;
        notifyDataSetChanged();

    }
    //Abrir el dialogFragement
    private void openDialogFragment(String titulo, String artista,String foto,String cantidadSeleccionada, String precio, String puntosTotales, String idCancion, String idLocal){
        Bundle b = new Bundle();
        b.putString("titulo",titulo);
        b.putString("artista",artista);
        b.putString("foto",foto);
        b.putString("cantidadSeleccionada",cantidadSeleccionada);
        b.putString("precio",precio);
        b.putString("puntosTotales",puntosTotales);
        b.putString("idCancion",idCancion);
        b.putString("idLocal",idLocal);

        VotarCancionDialogFragment votarCancionDialogFragment = new VotarCancionDialogFragment();
        votarCancionDialogFragment.setArguments(b);
        votarCancionDialogFragment.show(fm,"mtag");

    }

    @Override
    public int getItemCount() {
        if(mValues != null)
            return  mValues.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;

       // public final TextView id;
        public final TextView titulo;
        public final TextView artista;
        public final ImageView foto;
        public final Button BotonVotar;
        public final TextView precio;
        public final TextView precio2;
        public final TextView cantidadSeleccionada;
        public final TextView puntosTotales;

        ItemClickListener itemClickListener;

        public ResponseCancion mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //id =  view.findViewById(R.id.cancionID);
            titulo =  view.findViewById(R.id.DtituloCancionID);
            artista =  view.findViewById(R.id.DartistaID);
            foto =  view.findViewById(R.id.fotoID);
            BotonVotar =  view.findViewById(R.id.votarID);
            precio =  view.findViewById(R.id.precio);
            precio2 =  view.findViewById(R.id.precioID);
            cantidadSeleccionada = view.findViewById(R.id.cantidadSeleccionadaID);
            puntosTotales = view.findViewById(R.id.puntosTotalesID);

        }




        @Override
        public String toString() {
            return super.toString() + " '" + titulo.getText() + "'";
        }


        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());

        }
    }
}
