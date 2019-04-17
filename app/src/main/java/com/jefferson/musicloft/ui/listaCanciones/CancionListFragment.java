package com.jefferson.musicloft.ui.listaCanciones;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jefferson.musicloft.R;
import com.jefferson.musicloft.data.MusicLoftViewModel;
import com.jefferson.musicloft.retrofit.response.ResponseCancion;


import java.util.List;


public class CancionListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    MyCancionRecyclerViewAdapter adapter;
    List<ResponseCancion> cancionList;
    MusicLoftViewModel musicLoftViewModel;
    SwipeRefreshLayout swipeRefreshLayout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CancionListFragment() {
    }

    //viene por defecto
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CancionListFragment newInstance(int columnCount) {
        CancionListFragment fragment = new CancionListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dependecia, para seguir con la arquitectura
        musicLoftViewModel = ViewModelProviders.of(getActivity())
                .get(MusicLoftViewModel.class);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cancion_list, container, false);

        // Set the adapter
            Context context = view.getContext();
            recyclerView  =  view.findViewById(R.id.list);

            swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeRefreshLayout.setRefreshing(true);
                    swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAmarillo));
                    cargarNewData();
                }
            });

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //relaciona los componenentes del elemento de la lista con los datos

            adapter=  new MyCancionRecyclerViewAdapter(getActivity(),cancionList,this.getFragmentManager());
            recyclerView.setAdapter(adapter);

            cargarDataAdapter();



        return view;
    }



    private void cargarDataAdapter() {

//Observer esta pendiente de los cambios
        musicLoftViewModel.getCanciones().observe(getActivity(), new Observer<List<ResponseCancion>>() {
            @Override
            public void onChanged(@Nullable List<ResponseCancion> responseCancions) {
                cancionList = responseCancions;

                adapter.setData(cancionList);
            }
        });
    }

    private void cargarNewData() {

//Observer esta pendiente de los cambios
        musicLoftViewModel.getNewCanciones().observe(getActivity(), new Observer<List<ResponseCancion>>() {
            @Override
            public void onChanged(@Nullable List<ResponseCancion> responseCancions) {
                cancionList = responseCancions;
                swipeRefreshLayout.setRefreshing(false);
                adapter.setData(cancionList);
                //Elimina este observador para no llamar dos veces al servidor
                musicLoftViewModel.getNewCanciones().removeObserver(this);
            }
        });
    }


}
