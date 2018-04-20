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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class TelaCadastro extends AppCompatActivity {

    ValidaCpf validaCpf = new ValidaCpf();

    EditText editNomeCadastro, editCpfCadastro, editSenhaCadastro, editCrmCadastro, editLoginCadastro;
    Button btnRegistrar, btnCancelar, btnAlterar, btnExcluir;


    String url = "";
    String parametros = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);


        editNomeCadastro = (EditText)findViewById(R.id.editNomeCadastro);
        editLoginCadastro = (EditText)findViewById(R.id.editLoginCadastro);
        editCpfCadastro = (EditText)findViewById(R.id.editCpfCadastro);
        editCrmCadastro = (EditText)findViewById(R.id.editCrmCadastro);
        editSenhaCadastro = (EditText)findViewById(R.id.editSenhaCadastro);


        String tipoLogin = this.getIntent().getExtras().getString("tipo");


        if(tipoLogin.equals("p")) {
            editCrmCadastro.setEnabled(false);
        }
        else {
            editCrmCadastro.setEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_novo){

            String tipoLogin = this.getIntent().getExtras().getString("tipo");


                if(tipoLogin.equals("p")){



                    //editCrmCadastro.setEnabled(false);




                        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                        if(networkInfo != null && networkInfo.isConnected()) {


                            String nome = editNomeCadastro.getText().toString();
                            String login = editLoginCadastro.getText().toString();
                            String cpf = editCpfCadastro.getText().toString();
                            String crm = editCrmCadastro.getText().toString();
                            String senha = editSenhaCadastro.getText().toString();

                            if (validaCpf.isCPF(cpf) == true) {

                                if (nome.isEmpty() || login.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Favor preencher os campos nome, login, cpf e senha", Toast.LENGTH_LONG).show();
                                } else {

                                    url = "http://10.67.172.178/labapp/registrar.php";
                                    parametros = "nome=" + nome + "&login=" + login + "&cpf=" + cpf + "&crm=" + crm + "&senha=" + senha;
                                    new SolicitaDados().execute(url);
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "CPF inválido !!!", Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(getApplicationContext(), "Ocorreu um erro !", Toast.LENGTH_LONG).show();
                        }




            }
            else{

                ///editCrmCadastro.setEnabled(true);




                        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                        if (networkInfo != null && networkInfo.isConnected()) {


                            String nome = editNomeCadastro.getText().toString();
                            String login = editLoginCadastro.getText().toString();
                            String cpf = editCpfCadastro.getText().toString();
                            String crm = editCrmCadastro.getText().toString();
                            String senha = editSenhaCadastro.getText().toString();

                            if (validaCpf.isCPF(cpf) == true) {

                                if (nome.isEmpty() || login.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Favor preencher os campos nome, login, cpf e senha", Toast.LENGTH_LONG).show();
                                } else {

                                    url = "http://10.67.172.178/labapp/registrarmedico.php";
                                    parametros = "nome=" + nome + "&login=" + login + "&cpf=" + cpf + "&crm=" + crm + "&senha=" + senha;
                                    new SolicitaDados().execute(url);
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "CPF inválido !!!", Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(getApplicationContext(), "Ocorreu um erro !", Toast.LENGTH_LONG).show();
                        }

                    }


                }


        if(id == R.id.menu_atualiza){

            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isConnected()){

                String login = editLoginCadastro.getText().toString();
                String senha = editSenhaCadastro.getText().toString();

                if(login.isEmpty() || senha.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Favor preencher os campos login e senha", Toast.LENGTH_LONG).show();
                } else{

                    url = "http://10.67.172.178/labapp/atualizar.php";
                    parametros = "login=" + login + "&senha=" + senha;
                    new SolicitaDados().execute(url);
                    Toast.makeText(getApplicationContext(), "Senha Alterada", Toast.LENGTH_LONG).show();
                }

            } else{
                Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
            }

        }
        if(id == R.id.menu_deleta){

            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isConnected()){

                String login = editLoginCadastro.getText().toString();

                if(login.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Favor preencher o login", Toast.LENGTH_LONG).show();
                } else{

                    url = "http://10.67.172.178/labapp/excluir.php";
                    parametros = "login=" + login;
                    new SolicitaDados().execute(url);
                    Toast.makeText(getApplicationContext(), "Login excluído", Toast.LENGTH_LONG).show();
                }

            } else{
                Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
            }


        }

        if(id == R.id.menu_home){
            finish();
        }
        return true;
    }



    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls){

            return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado){

            if(resultado.contains("login_ok")){

                Toast.makeText(getApplicationContext(), "Login já cadastrado", Toast.LENGTH_LONG).show();

            } else if(resultado.contains("registro_ok")) {

                Toast.makeText(getApplicationContext(), "Login cadastrado com sucesso !", Toast.LENGTH_LONG).show();
                Intent abreInicio = new Intent(TelaCadastro.this, MainActivity.class);
                startActivity(abreInicio);

            } else {

                Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();

            }

        }

    }

}
