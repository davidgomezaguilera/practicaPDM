package com.example.practicafinalpdm.ui.principal;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practicafinalpdm.CambiarContra;
import com.example.practicafinalpdm.R;

public class cambiarContraFragment extends Fragment {


    public static cambiarContraFragment newInstance() {
        return new cambiarContraFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cambiar_contra_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        Intent intent = new Intent(getContext(), CambiarContra.class);
        startActivity(intent);
    }

}