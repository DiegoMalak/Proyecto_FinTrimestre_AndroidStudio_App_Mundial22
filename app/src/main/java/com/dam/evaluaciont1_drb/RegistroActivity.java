package com.dam.evaluaciont1_drb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dam.evaluaciont1_drb.datos.ListaResultados;
import com.dam.evaluaciont1_drb.datos.Resultado;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity {

    private EditText etFecha;
    private EditText etFase;
    private EditText etEquipo1;
    private Button btnSeleccionarEquipo1;
    private EditText etEquipo2;
    private Button btnSeleccionarEquipo2;
    private EditText etGolesEquipo1;
    private EditText etGolesEquipo2;

    private Button btnGuardar;
    private Button btnLimpiar;

    private ArrayList<String> fasesValidas =  new ArrayList<String>() {
        {
            add("Fase grupos");
            add("Octavos");
            add("Cuartos");
            add("Semifinales");
            add("Tercer puesto");
            add("Final");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etFecha = findViewById(R.id.etFecha);
        etFase = findViewById(R.id.etFase);
        etEquipo1 = findViewById(R.id.etEquipo1);
        btnSeleccionarEquipo1 = findViewById(R.id.btnSeleccionarEquipo1);
        etEquipo2 = findViewById(R.id.etEquipo2);
        btnSeleccionarEquipo2 = findViewById(R.id.btnSeleccionarEquipo2);
        etGolesEquipo1 = findViewById(R.id.etGolesEquipo1);
        etGolesEquipo2 = findViewById(R.id.etGolesEquipo2);

        btnGuardar = findViewById(R.id.btnGuardarResultado);
        btnLimpiar = findViewById(R.id.btnLimpiarDatos);

        ActivityResultLauncher<Intent> mGetContentSelect1 =
            registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            //Aquí se procesa el resultado de la actividad que se ha lanzado.
                            //En este caso, el resultado es el nombre del país seleccionado.
                            String team1 = result.getData().getStringExtra("equipo");
                            etEquipo1.setText(team1);
                        }
                    }
                }
            );

        ActivityResultLauncher<Intent> mGetContentSelect2 =
                registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK) {
                                //Aquí se procesa el resultado de la actividad que se ha lanzado.
                                //En este caso, el resultado es el nombre del país seleccionado.
                                String team2 = result.getData().getStringExtra("equipo");
                                etEquipo2.setText(team2);
                            }
                        }
                    }
                );

        btnSeleccionarEquipo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContentSelect1.launch(new Intent(getApplicationContext(), SeleccionActivity.class));
            }
        });

        btnSeleccionarEquipo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContentSelect2.launch(new Intent(getApplicationContext(), SeleccionActivity.class));
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarUI();
            }
        });
    }

    private void guardar() {
        // Comprobacion de campos vacios, en caso de que haya alguno vacio, se muestra un mensaje de error
        // y se corta la ejecucion del metodo con un return.
        if (etFecha.getText().toString().trim().isEmpty()
                || etFase.getText().toString().trim().isEmpty()
                || etEquipo1.getText().toString().trim().isEmpty()
                || etEquipo2.getText().toString().trim().isEmpty()
                || etGolesEquipo1.getText().toString().trim().isEmpty()
                || etGolesEquipo2.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.registro_datos_vacios, Toast.LENGTH_LONG).show();
            return;
        }

        // Cambio de comprobacion para saber si la fase introducida es valida,
        // acudo al array de fases validas y compruebo si la fase introducida esta en el array
        // correctamente escrito, usando el metodo contains() de la clase ArrayList.
        // Si no esta en el array, se muestra un mensaje de error y se corta la ejecucion
        // del metodo con un return.
        if (!fasesValidas.contains(etFase.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(),
                    R.string.registro_fase_incorrecta, Toast.LENGTH_LONG).show();
            return;
        }

        // Cambio de comprobacion para que compruebe que los dos equipos introducidos no son iguales.
        // En caso de que sean iguales, se muestra un mensaje de error y se corta la ejecucion
        // del metodo con un return.
        if (etEquipo1.getText().toString().equals(etEquipo2.getText().toString())) {
            Toast.makeText(getApplicationContext(),
                    R.string.registro_equipos_distintos, Toast.LENGTH_LONG).show();
            return;
        }

        //Mostramos un mensaje de éxito en un Toast
        ListaResultados listaResultados = new ListaResultados();
        listaResultados.addResultado(new Resultado(
            etFecha.getText().toString(),
            etFase.getText().toString(),
            etEquipo1.getText().toString(),
            Integer.parseInt(etGolesEquipo1.getText().toString()),
            etEquipo2.getText().toString(),
            Integer.parseInt(etGolesEquipo2.getText().toString())
        ));
        Toast.makeText(this, R.string.java_campos_correctos, Toast.LENGTH_LONG).show();
        //Limpiamos los campos de la UI.
        limpiarUI();
    }

    private void limpiarUI() {
        etFecha.setText("");
        etFase.setText("");
        etEquipo1.setText("");
        etEquipo2.setText("");
        etGolesEquipo1.setText("");
        etGolesEquipo2.setText("");
    }
}