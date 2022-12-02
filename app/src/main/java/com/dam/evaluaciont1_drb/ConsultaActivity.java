package com.dam.evaluaciont1_drb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dam.evaluaciont1_drb.datos.ListaResultados;
import com.dam.evaluaciont1_drb.datos.Resultado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {

    private EditText etEquipoConsulta;
    private Button btnConsultaSeleccion;
    private int modo = 0; // 0 para mi va a ser que tengo boton seleccion, y 1 que tengo texto limpiar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        etEquipoConsulta = findViewById(R.id.etEquipoConsulta);
        btnConsultaSeleccion = findViewById(R.id.btnConsultaSeleccion);

        ActivityResultLauncher<Intent> mGetContentPais =
            registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            //Aquí se procesa el resultado de la actividad que se ha lanzado.
                            //En este caso, el resultado es el nombre del país seleccionado.
                            String team = result.getData().getStringExtra("equipo");
                            etEquipoConsulta.setText(team);

                            btnConsultaSeleccion.setText(R.string.txt_btn_limpiarDatos_consulta);
                            modo = 1;

                            ListaResultados lista = new ListaResultados();
                            ArrayList<Resultado> partidosDelPais = lista.getResultadosPorPais(team);

                            // rellenar la interfaz
                            Log.d("DIEGO", Arrays.toString(partidosDelPais.toArray()));
                        }
                    }
                }
            );

        btnConsultaSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modo == 1) {
                    limpiar();
                    btnConsultaSeleccion.setText(R.string.txt_btn_seleccionar);
                    modo = 0;
                }
                else if (modo == 0) {
                    mGetContentPais.launch(new Intent(getApplicationContext(), SeleccionActivity.class));
                }
            }
        });
    }

    private void limpiar() {
        etEquipoConsulta.setText("");
    }
}