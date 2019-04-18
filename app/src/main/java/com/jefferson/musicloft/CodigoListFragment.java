package com.jefferson.musicloft;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jefferson.musicloft.dummy.DummyContent;
import com.jefferson.musicloft.dummy.DummyContent.DummyItem;
import com.jefferson.musicloft.retrofit.response.ResponseCodigoQR;

import java.util.List;


public class CodigoListFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    MyCodigoRecyclerViewAdapter adapter;
    List<ResponseCodigoQR> listaCodigoQR;

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
            loadCodigoQRdata();
        }
        return view;
    }

    private void loadCodigoQRdata() {
        adapter = new MyCodigoRecyclerViewAdapter(getActivity(),listaCodigoQR);
        recyclerView.setAdapter(adapter);
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
