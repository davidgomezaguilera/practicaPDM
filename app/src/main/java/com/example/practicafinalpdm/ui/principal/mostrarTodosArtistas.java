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
import com.example.practicafinalpdm.controller.BDExposiciones;
import com.example.practicafinalpdm.modelos.Artista;
import com.example.practicafinalpdm.modelos.Exponen;

import java.util.ArrayList;

public class mostrarTodosArtistas extends Fragment {

    private BDExposiciones bd;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private RecyclerView recyclerView;
    private AdaptadorArtista adapter;
    private View root;

    public static mostrarTodosArtistas newInstance() {
        return new mostrarTodosArtistas();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.mostrar_todos_artistas_fragment, container, false);
        bd = new BDExposiciones(getContext());
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewArtistas);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        ArrayList<Artista> listaArtista = new ArrayList<Artista>();
        String[] array = new String[1];

        array[0] = preferences.getString("idExpo","");
        ArrayList<Exponen> listaExponen = bd.consultarExponen(array);

        for(Exponen exponen: listaExponen){

            String dniArtista = exponen.getDniArtista();
            String[] arrayDni = new String[1];
            arrayDni[0] = dniArtista;
            listaArtista.add(bd.consultarArtista(arrayDni));

        }
        LinearLayoutManager lym = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lym);
        adapter = new AdaptadorArtista(getContext(), listaArtista);
        recyclerView.setAdapter(adapter);
        adapter.refrescar();
        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = recyclerView.getChildAdapterPosition(v);
                Navigation.findNavController(root).navigate(R.id.nav_mostrarArtista);
                editor.putString("dniPasaporte", listaArtista.get(posicion).getDniPasaporte());
                editor.commit();
            }
        });

    }

}