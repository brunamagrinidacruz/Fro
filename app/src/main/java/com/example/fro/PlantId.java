package com.example.fro;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Referencia (chamar API em Java): https://blog.matheuscastiglioni.com.br/consumindo-web-service-no-android/
 * Referencia (exemplo de chamada Java para API): https://github.com/Plant-id/Plant-id-API/pull/6/commits/4da59702bc3b9c9c85e81b8c2503fda5030df33c
 * **/
public class PlantId extends AsyncTask<Void, Void, String> {

    private String imagemPlantaBase64;

    private String urlString = "https://api.plant.id/v2/identify";
    private String apiKey = "5P203X1zTMgsllLH52vLIlVHxro52vlU49NxPicnt3ztFMZacO";

    public PlantId(String imagemPlantaBase64) {
        this.imagemPlantaBase64 = imagemPlantaBase64;
    }

    /*!< Funcao executada ao chamar a Thread */
    @Override
    protected String doInBackground(Void... voids) {
        String resposta = new String();

        try {
            JSONObject data = criarMensagem();
            resposta = chamarAPI(data);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resposta;
    }

    private JSONObject criarMensagem() throws JSONException {
        /*!< Adicionando chave */
        JSONObject data = new JSONObject();
        data.put("api_key", apiKey);

        /*!< Adicionado modificadores */
        JSONArray modifiers = new JSONArray()
                .put("crops_fast")
                .put("similar_images");
        data.put("modifiers", modifiers);

        /*!< Selecionando idioma */
        data.put("plant_language", "en");

        /*!< Adicionando detalhes */
        JSONArray plantDetails = new JSONArray()
                .put("common_names")
                .put("name_authority")
                .put("wiki_description")
                .put("taxonomy")
                .put("synonyms");
        data.put("plant_details", plantDetails);

        /*!< Adicionando imagem */
        JSONArray images = new JSONArray()
                .put(imagemPlantaBase64);
        data.put("images", images);

        return data;
    }

    private String chamarAPI(JSONObject data) throws IOException {
        StringBuilder resposta = new StringBuilder();

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        /*!< Enviando corpo da requisicao (objeto JSON) */
        OutputStream os = con.getOutputStream();
        os.write(data.toString().getBytes());
        os.close();

        con.connect();

        /*!< Lendo resposta da requisicao */
        Reader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        for (int c; (c = in.read()) >= 0;)
            resposta.append((char) c);

        con.disconnect();

        return resposta.toString();
    }

}

