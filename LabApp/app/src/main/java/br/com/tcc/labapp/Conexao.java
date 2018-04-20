package br.com.tcc.labapp;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Conexao {

/*
    public static String postDados(String urlUsuario, String parametrosUsuario){


        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();

        builder.url(urlUsuario);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

        RequestBody body = RequestBody.create(mediaType, parametrosUsuario);

        builder.post(body);

        Request request = builder.build();

        try{

            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch(IOException erro){

            return  null;

        }

    }
*/

    public static String postDados(String urlUsuario, String parametrosUsuario){
        URL url;
        HttpURLConnection connection = null;

        try{
            url = new URL(urlUsuario);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Lenght", "" + Integer.toString(parametrosUsuario.getBytes().length));
            connection.setRequestProperty("Content-Language", "pt-br");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);


            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            outputStreamWriter.write(parametrosUsuario);
            outputStreamWriter.flush();

            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linha;
            StringBuffer resposta = new StringBuffer();

            while((linha = bufferedReader.readLine())!= null){
                resposta.append(linha);
                resposta.append('\r');
            }

            bufferedReader.close();
            return resposta.toString();

        } catch (Exception erro){
            return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }

    }
}
