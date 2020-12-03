package com.example.practicafinalpdm.ui.principal;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.practicafinalpdm.R;
import com.example.practicafinalpdm.adaptadores.AdaptadorExpo;
import com.example.practicafinalpdm.controller.BDExposiciones;
import com.example.practicafinalpdm.modelos.Artista;
import com.example.practicafinalpdm.modelos.Comentario;
import com.example.practicafinalpdm.modelos.Exponen;
import com.example.practicafinalpdm.modelos.Exposicion;
import com.example.practicafinalpdm.modelos.Trabajo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class principal extends Fragment {


    public static principal newInstance() {
        return new principal();
    }

    private Button botonAltaArtista;
    private Button botonAltaExposicion;
    private View root;
    //asignando el recyclerView a nuestra vista principal
    private ArrayList<Exposicion> listaExpo;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorExpo adapter;
    private BDExposiciones bd;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Button addExpoFinal;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        root =  inflater.inflate(R.layout.principal_fragment, container, false);
        bd = new BDExposiciones(getContext());
        botonAltaArtista = root.findViewById(R.id.btAltaArtista);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        botonAltaExposicion = root.findViewById(R.id.btAddExpo);
        addExpoFinal = root.findViewById(R.id.btAddExpoFinal);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();
        botonAltaArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation con su metodo findnavcontroller, le paso un View al que le hago .navigate le paso el fragment que he creado en mobile_navigation.xml
                Navigation.findNavController(root).navigate(R.id.nav_addArtista);
            }
        });
        botonAltaExposicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.nav_addExpo);
            }
        });







        return root;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        try{
            //bd.insertarExposiciones(new Exposicion("1","nombre","descripcion",new SimpleDateFormat("yyyy-MM-dd").parse("1998-09-29"), new Date()));
            //bd.insertarArtista(new Artista("1", "Artista1", "direccionArtista1", "poblacionArtista1", "provinciaArtista1", "paisArtista1", "123456789", "123456789","123456789", "artista1@gmail.com", "artista1.com", new Date("1998-09-29")));
            /*for(int i = 0; i!=10; i++){
                if(bd.insertarExposiciones(new Exposicion(""+i,"nombre"+i,"descripcion"+i,new Date(), new Date())) != -1 && bd.insertarArtista(new Artista(""+i, "Artista"+i, "direccionArtista"+i, "poblacionArtista"+i, "provinciaArtista"+i, "paisArtista"+i, "123456789", "123456789","123456789", "artista1@gmail.com", "artista"+i+".com", new Date())) != -1){
                    if(bd.insertarExponen(new Exponen(""+i,""+i)) != -1){
                        System.out.println("Todo correcto");
                    }

                }
            }*/

            //Trabajo trabajo = new Trabajo("Trabajo1", "DescripcionTrabajo1", "30", "20" ,"1", "url");
            //bd.insertarTrabajo(trabajo);
            //Comentario comentario = new Comentario("1","Trabajo1","Muy buena obra, mis dieces.");
            //bd.insertarComentario(comentario)
            }catch (Exception e){
            e.printStackTrace();
        }
        listaExpo = bd.consultarExpo();
        //System.out.println(bd.consultarExpo());
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdaptadorExpo(getContext(), listaExpo);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = recyclerView.getChildAdapterPosition(v);
                Navigation.findNavController(root).navigate(R.id.nav_mostrarTodosArtistas);
                editor.putString("idExpo", listaExpo.get(posicion).getIdExposicion());
                editor.commit();
            }
        });
        adapter.refrescar();

    }

}