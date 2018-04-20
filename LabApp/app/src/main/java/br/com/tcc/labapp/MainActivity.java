package br.com.tcc.labapp;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec tabPaciente = tabHost.newTabSpec("ABA PACIENTE");
        TabHost.TabSpec tabMedico = tabHost.newTabSpec("ABA MEDICO");

        tabPaciente.setIndicator("Paciente");
        tabPaciente.setContent(new Intent(this,TabPaciente.class));

        tabMedico.setIndicator("Médico");
        tabMedico.setContent(new Intent(this,TabMedico.class));

        tabHost.addTab(tabPaciente);
        tabHost.addTab(tabMedico);

    }
}
