package com.dam.evaluaciont1_drb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.dam.evaluaciont1_drb.datos.ListaResultados;
import com.dam.evaluaciont1_drb.datos.Resultado;
import com.dam.evaluaciont1_drb.fragmentos.ResultadoFragment;

import java.util.ArrayList;

public class ConsultaActivity extends AppCompatActivity {

    // Declaramos los elementos de la interfaz de consulta.
    private EditText etEquipoConsulta;
    private Button btnConsultaSeleccion;
    private FrameLayout fl1, fl2, fl3, fl4, fl5, fl6, fl7;
    private int modo = 0; // 0 para mi va a ser que tengo boton seleccion, y 1 que tengo texto limpiar.
    // Utilizamos el 'modo' como si fuera un alternador.

    // Hacemos el OnCreate de la actividad de consulta.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        // Inicializamos los elementos de la interfaz de consulta.
        etEquipoConsulta = findViewById(R.id.etEquipoConsulta);
        btnConsultaSeleccion = findViewById(R.id.btnConsultaSeleccion);
        fl1 = findViewById(R.id.flConsulta1);
        fl2 = findViewById(R.id.flConsulta2);
        fl3 = findViewById(R.id.flConsulta3);
        fl4 = findViewById(R.id.flConsulta4);
        fl5 = findViewById(R.id.flConsulta5);
        fl6 = findViewById(R.id.flConsulta6);
        fl7 = findViewById(R.id.flConsulta7);

        // Creamos un array de los FrameLayouts para poder iterar sobre ellos.
        FrameLayout[] fls = {fl1, fl2, fl3, fl4, fl5, fl6, fl7};

        // Hacemos el ActivityResultLauncher para poder recibir el resultado de la actividad de selecci??n.
        ActivityResultLauncher<Intent> mGetContentPais =
            registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Si el resultado es correcto, obtenemos el intent y el extra.
                        if (result.getResultCode() == RESULT_OK) {
                            //Aqu?? se procesa el resultado de la actividad que se ha lanzado.
                            //En este caso, el resultado es el nombre del pa??s seleccionado.
                            String team = result.getData().getStringExtra("equipo");
                            etEquipoConsulta.setText(team);

                            btnConsultaSeleccion.setText(R.string.txt_btn_limpiarDatos_consulta);
                            modo = 1;

                            // Creamos un array de resultados para almacenar los resultados que
                            // coincidan con el equipo seleccionado.
                            ListaResultados lista = new ListaResultados();
                            ArrayList<Resultado> partidosDelPais = lista.getResultadosPorPais(team);

                            // Iteramos sobre los FrameLayouts para mostrar los resultados.
                            // Con esto vamos a mostrar los resultados en los FrameLayouts mediante
                            // los fragmentos que se van a mostrar seg??n el n??mero de resultados.
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            // Creamos un contador para saber en qu?? FrameLayout estamos y poder
                            // mostrar el fragmento correspondiente. De esta forma, si hay 3 resultados
                            // se mostrar??n en los 3 primeros FrameLayouts.
                            for (int i = 0; i < partidosDelPais.size(); i++) {
                                Resultado resultadoPartidoPais = partidosDelPais.get(i);
                                // Creo el fragment y le paso el resultado del partido. (Clase ResultadoFragment).
                                ResultadoFragment resultadoFragment = ResultadoFragment.newInstance(resultadoPartidoPais);
                                // A??ado el fragment a la transacci??n.
                                ft.add(fls[i].getId(), resultadoFragment);
                            }
                            // El addToBackStack() es para que cuando pulsas hacia atr??s deshaga el cambio del fragment.
                            // En este caso lo quito porque me parece m??s adecuado que vuelva al inicio.
                            //ft.addToBackStack(null);
                            ft.commit();
                        }
                    }
                }
            );

        // Creamos el listener del bot??n de selecci??n.
        btnConsultaSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si el modo es 0, es decir, que tenemos el bot??n de selecci??n, lanzamos la actividad
                // de selecci??n, si no, es decir, que tenemos el bot??n de limpiar, limpiamos los datos.
                if (modo == 1) {
                    limpiar();
                    btnConsultaSeleccion.setText(R.string.txt_btn_seleccionar);
                    modo = 0;
                }
                else if (modo == 0) {
                    // Lanzamos la actividad de selecci??n.
                    mGetContentPais.launch(new Intent(getApplicationContext(), SeleccionActivity.class));
                }
            }
        });
    }

    // M??todo para limpiar los datos.
    private void limpiar() {
        // Limpiamos el EditText.
        etEquipoConsulta.setText("");

        // Creamos un array de los FrameLayouts para poder iterar sobre ellos.
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // Iteramos sobre los FrameLayouts para limpiarlos.
        FrameLayout[] fls = {fl1, fl2, fl3, fl4, fl5, fl6, fl7};
        // Creamos un contador para cuantos fragmentos hay mostrados y poder borrarlos.
        for (FrameLayout fl : fls) {
            ResultadoFragment resultadoFragment = (ResultadoFragment) fm.findFragmentById(fl.getId());
            if (resultadoFragment != null) {
                ft.remove(resultadoFragment);
            }
        }
        ft.commit();
    }
}