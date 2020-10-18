package com.example.fro;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * Referencia (tirar foto e utilizar): https://developer.android.com/training/camera/photobasics?hl=pt-br
 * Referencia (converter de bitmap para Base64): https://stackoverflow.com/questions/9224056/android-bitmap-to-base64-string
 * **/
public class CadastroPlanta extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    private String nomeDaPlanta;
    private String urlDaPlanta;
    private String keyDaPlanta;
    private Bitmap imagemPlanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_planta);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        /*!< Menu inferior e lateral */
        Toolbar toolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
        setTitle(" ");
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigationDrawerOpen, R.string.navigationDrawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        Intent intent = getIntent();
        int tipo = (int) intent.getSerializableExtra("tipo");
        if(tipo == 1) { /*!< Reconhecimento */
            reconhecimentoDePlanta();
        } else { /*!< Manual */
            reconhecimentoManual();
        }
    }

    private boolean onNavigationItemSelected(MenuItem menuItem) {
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void reconhecimentoDePlanta() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void reconhecimentoManual() {
        Spinner tipoPlantaSpinner = (Spinner) findViewById(R.id.tipoPlantaSpinner);
        BancoDePlantas bancoDePlantas = new BancoDePlantas();

        tipoPlantaSpinner.setVisibility(View.VISIBLE);

        /*!< Criando Spinner */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bancoDePlantas.getNomesPlantas());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoPlantaSpinner.setAdapter(adapter);

        /*!< Quando o usuário atualiza a lista de tipos de plantas, irá atualizar a imagem apresentada. A imagem sera uma padrao para cada planta localizada em /drawable */
        tipoPlantaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                nomeDaPlanta = bancoDePlantas.getNomesPlantas().get(position);
                urlDaPlanta = bancoDePlantas.getUrlPlantas().get(position);
                keyDaPlanta = bancoDePlantas.getKeysPlantas().get(position);
                String uri = "@drawable/" + urlDaPlanta;
                int imageResource = getResources().getIdentifier(uri, null, getPackageName()); /*!< Pegando o resource da imagem */
                Drawable res = getResources().getDrawable(imageResource);
                ImageView fotoDaPlanta = findViewById(R.id.fotoDaPlanta);
                /*!< Atualizando imagem no ImageView */
                fotoDaPlanta.setImageDrawable(res);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

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

                /*!< Pegando a primeira sugestao */
                JSONObject primeiraSugestao = sugestoes.getJSONObject(0);

                /*!< Componentes da tela que serao apresentados dependendo do resultado da API */
                ImageView fotoDaPlanta = findViewById(R.id.fotoDaPlanta);
                TextView tipoPlantaTexto = findViewById(R.id.tipoPlantaTexto);
                tipoPlantaTexto.setVisibility(View.INVISIBLE);
                Spinner tipoPlantaSpinner = (Spinner) findViewById(R.id.tipoPlantaSpinner);
                tipoPlantaSpinner.setVisibility(View.INVISIBLE);

                /*!< Probabilidade da API acertar deve ser maior que 30% */
                double probabilidade = primeiraSugestao.getDouble("probability");
                if(probabilidade >= 0.3) { /*!< Utiliza a informação da API */
                    /*!< Pegando os detalhes das plantas */
                    JSONObject detalhesDaPrimeiraSugestao = new JSONObject(primeiraSugestao.getString("plant_details"));

                    /*!< Pegando array com os nomes comuns da planta */
                    JSONArray nomesComunsDaPrimeiraSugestao = detalhesDaPrimeiraSugestao.getJSONArray("common_names");

                    /*!< Identificando a planta */
                    BancoDePlantas bancoDePlantas = new BancoDePlantas();
                    Planta planta = bancoDePlantas.identificarPlanta(nomesComunsDaPrimeiraSugestao);

                    /*!< Configurando coisas da tela */
                    imagemPlanta = imageBitmap;
                    fotoDaPlanta.setImageBitmap(imageBitmap);
                    tipoPlantaTexto.setVisibility(View.VISIBLE);
                    keyDaPlanta = planta.getId();
                    nomeDaPlanta = planta.getNome();
                    tipoPlantaTexto.setText(nomeDaPlanta);
                } else { /*!< Como não achou na API, habilita o Spinner para seleção manual */
                    reconhecimentoManual();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else { /*!< Foto não capturada com sucesso*/
            /*!< Abrir tela de selecao de tipo de cadastro de planta (reconhecimento ou manual) */
            Intent intent = new Intent(this, TipoCadastroPlanta.class);
            startActivity(intent);
        }

    }

    public void abrirTelaPlanta(View view) {
        TextView apelidoPlanta = findViewById(R.id.apelidoPlanta);
        if(apelidoPlanta.getText().toString().matches("")) {
            Snackbar mySnackbar = Snackbar.make(view, "É necessário escolher um apelido para a platinha!", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else if(apelidoPlanta.getText().length() > 20) {
            Snackbar mySnackbar = Snackbar.make(view, "A plantinha deve ter um nome de até 20 caracteres.", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else {
            /*!< Cadastrar plantinha no banco de dados */

            /*!< Abrir tela da plantinha passando a plantinha correspondente */
            Intent intent = new Intent(this, DescricaoPlanta.class);
            intent.putExtra("plantaApelido", apelidoPlanta.getText().toString());
            intent.putExtra("plantaKey", keyDaPlanta);

            /*!< Convertendo a imagem para byte e passando para proxima tela */
            if(imagemPlanta != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagemPlanta.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] imagemPlantaByte = stream.toByteArray();
                intent.putExtra("plantaImagem", imagemPlantaByte);
            }

            startActivity(intent);
        }
    }

}