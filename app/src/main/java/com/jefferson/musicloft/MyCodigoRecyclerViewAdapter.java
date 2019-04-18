package com.jefferson.musicloft;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jefferson.musicloft.CodigoListFragment.OnListFragmentInteractionListener;
import com.jefferson.musicloft.retrofit.response.ResponseCancion;
import com.jefferson.musicloft.retrofit.response.ResponseCodigoQR;


import java.util.List;


public class MyCodigoRecyclerViewAdapter extends RecyclerView.Adapter<MyCodigoRecyclerViewAdapter.ViewHolder> {

    private final List<ResponseCodigoQR> mValues;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
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
