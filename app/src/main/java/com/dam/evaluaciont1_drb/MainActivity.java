package com.dam.evaluaciont1_drb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // No declaro el ImageView porque no lo voy a usar.
    private Button btnRegistro;
    private Button btnConsulta;

    // No declaro el TextView porque no lo voy a usar.

    // Hacemos el OnCreate de la actividad principal.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos los elementos de la interfaz principal.
        btnRegistro = findViewById(R.id.btnRegistrar);
        btnConsulta = findViewById(R.id.btnConsultar);

        // Creamos el Listener del boton de registro.
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos el Intent para ir a la actividad de registro.
                Intent i = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(i);
            }
        });

        // Creamos el Listener del boton de consulta.
        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos el Intent para ir a la actividad de consulta.
                Intent i = new Intent(getApplicationContext(), ConsultaActivity.class);
                startActivity(i);
            }
        });

    }


}