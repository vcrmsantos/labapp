package br.com.fatecipiranga.app.labapp.main.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

import br.com.fatecipiranga.app.labapp.R;
import br.com.fatecipiranga.app.labapp.main.Model.Paciente;
import br.com.fatecipiranga.app.labapp.main.Utils.FirebaseConfig;

public class MainActivity extends AppCompatActivity {


    //private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    //private DatabaseReference pacienteReference = databaseReference.child("Pacientes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paciente victor = new Paciente();
        victor.setNomePaciente("Victor Moreira");


        Paciente lucas = new Paciente();
        lucas.setNomePaciente("Lucas Da Silva");



        victor.save();
        lucas.save();
        //lucas.delete();

        victor.setNomePaciente("Victor Da Silva");
        victor.update();
        //victor.delete();

        victor.read();
        lucas.read();

    }

    public void abrirCadastroPaciente(View v) {
        Intent intent = new Intent(MainActivity.this, CadastroPacienteActivity.class);
        startActivity(intent);
    }
}




