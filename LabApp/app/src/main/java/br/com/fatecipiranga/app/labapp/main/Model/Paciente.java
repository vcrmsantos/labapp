package br.com.fatecipiranga.app.labapp.main.Model;

import com.google.firebase.database.DatabaseReference;

import java.util.Date;

import br.com.fatecipiranga.app.labapp.main.Utils.FirebaseConfig;

/**
 * Created by madara on 4/3/18.
 */

public class Paciente {


    private DatabaseReference firebaseDB = FirebaseConfig.getFireBase();

    private String idPaciente;
    private String nomePaciente;
    private int cpfPaciente;
    private int telefonePaciente;
    private String enderecoPaciente;
    private Date dtNascPaciente;
    private String emailPaciente;
    private int pesoPaciente;
    private double alturaPaciente;

    public Paciente(String idPaciente, String nomePaciente, int cpfPaciente) {
        this.idPaciente = idPaciente;
        this.nomePaciente = nomePaciente;
        this.cpfPaciente = cpfPaciente;
    }

    public Paciente() {

    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public int getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(int cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public int getTelefonePaciente() {
        return telefonePaciente;
    }

    public void setTelefonePaciente(int telefonePaciente) {
        this.telefonePaciente = telefonePaciente;
    }

    public String getEnderecoPaciente() {
        return enderecoPaciente;
    }

    public void setEnderecoPaciente(String enderecoPaciente) {
        this.enderecoPaciente = enderecoPaciente;
    }

    public Date getDtNascPaciente() {
        return dtNascPaciente;
    }

    public void setDtNascPaciente(Date dtNascPaciente) {
        this.dtNascPaciente = dtNascPaciente;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public int getPesoPaciente() {
        return pesoPaciente;
    }

    public void setPesoPaciente(int pesoPaciente) {
        this.pesoPaciente = pesoPaciente;
    }

    public double getAlturaPaciente() {
        return alturaPaciente;
    }

    public void setAlturaPaciente(double alturaPaciente) {
        this.alturaPaciente = alturaPaciente;
    }

    public void save(){

        firebaseDB.child("Pacientes").child(this.getIdPaciente()).setValue(this);
    }

    public void delete(){
        firebaseDB.child("Pacientes").child(this.getIdPaciente()).removeValue();

    }


}
