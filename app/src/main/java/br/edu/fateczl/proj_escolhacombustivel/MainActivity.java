package br.edu.fateczl.proj_escolhacombustivel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtGasolina;
    private EditText edtEtanol;
    private TextView tvRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtGasolina = findViewById(R.id.edtGasolina);
        edtGasolina.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtEtanol = findViewById(R.id.edtEtanol);
        edtEtanol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnCalc = findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(op -> Calcular());

    }

    private void Calcular() {

        String valorGasolinaStr = edtGasolina.getText().toString();
        String valorEtanolStr = edtEtanol.getText().toString();

        if (valorGasolinaStr.isEmpty() && valorEtanolStr.isEmpty()) {
            tvRes.setText("");
            return;
        }


        if (valorGasolinaStr.isEmpty() || valorEtanolStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double valorGasolina = Double.parseDouble(valorGasolinaStr);
            double valorEtanol = Double.parseDouble(valorEtanolStr);

            if (valorGasolina <= 0 || valorEtanol <= 0) {
                Toast.makeText(this, "Os valores devem ser maiores que zero", Toast.LENGTH_SHORT).show();
                return;
            }

            double proporcao = valorEtanol / valorGasolina;

            if (proporcao <= 0.7) {
                tvRes.setText("A melhor opção é abastecer com Etanol.");
            } else {
                tvRes.setText("A melhor opção é abastecer com Gasolina.");
            }

        } catch(NumberFormatException e){
                Toast.makeText(this, "Valores inseridos inválidos", Toast.LENGTH_SHORT).show();
            }
    }
}


