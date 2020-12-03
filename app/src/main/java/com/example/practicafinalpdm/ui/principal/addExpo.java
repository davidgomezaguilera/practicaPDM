package com.example.practicafinalpdm.ui.principal;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicafinalpdm.R;
import com.example.practicafinalpdm.controller.BDExposiciones;
import com.example.practicafinalpdm.modelos.Exposicion;

import java.util.Date;

public class addExpo extends Fragment {

    private Button addExpo;
    private View root;
    private EditText idExpo, nombreExpo, descExpo, fechaIni, fechaFin;
    private BDExposiciones bd;

    public static addExpo newInstance() {
        return new addExpo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.add_expo_fragment, container, false);
        addExpo = root.findViewById(R.id.btAddExpoFinal);
        idExpo = root.findViewById(R.id.etAddIdExpo);
        nombreExpo = root.findViewById(R.id.etAddNombreExpo);
        descExpo = root.findViewById(R.id.etAddDescExpo);
        fechaIni = root.findViewById(R.id.etAddFechaIn);
        fechaFin = root.findViewById(R.id.etAddFechaFi);
        bd = new BDExposiciones(getContext());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel

        addExpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date fechaI = new Date(fechaIni.getText().toString());
                Date fechaF = new Date(fechaFin.getText().toString());
                Exposicion nueva = new Exposicion(idExpo.getText().toString(), nombreExpo.getText().toString(), descExpo.getText().toString(), fechaI, fechaF);
                if(bd.insertarExposiciones(nueva)!=-1){

                    Toast.makeText(getContext(),"Exposicion "+ idExpo.getText().toString() + " insertada con exito", Toast.LENGTH_LONG).show();

                }
                bd.close();
            }
        });
    }

}