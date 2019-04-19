package com.jefferson.musicloft;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.retrofit.response.ResponseCodigoQR;



import java.util.List;


import static android.support.v4.content.ContextCompat.startActivity;


public class MyCodigoRecyclerViewAdapter extends RecyclerView.Adapter<MyCodigoRecyclerViewAdapter.ViewHolder> {

    private List<ResponseCodigoQR> mValues;
    private Context ctx;

    public MyCodigoRecyclerViewAdapter(Context context,List<ResponseCodigoQR> items) {
        mValues = items;
        ctx = context;
    }

    //carga el lazout del que elemento que vamos a diseñar de la lista
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_codigo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(mValues!=null){

        holder.mItem = mValues.get(position);

        holder.textPuntos.setText(holder.mItem.getPrecio()+" Puntos");
        String foto = holder.mItem.getUrl();
            if(!foto.equals("")){
                Glide.with(ctx)
                        .load(foto)
                        .into(holder.imagenQR);
            }
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = holder.mItem.getUrl();


                Intent i = new Intent(v.getContext(), MostrarCodigoQR.class);
                i.putExtra("IMG",url);
                v.getContext().startActivity(i);

            }
        });


    }



    public void setData(List<ResponseCodigoQR> codigoQRS){
        this.mValues = codigoQRS;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if(mValues!= null){
            return mValues.size();
        }
        else {
            return 0;
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ResponseCodigoQR mItem;
        public final ImageView imagenQR;
        public final TextView  textPuntos;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imagenQR =  view.findViewById(R.id.imageViewCodigoQR);
            textPuntos =  view.findViewById(R.id.textPrecioQR);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textPuntos.getText() + "'";
        }
    }
}
