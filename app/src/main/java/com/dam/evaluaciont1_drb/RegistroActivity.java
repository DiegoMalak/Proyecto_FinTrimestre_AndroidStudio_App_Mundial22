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

    // Declaramos los elementos de la interfaz de registro.
    private EditText etFecha;
    private EditText etFase;
    private EditText etEquipo1;
    private Button btnSeleccionarEquipo1;
    private EditText etEquipo2;
    private Button btnSeleccionarEquipo2;
    private EditText etGolesEquipo1;
    private EditText etGolesEquipo2;

    // Declaramos los botonoes de la interfaz de Guardar y Limpiar.
    private Button btnGuardar;
    private Button btnLimpiar;

    // Creamos un ArrayList de las Fases validas para usarlo posteriormente.
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

    // Hacemos el OnCreate de la actividad de registro.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializamos los elementos de la interfaz de registro.
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

        // Creamos el ActivityResultLauncher para el boton de seleccionar equipo 1.
        // Este ActivityResultLauncher nos permite abrir la actividad de seleccionar equipo
        // y recibir el resultado de la misma para el equipo 1.
        ActivityResultLauncher<Intent> mGetContentSelect1 =
            registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Comprobamos que el resultado de la actividad de seleccionar equipo
                        // no sea nulo y que el resultado sea OK, en caso de que sea asi
                        // obtenemos el nombre del equipo seleccionado y lo mostramos en el
                        // EditText correspondiente.
                        if (result.getResultCode() == RESULT_OK) {
                            //Aquí se procesa el resultado de la actividad que se ha lanzado.
                            //En este caso, el resultado es el nombre del país seleccionado.
                            String team1 = result.getData().getStringExtra("equipo");
                            etEquipo1.setText(team1);
                        }
                    }
                }
            );

        // Creamos el ActivityResultLauncher para el boton de seleccionar equipo 2.
        // Este ActivityResultLauncher nos permite abrir la actividad de seleccionar equipo
        // y recibir el resultado de la misma para el equipo 2.
        ActivityResultLauncher<Intent> mGetContentSelect2 =
                registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            // Comprobamos que el resultado de la actividad de seleccionar equipo
                            // no sea nulo y que el resultado sea OK, en caso de que sea asi
                            // obtenemos el nombre del equipo seleccionado y lo mostramos en el
                            // EditText correspondiente.
                            if (result.getResultCode() == RESULT_OK) {
                                //Aquí se procesa el resultado de la actividad que se ha lanzado.
                                //En este caso, el resultado es el nombre del país seleccionado.
                                String team2 = result.getData().getStringExtra("equipo");
                                etEquipo2.setText(team2);
                            }
                        }
                    }
                );

        // Creamos el Listener para el boton de seleccionar equipo 1.
        btnSeleccionarEquipo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos un Intent para abrir la actividad de seleccionar equipo.
                mGetContentSelect1.launch(new Intent(getApplicationContext(), SeleccionActivity.class));
            }
        });

        // Creamos el Listener para el boton de seleccionar equipo 2.
        btnSeleccionarEquipo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos un Intent para abrir la actividad de seleccionar equipo.
                mGetContentSelect2.launch(new Intent(getApplicationContext(), SeleccionActivity.class));
            }
        });

        // Creamos el Listener para el boton de guardar.
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        // Creamos el Listener para el boton de limpiar.
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarUI();
            }
        });
    }

    // Creamos el metodo para guardar los datos.
    private void guardar() {
        // Comprobacion de campos vacios, en caso de que haya alguno vacio, se muestra un mensaje de error
        // y se corta la ejecucion del metodo con un return.
        if (etFecha.getText().toString().trim().isEmpty()
                || etFase.getText().toString().trim().isEmpty()
                || etEquipo1.getText().toString().trim().isEmpty()
                || etEquipo2.getText().toString().trim().isEmpty()
                || etGolesEquipo1.getText().toString().trim().isEmpty()
                || etGolesEquipo2.getText().toString().trim().isEmpty()) {
            // Mostramos un mensaje de error en caso de que haya algun campo vacio.
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
            // Mostramos un mensaje de error en caso de que la fase introducida no sea valida.
            Toast.makeText(getApplicationContext(),
                    R.string.registro_fase_incorrecta, Toast.LENGTH_LONG).show();
            return;
        }

        // Cambio de comprobacion para que compruebe que los dos equipos introducidos no son iguales.
        // En caso de que sean iguales, se muestra un mensaje de error y se corta la ejecucion
        // del metodo con un return.
        if (etEquipo1.getText().toString().equals(etEquipo2.getText().toString())) {
            // Mostramos un mensaje de error en caso de que los dos equipos sean iguales.
            Toast.makeText(getApplicationContext(),
                    R.string.registro_equipos_distintos, Toast.LENGTH_LONG).show();
            return;
        }

        // Una vez comprobado que todos los campos estan rellenos y que la fase introducida es valida,
        // interactuamos con los datos para simular el guardado, en este caso, simplemente mostramos
        // un mensaje de que los datos se han guardado correctamente y limpiamos la UI.
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

    // Creamos el metodo para limpiar la UI.
    private void limpiarUI() {
        etFecha.setText("");
        etFase.setText("");
        etEquipo1.setText("");
        etEquipo2.setText("");
        etGolesEquipo1.setText("");
        etGolesEquipo2.setText("");
    }
}