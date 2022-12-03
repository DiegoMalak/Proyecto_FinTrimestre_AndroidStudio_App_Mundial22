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

    private EditText etEquipoConsulta;
    private Button btnConsultaSeleccion;
    private FrameLayout fl1, fl2, fl3, fl4, fl5, fl6, fl7;
    private int modo = 0; // 0 para mi va a ser que tengo boton seleccion, y 1 que tengo texto limpiar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        etEquipoConsulta = findViewById(R.id.etEquipoConsulta);
        btnConsultaSeleccion = findViewById(R.id.btnConsultaSeleccion);
        fl1 = findViewById(R.id.flConsulta1);
        fl2 = findViewById(R.id.flConsulta2);
        fl3 = findViewById(R.id.flConsulta3);
        fl4 = findViewById(R.id.flConsulta4);
        fl5 = findViewById(R.id.flConsulta5);
        fl6 = findViewById(R.id.flConsulta6);
        fl7 = findViewById(R.id.flConsulta7);

        FrameLayout[] fls = {fl1, fl2, fl3, fl4, fl5, fl6, fl7};

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

                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            for (int i = 0; i < partidosDelPais.size(); i++) {
                                Resultado resultadoPartidoPais = partidosDelPais.get(i);
                                ResultadoFragment resultadoFragment = ResultadoFragment.newInstance(resultadoPartidoPais);
                                ft.add(fls[i].getId(), resultadoFragment);
                            }
                            ft.addToBackStack(null);
                            ft.commit();
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

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        FrameLayout[] fls = {fl1, fl2, fl3, fl4, fl5, fl6, fl7};
        for (FrameLayout fl : fls) {
            ResultadoFragment resultadoFragment = (ResultadoFragment) fm.findFragmentById(fl.getId());
            if (resultadoFragment != null) {
                ft.remove(resultadoFragment);
            }
        }

        ft.commit();
    }
}