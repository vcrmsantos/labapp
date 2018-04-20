package br.com.fatecipiranga.app.labapp.main.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import br.com.fatecipiranga.app.labapp.R;
import br.com.fatecipiranga.app.labapp.main.Utils.FirebaseConfig;
import br.com.fatecipiranga.app.labapp.main.Model.Paciente;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference pacienteReference = FirebaseConfig.getFireBase();
    private Button deleteButton;
    private Paciente pacienteCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviewById();

        Paciente victor = new Paciente("001", "Victor Moreira", 777777777);
        victor.save();

        pacienteReference.child("Pacientes").child(victor.getIdPaciente()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {
                    pacienteCall = dataSnapshot.getValue(Paciente.class);
                    Toast.makeText(getApplicationContext(), "OLÁ " + pacienteCall.getNomePaciente(), Toast.LENGTH_SHORT).show();
                }

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pacienteCall.delete();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void findviewById() {
            deleteButton = (Button) findViewById(R.id.deleteButton);
    }
}
