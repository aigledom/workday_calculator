package com.example.calculadorajornadas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Elementos usados en la MainActivity
    //Arrays de horas y minutos
    EditText[] horasEditTexts, minutosEditTexts;
    Button btnCalcular;
    TextView tvHorasTotales, tvHorasRestantes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Creación de la vista y se envía el estado guardado
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Inicializa arrays para los EditTexts de horas
        horasEditTexts = new EditText[]{
                findViewById(R.id.etLunesHoras),
                findViewById(R.id.etMartesHoras),
                findViewById(R.id.etMiercolesHoras),
                findViewById(R.id.etJuevesHoras),
                findViewById(R.id.etViernesHoras)
        };

        //Inicializa arrays para los EditTexts de minutos
        minutosEditTexts = new EditText[]{
                findViewById(R.id.etLunesMinutos),
                findViewById(R.id.etMartesMinutos),
                findViewById(R.id.etMiercolesMinutos),
                findViewById(R.id.etJuevesMinutos),
                findViewById(R.id.etViernesMinutos)
        };

        btnCalcular = findViewById(R.id.btnCalcular);
        tvHorasTotales = findViewById(R.id.tvHorasTotales);
        tvHorasRestantes = findViewById(R.id.tvHorasRestantes);

        //Listener para el botón calcula la suma de horas
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularHoras();
            }
        });
    }

    public void calcularHoras() {
        double horasTotales = 0;
        double minutosTotales = 0;

        // Calcular las horas totales y minutos totales
        for (int i = 0; i < horasEditTexts.length; i++) {
            double horas = Double.parseDouble(horasEditTexts[i].getText().toString());
            double minutos = Double.parseDouble(minutosEditTexts[i].getText().toString());
            //Realiza el sumatorio de todas las horas y minutos
            horasTotales += horas;
            minutosTotales += minutos;
        }

        // Ajustar las horas si los minutos superan 60
        if (minutosTotales >= 60) {
            horasTotales += minutosTotales / 60;
            minutosTotales %= 60;
        }

        // Mostrar las horas totales en el TextView
        tvHorasTotales.setText("Horas totales: " + horasTotales + " horas " + minutosTotales + " minutos");

        // Calcular las horas restantes para alcanzar el objetivo
        double horasObjetivo = 38.0 + (55.0 / 60); // Convertir minutos a horas
        double horasRestantes = horasObjetivo - horasTotales;

        // Mostrar las horas restantes
        tvHorasRestantes.setText("Horas restantes: " + horasRestantes + " horas");
    }
}