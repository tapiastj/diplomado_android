package com.example.android.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class principal extends AppCompatActivity {
    private EditText numero1,numero2;
    private Spinner opciones;
    private TextView resultado;
    private  String opc[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        numero1 = findViewById(R.id.txtNum1);
        numero2 = findViewById(R.id.txtNum2);
        opciones = findViewById(R.id.cmb1);
        resultado = findViewById(R.id.lblResultado);

        opc = this.getResources().getStringArray(R.array.operaciones);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,opc);
        opciones.setAdapter(adapter);
    }

    public void calcular(View v){
        double n1,n2,result = 0;
        int opcion;
        if(validar()) {
            n1 = Double.parseDouble(numero1.getText().toString());
            n2 = Double.parseDouble(numero2.getText().toString());
            opcion = opciones.getSelectedItemPosition();
            switch (opcion) {
                case 0:
                    result = Metodos.sumar(n1, n2);
                    break;
                case 1:
                    result = Metodos.restar(n1, n2);
                    break;
                case 2:
                    result = Metodos.multiplicar(n1, n2);
                    break;
                case 3:
                    result = Metodos.dividir(n1, n2);
                    break;
            }

            this.resultado.setText(String.format("%.2f", result));
        }
    }

    public void limpiar(View v){
        this.resultado.setText("");
        this.numero1.setText("");
        this.numero2.setText("");
        this.opciones.setSelection(0);
        this.numero1.requestFocus();
    }

    public boolean validar(){
        int opcion = opciones.getSelectedItemPosition();
        if (numero1.getText().toString().isEmpty()){
            numero1.setError(this.getResources().getString(R.string.error_uno));
            numero1.requestFocus();
            return false;
        }

        if (numero2.getText().toString().isEmpty()){
            numero2.setError(this.getResources().getString(R.string.error_dos));
            numero2.requestFocus();
            return false;
        }

        if(opcion == 3 && Double.parseDouble(numero2.getText().toString()) == 0){
            numero2.setError(this.getResources().getString(R.string.error_tres));
            numero2.requestFocus();
            return false;
        }

        return true;
    }
}
