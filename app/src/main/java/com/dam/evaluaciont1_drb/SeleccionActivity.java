package com.dam.evaluaciont1_drb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SeleccionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAlemania, btnArabiaSaudi, btnArgentina, btnAustralia, btnBelgica, btnBrasil;
    private Button btnCamerun, btnCanada, btnCoreaDelSur, btnCostaRica, btnCroacia, btnDinamarca;
    private Button btnEcuador, btnEspania, btnEstadosUnidos, btnFrancia, btnGales, btnGhana;
    private Button btnHolanda, btnInglaterra, btnIran, btnJapon, btnMarruecos, btnMexico;
    private Button btnPolonia, btnPortugal, btnQatar, btnSenegal, btnSerbia,
            btnSuiza, btnTunez, btnUruguay;

    private EditText etIntroducePais;
    private Button btnAceptar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

        // Botones de selección de equipos.
        //Primer grupo de botones.
        btnAlemania = findViewById(R.id.btnAlemania);
        btnAlemania.setOnClickListener(this);
        btnArabiaSaudi = findViewById(R.id.btnArabiaSaudi);
        btnArabiaSaudi.setOnClickListener(this);
        btnArgentina = findViewById(R.id.btnArgentina);
        btnArgentina.setOnClickListener(this);
        //Segundo grupo de botones.
        btnAustralia = findViewById(R.id.btnAustralia);
        btnAustralia.setOnClickListener(this);
        btnBelgica = findViewById(R.id.btnBelgica);
        btnBelgica.setOnClickListener(this);
        btnBrasil = findViewById(R.id.btnBrasil);
        btnBrasil.setOnClickListener(this);
        //Tercer grupo de botones.
        btnCamerun = findViewById(R.id.btnCamerun);
        btnCamerun.setOnClickListener(this);
        btnCanada = findViewById(R.id.btnCanada);
        btnCanada.setOnClickListener(this);
        btnCoreaDelSur = findViewById(R.id.btnCoreaDelSur);
        btnCoreaDelSur.setOnClickListener(this);
        //Cuarto grupo de botones.
        btnCostaRica = findViewById(R.id.btnCostaRica);
        btnCostaRica.setOnClickListener(this);
        btnCroacia = findViewById(R.id.btnCroacia);
        btnCroacia.setOnClickListener(this);
        btnDinamarca = findViewById(R.id.btnDinamarca);
        btnDinamarca.setOnClickListener(this);
        //Quinto grupo de botones.
        btnEcuador = findViewById(R.id.btnEcuador);
        btnEcuador.setOnClickListener(this);
        btnEspania = findViewById(R.id.btnEspania);
        btnEspania.setOnClickListener(this);
        btnEstadosUnidos = findViewById(R.id.btnEstadosUnidos);
        btnEstadosUnidos.setOnClickListener(this);
        //Sexto grupo de botones.
        btnFrancia = findViewById(R.id.btnFrancia);
        btnFrancia.setOnClickListener(this);
        btnGales = findViewById(R.id.btnGales);
        btnGales.setOnClickListener(this);
        btnGhana = findViewById(R.id.btnGhana);
        btnGhana.setOnClickListener(this);
        //Septimo grupo de botones.
        btnHolanda = findViewById(R.id.btnHolanda);
        btnHolanda.setOnClickListener(this);
        btnInglaterra = findViewById(R.id.btnInglaterra);
        btnInglaterra.setOnClickListener(this);
        btnIran = findViewById(R.id.btnIran);
        btnIran.setOnClickListener(this);
        //Octavo grupo de botones.
        btnJapon = findViewById(R.id.btnJapon);
        btnJapon.setOnClickListener(this);
        btnMarruecos = findViewById(R.id.btnMarruecos);
        btnMarruecos.setOnClickListener(this);
        btnMexico = findViewById(R.id.btnMexico);
        btnMexico.setOnClickListener(this);
        //Noveno grupo de botones.
        btnPolonia = findViewById(R.id.btnPolonia);
        btnPolonia.setOnClickListener(this);
        btnPortugal = findViewById(R.id.btnPortugal);
        btnPortugal.setOnClickListener(this);
        btnQatar = findViewById(R.id.btnQatar);
        btnQatar.setOnClickListener(this);
        //Decimo grupo de botones.
        btnSenegal = findViewById(R.id.btnSenegal);
        btnSenegal.setOnClickListener(this);
        btnSerbia = findViewById(R.id.btnSerbia);
        btnSerbia.setOnClickListener(this);
        btnSuiza = findViewById(R.id.btnSuiza);
        btnSuiza.setOnClickListener(this);
        //Decimo primer grupo de botones.
        btnTunez = findViewById(R.id.btnTunez);
        btnTunez.setOnClickListener(this);
        btnUruguay = findViewById(R.id.btnUruguay);
        btnUruguay.setOnClickListener(this);

        etIntroducePais = findViewById(R.id.etIntroducePais);

        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);

        //Hacemos los setOnClickListener de los botones de Aceptar y Cancelar.
        //Si pulsamos Aceptar, se guardará el país introducido en el EditText.
        //Si pulsamos Cancelar, se borrará el contenido del EditText.

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aceptarSeleccion();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarSeleccion();
            }
        });

    }

    @Override
    public void onClick(View v) {
        //Hay que hacer un bucle que recorra todos los botones, cuando lo pulse cogerá el texto del
        //botón y lo pondrá en el EditText.

        //Primero creamos un array de botones.
        Button[] botones = {btnAlemania, btnArabiaSaudi, btnArgentina, btnAustralia, btnBelgica,
                btnBrasil, btnCamerun, btnCanada, btnCoreaDelSur, btnCostaRica, btnCroacia,
                btnDinamarca, btnEcuador, btnEspania, btnEstadosUnidos, btnFrancia, btnGales, btnGhana,
                btnHolanda, btnInglaterra, btnIran, btnJapon, btnMarruecos, btnMexico, btnPolonia,
                btnPortugal, btnQatar, btnSenegal, btnSerbia, btnSuiza, btnTunez, btnUruguay};

        //Ahora creamos un bucle que recorra el array de botones.
        for (int i = 0; i < botones.length; i++) {
            //Si el botón pulsado es igual al botón del array, se pondrá el texto del botón en el
            //EditText.
            if (v == botones[i]) {
                etIntroducePais.setText(botones[i].getText());
            }
        }
    }

    private void aceptarSeleccion() {
        // TODO: Comprobar que hay texto en el edit text
        //Vamos a controlar que el EditText no esté vacío.
        if (etIntroducePais.getText().toString().isEmpty()) {
            //Si está vacío, mostramos un Toast.
            Toast.makeText(getApplicationContext(), R.string.et_error_seleccion_pais,
                    Toast.LENGTH_LONG).show();
        } else {
            //Si no está vacío, guardamos el país introducido en el EditText.
            Intent i = new Intent();
            i.putExtra("equipo", etIntroducePais.getText().toString());
            setResult(RESULT_OK, i);
            finish();
        }
    }

    private void cancelarSeleccion() {
        //Hacemos el finish() para cerrar la actividad y que nos devuelva al activity anterior.
        finish();
    }

}