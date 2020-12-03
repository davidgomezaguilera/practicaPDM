package com.example.practicafinalpdm.addArtista;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicafinalpdm.R;
import com.example.practicafinalpdm.controller.BDExposiciones;
import com.example.practicafinalpdm.modelos.Artista;
import com.example.practicafinalpdm.modelos.Exponen;
import com.example.practicafinalpdm.modelos.Exposicion;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class addArtista extends Fragment {

    private Button addArtistaFinal;
    private TextView etNombre;
    private TextView etDireccion;
    private TextView etPoblacion;
    private TextView etProvincia;
    private TextView etPais;
    private TextView etMovilTrabajo;
    private TextView etMovilPersonal;
    private TextView etTelefonoFijo;
    private TextView etEmail;
    private TextView etWebBlog;
    private TextView etFechaNacimiento;
    private TextView etDNI;
    private BDExposiciones bd;
    private Context context;
    private Spinner spinner;

    public static addArtista newInstance() {
        return new addArtista();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_artista_fragment, container, false);
        context = getContext();
        addArtistaFinal = root.findViewById(R.id.btaddArtistaFinal);
        etNombre = root.findViewById(R.id.etNombre);
        etDireccion = root.findViewById(R.id.etDireccion);
        etPoblacion = root.findViewById(R.id.etPoblacion);
        etProvincia = root.findViewById(R.id.etProvincia);
        etPais = root.findViewById(R.id.etPais);
        etMovilTrabajo = root.findViewById(R.id.etMovilTrabajo);
        etMovilPersonal = root.findViewById(R.id.etMovilPersonal);
        etTelefonoFijo = root.findViewById(R.id.etTelefonoFijo);
        etEmail = root.findViewById(R.id.etEmail);
        etWebBlog = root.findViewById(R.id.etFechaNacimiento);
        etFechaNacimiento = root.findViewById(R.id.etFechaNacimiento);
        etDNI = root.findViewById(R.id.etDNI);
        spinner = root.findViewById(R.id.spinner);
        bd = new BDExposiciones(context);

        ArrayList<Exposicion> listaExposiciones = bd.consultarExpo();
        String[] idsExpo = new String[listaExposiciones.size()];
        for(int i=0; i<listaExposiciones.size();i++){
            idsExpo[i] = listaExposiciones.get(i).getIdExposicion();
        }
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, idsExpo));




        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        addArtistaFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date fecha = new Date();

                try {
                    ArrayList<Exposicion> listaExposiciones = bd.consultarExpo();



                    Artista artista = new Artista(etDNI.getText().toString(), etNombre.getText().toString(), etDireccion.getText().toString(), etPoblacion.getText().toString(), etProvincia.getText().toString(), etPais.getText().toString(), etMovilTrabajo.getText().toString(), etMovilPersonal.getText().toString(), etTelefonoFijo.getText().toString(), etEmail.getText().toString(),etWebBlog.getText().toString(), new Date());
                    String idExpo = spinner.getSelectedItem().toString();
                    Exponen exponen = new Exponen(idExpo,artista.getDniPasaporte());
                    if(bd.insertarArtista(artista)!=-1){
                        if(bd.insertarExponen(exponen)!=-1){
                            Toast.makeText(getContext(),"Artista "+artista.getNombre()+" insertado correctamente.",Toast.LENGTH_LONG).show();

                        }
                    }
                    //System.out.println(bd.insertarArtista(artista));
                    bd.close();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}