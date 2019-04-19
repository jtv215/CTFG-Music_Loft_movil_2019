package com.jefferson.musicloft.ui.ListaCodigoqr;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jefferson.musicloft.R;
import com.jefferson.musicloft.data.CodigoQRViewModel;
import com.jefferson.musicloft.retrofit.response.ResponseCodigoQR;

import java.util.List;


public class CodigoListFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    MyCodigoRecyclerViewAdapter adapter;
    List<ResponseCodigoQR> listaCodigoQR;
    CodigoQRViewModel codigoQRViewModel;


    public CodigoListFragment() {
    }

    @SuppressWarnings("unused")
    public static CodigoListFragment newInstance(int columnCount) {
        CodigoListFragment fragment = new CodigoListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        codigoQRViewModel = ViewModelProviders.of(getActivity())
                .get(CodigoQRViewModel.class);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_codigo_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyCodigoRecyclerViewAdapter(getActivity(),listaCodigoQR);
            recyclerView.setAdapter(adapter);

            loadCodigoQRdata();
        }
        return view;
    }



    private void loadCodigoQRdata() {

        codigoQRViewModel.getCodigoQR().observe(getActivity(), new Observer<List<ResponseCodigoQR>>() {
            @Override
            public void onChanged(@Nullable List<ResponseCodigoQR> responseCodigoQRS) {
                listaCodigoQR = responseCodigoQRS;
                adapter.setData(listaCodigoQR);
            }
        });
        adapter = new MyCodigoRecyclerViewAdapter(getActivity(),listaCodigoQR);
        recyclerView.setAdapter(adapter);


    }


}
