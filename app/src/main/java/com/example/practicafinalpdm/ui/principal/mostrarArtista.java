package com.example.practicafinalpdm.ui.principal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.practicafinalpdm.R;
import com.example.practicafinalpdm.controller.BDExposiciones;
import com.example.practicafinalpdm.modelos.Artista;
import com.example.practicafinalpdm.modelos.Exponen;

import java.util.ArrayList;

public class mostrarArtista extends Fragment {

    private BDExposiciones bd;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextView nombreArtista;
    private Button botonMostrarTrabajo,botonLlamarMovilTrabajo;

    public static mostrarArtista newInstance() {
        return new mostrarArtista();
    }

    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.mostrar_artista_fragment, container, false);
        bd = new BDExposiciones(getContext());
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();
        nombreArtista = root.findViewById(R.id.tvNombreArtista);
        botonMostrarTrabajo = root.findViewById(R.id.btMostrarTrabajos);
        botonLlamarMovilTrabajo = root.findViewById(R.id.btMovilTrabajo);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel

        String[] dni = new String[1];
        dni[0] = preferences.getString("dniPasaporte","");
        Artista mostrar = bd.consultarArtista(dni);

        nombreArtista.setText(mostrar.getNombre());


        botonMostrarTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(root).navigate(R.id.nav_mostrarTrabajosArtista);


            }
        });
        botonLlamarMovilTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] dni = new String[1];
                dni[0] = preferences.getString("dniPasaporte", "");
                Artista artista = bd.consultarArtista(dni);
                Intent llamada = new Intent(Intent.ACTION_CALL);
                llamada.setData(Uri.parse("tel:"+artista.getMovilTrabajo()));
                startActivity(llamada);
            }
        });
    }

}