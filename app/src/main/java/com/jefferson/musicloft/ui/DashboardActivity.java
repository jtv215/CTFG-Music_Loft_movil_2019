package com.jefferson.musicloft.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.jefferson.musicloft.R;

public class DashboardActivity extends AppCompatActivity {

    private TextView inicio;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.playList:
                    return true;
                case R.id.perfil:
                    return true;
                case R.id.codigoqr:
                   return  true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView inicio = findViewById(R.id.inicioDashboard);
        inicio.setText("Jefferson");
       // setSupportActionBar(toolbar);
       getSupportActionBar().hide();



      //  inicio = (TextView) findViewById(R.id.inicioDashboard);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //llama al cancion listFragment para cargar la lista
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer,new CancionListFragment())
                .commit();

    }

}
