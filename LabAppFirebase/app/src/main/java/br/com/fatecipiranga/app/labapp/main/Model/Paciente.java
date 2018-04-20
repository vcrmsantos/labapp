package br.com.fatecipiranga.app.labapp.main.Model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;
import br.com.fatecipiranga.app.labapp.main.Utils.FirebaseConfig;


public class Paciente {


    private DatabaseReference pacienteReference = FirebaseDatabase.getInstance().getReference().child("pacientes");

    private String uuidPaciente = UUID.randomUUID().toString().replace("-", "");
    //private String uuidPaciente = pacienteReference.push().getKey();
    private String nomePaciente;
    private String emailPaciente;
    private String senhaPaciente;

    public Paciente() {

    }

    public String getUuidPaciente() {
        return uuidPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public String getSenhaPaciente() {
        return senhaPaciente;
    }

    public void setSenhaPaciente(String senhaPaciente) {
        this.senhaPaciente = senhaPaciente;
    }

    @Override
    public String toString() {
        return "Paciente:" +
                "\nUUID: " + this.getUuidPaciente() +
                "\nNome: " + this.getNomePaciente() +
                "\nIdade: " + this.getEmailPaciente();
    }

    public void save(){
        pacienteReference.child(this.getUuidPaciente()).setValue(this);
    }

    public void delete(){
        pacienteReference.child(this.getUuidPaciente()).removeValue();

    }

    public boolean update() {
        pacienteReference.child(this.getUuidPaciente()).setValue(this);
        return true;
    }

    public void read() {
        ValueEventListener pacienteListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Paciente pacienteCall = new Paciente();
                pacienteCall = dataSnapshot.getValue(Paciente.class);
                //System.out.println(pacienteCall);
                Log.i("FIREBASE", pacienteCall.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        pacienteReference.child(this.getUuidPaciente()).addValueEventListener(pacienteListener);
    }


}
