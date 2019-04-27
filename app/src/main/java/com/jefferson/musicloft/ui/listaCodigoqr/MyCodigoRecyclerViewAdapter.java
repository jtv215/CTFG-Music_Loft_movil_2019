package com.jefferson.musicloft.ui.listaCodigoqr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.jefferson.musicloft.R;
import com.jefferson.musicloft.retrofit.response.ResponseCodigoQR;


import java.util.List;


public class MyCodigoRecyclerViewAdapter extends RecyclerView.Adapter<MyCodigoRecyclerViewAdapter.ViewHolder> {

    private List<ResponseCodigoQR> mValues;
    private Context ctx;


    public MyCodigoRecyclerViewAdapter(Context context,List<ResponseCodigoQR> items) {
        mValues = items;
        ctx = context;
    }

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

           /* if(!foto.equals("")){
                Glide.with(ctx)
                        .load(foto)
                        .into(holder.imagenQR);
            }*/
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = holder.mItem.getUrl();

                Intent i = new Intent(v.getContext(), MostrarCodigoQR.class);
                i.putExtra("idcodigoQR",holder.mItem.getId());
                i.putExtra("puntos",holder.mItem.getPrecio());
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
