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

public class TabMedico extends Activity {

    EditText editLoginMedico, editSenhaMedico;
    Button btnLogarMedico;
    TextView txtCadastroMedico;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_medico);

        editLoginMedico = (EditText)findViewById(R.id.editLoginMedico);
        editSenhaMedico = (EditText)findViewById(R.id.editSenhaMedico);
        btnLogarMedico = (Button)findViewById(R.id.btnLogarMedico);
        txtCadastroMedico = (TextView)findViewById(R.id.txtCadastroMedico);


        txtCadastroMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(TabMedico.this, TelaCadastro.class);
                abreCadastro.putExtra("tipo","m");
                startActivity(abreCadastro);
            }
        });



        btnLogarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()){

                    String login = editLoginMedico.getText().toString();
                    String senha = editSenhaMedico.getText().toString();

                    if(login.isEmpty() || senha.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher login e senha", Toast.LENGTH_LONG).show();
                    } else{

                        url = "http://10.67.172.178/labapp/logarmedico.php";
                        parametros = "login=" + login + "&senha=" + senha;
                        new TabMedico.SolicitaDados().execute(url);
                        editLoginMedico.setText("");
                        editSenhaMedico.setText("");
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
                Intent abreInicio = new Intent(TabMedico.this, TelaInicialPaciente.class);
                startActivity(abreInicio);
            } else{
                Toast.makeText(getApplicationContext(), "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
            }
        }

    }

}
