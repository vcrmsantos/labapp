package br.com.tcc.labapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;

public class TelaAgendamento extends AppCompatActivity {

    private Spinner spnExame, spnMedico;
    private EditText etData, etHora;
    private String url = "";
    private String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_agendamento);

        spnExame = (Spinner)findViewById(R.id.spnExame);
        spnMedico = (Spinner)findViewById(R.id.spnMedico);
        etData = (EditText)findViewById(R.id.etData);
        etHora = (EditText)findViewById(R.id.etHora);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agendamento,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_criar){


            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()) {


                String exame = spnExame.getSelectedItem().toString();
                long medico = spnMedico.getSelectedItemId();
                Date data = (Date) etData.getText();
                Time hora = (Time) etHora.getText();

                    if (exame.isEmpty() || medico == 0 || data != null || hora != null) {
                        Toast.makeText(getApplicationContext(), "Favor preencher todos os campos", Toast.LENGTH_LONG).show();
                    } else {

                        url = "http://10.67.172.178/labapp/registrar.php";
                        parametros = "exame=" + exame + "&medico=" + medico + "&data=" + data + "&hora=" + hora;
                        new TelaAgendamento.SolicitaDados().execute(url);
                    }

            } else {

                Toast.makeText(getApplicationContext(), "Ocorreu um erro !", Toast.LENGTH_LONG).show();
            }

        }

        if(id == R.id.menu_home){
            finish();
        }
        return true;
    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {

            if (resultado.contains("login_ok")) {

                Toast.makeText(getApplicationContext(), "Login já cadastrado", Toast.LENGTH_LONG).show();

            } else if (resultado.contains("registro_ok")) {

                Toast.makeText(getApplicationContext(), "Login cadastrado com sucesso !", Toast.LENGTH_LONG).show();
                Intent abreInicio = new Intent(TelaAgendamento.this, MainActivity.class);
                startActivity(abreInicio);

            } else {

                Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();

            }

        }

    }
}
