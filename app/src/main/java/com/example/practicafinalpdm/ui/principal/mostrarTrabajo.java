package com.example.practicafinalpdm.ui.principal;

import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicafinalpdm.R;
import com.example.practicafinalpdm.adaptadores.AdaptadorArtista;
import com.example.practicafinalpdm.adaptadores.AdaptadorComentarios;
import com.example.practicafinalpdm.adaptadores.AdaptadorTrabajos;
import com.example.practicafinalpdm.controller.BDExposiciones;
import com.example.practicafinalpdm.modelos.Comentario;
import com.example.practicafinalpdm.modelos.Trabajo;

import java.util.ArrayList;

public class mostrarTrabajo extends Fragment {

    private BDExposiciones bd;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private RecyclerView recyclerView;
    private AdaptadorComentarios adapter;
    private View root;
    private ArrayList<Trabajo> listaTrabajos;
    private ArrayList<Comentario> listaComentarios;
    private TextView nombreTrabajo, descripcionTrabajo, tamanioTrabajo, pesoTrabajo;
    private ImageView imagenTrabajo;

    public static mostrarTrabajo newInstance() {
        return new mostrarTrabajo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.mostrar_trabajo_fragment, container, false);
        bd = new BDExposiciones(getContext());
        nombreTrabajo = root.findViewById(R.id.etNombreMostrarTrabajo);
        descripcionTrabajo = root.findViewById(R.id.etDescMostrarTrabajo);
        tamanioTrabajo = root.findViewById(R.id.tamanioTrabajo);
        pesoTrabajo = root.findViewById(R.id.pesoTrabajo);
        imagenTrabajo = root.findViewById(R.id.fotoTrabajo);
        recyclerView = root.findViewById(R.id.recyclerComentarios);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        String[] nombreTrabajo1 = new String[1];
        nombreTrabajo1[0] = preferences.getString("nombreTrabajo", "");

        Trabajo trabajo =  bd.consultarTrabajo(nombreTrabajo1);
        nombreTrabajo.setText(trabajo.getNombreTrabajo());
        descripcionTrabajo.setText(trabajo.getDescripcion());
        tamanioTrabajo.setText(trabajo.getTamanio());
        pesoTrabajo.setText(trabajo.getPeso());
        //imagenTrabajo.setImageDrawable(R.drawable.trabajo1);




        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerComentarios);

//        imagenTrabajo.setImageIcon(R.drawable.ic_menu_camera);

        listaComentarios = bd.consultarComentarios(nombreTrabajo1);
        LinearLayoutManager lym = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lym);
        adapter = new AdaptadorComentarios(getContext(), listaComentarios);
        recyclerView.setAdapter(adapter);
        adapter.refrescar();

    }

}