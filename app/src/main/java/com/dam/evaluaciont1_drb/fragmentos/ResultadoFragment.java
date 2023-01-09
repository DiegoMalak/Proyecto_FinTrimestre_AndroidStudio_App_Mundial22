package com.dam.evaluaciont1_drb.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dam.evaluaciont1_drb.R;
import com.dam.evaluaciont1_drb.datos.Resultado;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultadoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadoFragment extends Fragment {

    private static final String PARTIDO_PARAM = "partidoresultado";
    private Resultado partido;
    private TextView tvResultadoFase, tvResultadoFecha, tvResultadoEquipo1, tvResultadoGoles1, tvResultadoEquipo2, tvResultadoGoles2;

    // Lo creamos para obligarle a ir por el newInstance para crear el fragmento.
    private ResultadoFragment() {
    }

    // El newInstance es el que crea el fragmento, y el bundle es el que le pasa los datos (el partido).
    // El parcelable es para pasar datos al fragmento  de la mejor manera.
    // El setArguments establece los argumentos del fragmento.
    public static ResultadoFragment newInstance(Resultado resultado) {
        ResultadoFragment fragment = new ResultadoFragment();
        Bundle args = new Bundle();
        args.putParcelable(PARTIDO_PARAM, resultado);
        fragment.setArguments(args);
        return fragment;
    }

    // El onCreate es el que se ejecuta cuando se crea el fragmento.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.partido = getArguments().getParcelable(PARTIDO_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Hacemos el inflate del layout para que se muestre en pantalla y lo guardamos en una variable
        // de tipo View para poder trabajar con él.
        // Después, con la variable de tipo View, buscamos los elementos que queremos mostrar en pantalla
        // y los guardamos en variables de tipo TextView.
        View v = inflater.inflate(R.layout.fragment_resultado, container, false);
        tvResultadoFase = v.findViewById(R.id.tvResultadoFase);
        tvResultadoFecha = v.findViewById(R.id.tvResultadoFecha);
        tvResultadoEquipo1 = v.findViewById(R.id.tvResultadoEquipo1);
        tvResultadoGoles1 = v.findViewById(R.id.tvResultadoGoles1);
        tvResultadoEquipo2 = v.findViewById(R.id.tvResultadoEquipo2);
        tvResultadoGoles2 = v.findViewById(R.id.tvResultadoGoles2);

        // Una vez que tenemos los elementos que queremos mostrar en pantalla, los rellenamos con los
        // datos que nos llegan del objeto Resultado, controlando que no sean nulos y usando el método
        // setText() para mostrarlos en pantalla rellenando los TextView.
        if (this.partido != null) {
            tvResultadoFase.setText(this.partido.getFase());
            tvResultadoFecha.setText(this.partido.getFecha());
            tvResultadoEquipo1.setText(this.partido.getEquipo1());
            tvResultadoGoles1.setText(String.valueOf(this.partido.getGolesEquipo1()));
            tvResultadoEquipo2.setText(this.partido.getEquipo2());
            tvResultadoGoles2.setText(String.valueOf(this.partido.getGolesEquipo2()));
        }
        return v;
    }
}