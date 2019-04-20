package com.jefferson.musicloft.ui.principal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jefferson.musicloft.R;
import com.jefferson.musicloft.common.Constantes;
import com.jefferson.musicloft.common.MyApp;
import com.jefferson.musicloft.common.SharedPreferencedManager;
import com.jefferson.musicloft.retrofit.AuthMusicLoftClient;
import com.jefferson.musicloft.retrofit.AuthTMusicLoftService;
import com.jefferson.musicloft.retrofit.response.ResponseEstablecimiento;
import com.jefferson.musicloft.ui.principal.DashboardActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeleccionarEstablecimiento extends AppCompatActivity {

    AuthTMusicLoftService authTMusicLoftService;
    AuthMusicLoftClient authMusicLoftClient;
    //private List<ResponseEstablecimiento> listEstablecimiento = new ArrayList<>();
    ArrayList<String> nombreEstablecimiento = new ArrayList<>();
    ArrayList<String> idEstablecimiento = new ArrayList<>();
    ArrayList<String> informacionEstablecimiento= new ArrayList<>();

    Spinner spinner;
    Button btnSiguiente;
    TextView txtInformacion;
    int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_establecimiento);
        getSupportActionBar().hide();

        retrofitInit();

        nombreEstablecimiento.add("Seleccione Local");
        btnSiguiente = findViewById(R.id.btnSiguiente);
        txtInformacion = findViewById((R.id.txtInformacion));
        spinner = (Spinner) findViewById(R.id.spinner2);

        cargarEstablecimientos();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombreEstablecimiento);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



    }



    public void cargarEstablecimientos () {


        Call<List<ResponseEstablecimiento>> call = authTMusicLoftService.cargarEstablecimientos();
        call.enqueue(new Callback<List<ResponseEstablecimiento>>() {
            @Override
            public void onResponse(Call<List<ResponseEstablecimiento>> call, Response<List<ResponseEstablecimiento>> response) {

               if(response.isSuccessful()) {
                   List<ResponseEstablecimiento> establecimientoRespuest = response.body();

                   //Suponiendo tener un Listado de objetos:

                   for (int i = 0; i < establecimientoRespuest.size(); i++) {
                       nombreEstablecimiento.add(establecimientoRespuest.get(i).getNombre());
                       idEstablecimiento.add(establecimientoRespuest.get(i).getId());
                       informacionEstablecimiento.add(establecimientoRespuest.get(i).getInformacion());
                   }

                   spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                       @Override
                       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                           posicion= position;
                           String sSelected=parent.getItemAtPosition(position).toString();
                           if(position==0){
                               txtInformacion.setText("Descprición");
                           }else {
                               txtInformacion.setText(informacionEstablecimiento.get(position - 1));
                           }
                       }

                       @Override
                       public void onNothingSelected(AdapterView<?> parent) {

                       }
                   });


                   btnSiguiente.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           if(posicion==0){
                               Toast.makeText(MyApp.geContext(), "Seleccione un local", Toast.LENGTH_SHORT).show();

                           }else{

                               SharedPreferencedManager.setSomeStringValue(Constantes.PREF_ESTABLECIMIENTO,idEstablecimiento.get(posicion-1));
                               SharedPreferencedManager.setSomeStringValue(Constantes.PREF_NOMBRELOCAL,nombreEstablecimiento.get(posicion));

                               Intent intent = new Intent (MyApp.geContext(), DashboardActivity.class);
                               startActivityForResult(intent, 0);
                           }

                       }
                   });

               }else{
                       Toast.makeText(MyApp.geContext(), "Algo a ido mal   ", Toast.LENGTH_SHORT).show();
                   }

               }
            @Override
            public void onFailure(Call<List<ResponseEstablecimiento>> call, Throwable t) {
                Toast.makeText(MyApp.geContext(),"Problemas de Conexión, Inténtelo de nuevo",Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void retrofitInit() {
        authMusicLoftClient= AuthMusicLoftClient.getInstance();
        authTMusicLoftService = authMusicLoftClient.getAuthMusicLoftService();
    }


}
