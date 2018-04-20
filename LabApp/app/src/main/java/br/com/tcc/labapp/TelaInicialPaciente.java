package br.com.tcc.labapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.app.Activity;
import android.app.AlertDialog;



public class TelaInicialPaciente extends Activity {

    AlertDialog alertDialogStores;
    Button btnTelaAgendamento;

    ListView listVdados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial_paciente);


        btnTelaAgendamento = (Button)findViewById(R.id.btnTelaAgendamento);

        btnTelaAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreAgendamento = new Intent(TelaInicialPaciente.this, TelaAgendamento.class);
                startActivity(abreAgendamento);
            }
        });
    }







}
