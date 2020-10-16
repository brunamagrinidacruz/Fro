package com.example.fro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

/**
 * Referencia (tirar foto e utilizar): https://developer.android.com/training/camera/photobasics?hl=pt-br
 * Referencia (converter de bitmap para Base64): https://stackoverflow.com/questions/9224056/android-bitmap-to-base64-string
 * **/
public class CadastroPlanta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_planta);
        reconhecimentoDePlanta();
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public void reconhecimentoDePlanta() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView fotoDaPlanta = findViewById(R.id.fotoDaPlanta);
            fotoDaPlanta.setImageBitmap(imageBitmap);

            /*!< Conversao de bitmap para byte array */
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();

            /*!< Convertendo a imagem para Base64 */
            String imagemPlantaBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            try {
                /*!< Chamando a API */
                String respostaPlantId = new PlantId(imagemPlantaBase64).execute().get();

                /*!< Objeto JSON da resposta da API */
                JSONObject plantaJson = new JSONObject(respostaPlantId);

                /*!< Pegando lista de sugestoes de plantas */
                JSONArray sugestoes = plantaJson.getJSONArray("suggestions");

                System.out.println(sugestoes);

                /*!< Pegando a primeira sugestao */
                JSONObject primeiraSugestao = sugestoes.getJSONObject(0);

                /*!< Pegando os detalhes das plantas */
                JSONObject detalhesDaPrimeiraSugestao = new JSONObject(primeiraSugestao.getString("plant_details"));

                /*!< Pegando array com os nomes comuns da planta */
                JSONArray nomesComunsDaPrimeiraSugestao = detalhesDaPrimeiraSugestao.getJSONArray("common_names");

                /*!< Identificando a planta */
                BancoDePlantas bancoDePlantas = new BancoDePlantas();
                Planta planta = bancoDePlantas.identificarPlanta(nomesComunsDaPrimeiraSugestao);

                TextView informacoesPlanta = findViewById(R.id.informacoesPlanta);
                informacoesPlanta.setText("Nome: " + planta.getNome());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}