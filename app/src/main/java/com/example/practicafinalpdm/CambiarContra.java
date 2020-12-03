package com.example.practicafinalpdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CambiarContra extends AppCompatActivity {

    private EditText contraNueva;
    private Button cambiarContra;
    private SharedPreferences pre;
    private SharedPreferences.Editor e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contra);
        pre = PreferenceManager.getDefaultSharedPreferences(this);
        e = pre.edit();
        contraNueva = findViewById(R.id.etCambiarContra);
        cambiarContra = findViewById(R.id.btCambiarContra);

        Intent intent = new Intent(this, login.class);


        cambiarContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                e.putString("contrasenia", contraNueva.getText().toString());
                e.commit();

                startActivity(intent);
            }
        });

    }
}