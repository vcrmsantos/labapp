package br.com.tcc.labapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class TabPaciente extends Activity {


    EditText editLoginPaciente, editSenhaPaciente;
    Button btnLogarPaciente;
    TextView txtCadastroPaciente;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_paciente);


        editLoginPaciente = (EditText)findViewById(R.id.editLoginPaciente);
        editSenhaPaciente = (EditText)findViewById(R.id.editSenhaPaciente);
        btnLogarPaciente = (Button)findViewById(R.id.btnLogarPaciente);
        txtCadastroPaciente = (TextView)findViewById(R.id.txtCadastroPaciente);



        txtCadastroPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(TabPaciente.this, TelaCadastro.class);
                abreCadastro.putExtra("tipo","p");
                startActivity(abreCadastro);
            }
        });

        btnLogarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()){

                    String login = editLoginPaciente.getText().toString();
                    String senha = editSenhaPaciente.getText().toString();

                    if(login.isEmpty() || senha.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher login e senha", Toast.LENGTH_LONG).show();
                    } else{

                        url = "http://10.67.172.178/labapp/logar.php";
                        parametros = "login=" + login + "&senha=" + senha;
                        new SolicitaDados().execute(url);
                        editLoginPaciente.setText("");
                        editSenhaPaciente.setText("");
                    }

                } else{
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls){

                return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado){
            if(resultado.contains("login_ok")){
                Intent abreInicio = new Intent(TabPaciente.this, TelaInicialPaciente.class);
                startActivity(abreInicio);
            } else{
                Toast.makeText(getApplicationContext(), "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
            }
        }

    }

}
