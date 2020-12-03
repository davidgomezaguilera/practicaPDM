package com.example.practicafinalpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.prefs.Preferences;

public class login extends AppCompatActivity {

    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor;
    private EditText usuario, contrasenia;
    private Button botonIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferencias.edit();
        usuario = findViewById(R.id.etUsuario);
        contrasenia = findViewById(R.id.etContra);
        botonIniciar = findViewById(R.id.btIniciarSesion);
        Intent intent = new Intent(this, CambiarContra.class);
        Intent intent2 = new Intent(this, MainActivity.class);

        botonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(preferencias.getString("contrasenia",""));
                if(preferencias.getString("contrasenia","").equals("")){
                    startActivity(intent);
                }else if(preferencias.getString("contrasenia", "").equals(contrasenia.getText().toString())){
                    startActivity(intent2);
                }


            }
        });



    }
}