package com.example.practicafinalpdm.ui.principal;

import androidx.lifecycle.ViewModelProvider;

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

import com.example.practicafinalpdm.R;
import com.example.practicafinalpdm.adaptadores.AdaptadorArtista;
import com.example.practicafinalpdm.adaptadores.AdaptadorExpo;
import com.example.practicafinalpdm.adaptadores.AdaptadorTrabajos;
import com.example.practicafinalpdm.controller.BDExposiciones;
import com.example.practicafinalpdm.modelos.Trabajo;

import java.util.ArrayList;

public class mostrarTrabajosArtista extends Fragment {

    private BDExposiciones bd;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private RecyclerView recyclerView;
    private AdaptadorTrabajos adapter;
    private View root;
    private ArrayList<Trabajo> listaTrabajos;

    public static mostrarTrabajosArtista newInstance() {
        return new mostrarTrabajosArtista();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.mostrar_trabajos_artista_fragment, container, false);

        bd = new BDExposiciones(getContext());
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewTrabajos);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();




        return root;



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel

        String[] dni = new String[1];
        dni[0] = preferences.getString("dniPasaporte","");

        listaTrabajos = bd.consultarTrabajos(dni);
        LinearLayoutManager lym = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lym);
        adapter = new AdaptadorTrabajos(getContext(),listaTrabajos);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int posicion = recyclerView.getChildAdapterPosition(v);
                Navigation.findNavController(root).navigate(R.id.nav_mostrarTrabajo);
                editor.putString("nombreTrabajo",listaTrabajos.get(posicion).getNombreTrabajo());
                editor.commit();
            }
        });


        adapter.refrescar();


    }

}