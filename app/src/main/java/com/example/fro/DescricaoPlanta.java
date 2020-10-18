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
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class DescricaoPlanta extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Planta planta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_planta);

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

        /*!< Pegando informações da planta*/
        Intent intent = getIntent();
        String keyPlanta = (String) intent.getSerializableExtra("plantaKey");
        String plantaApelido = (String) intent.getSerializableExtra("plantaApelido");
        byte[] plantaImagemByte = intent.getByteArrayExtra("plantaImagem");

        /*!< Buscando planta */
        BancoDePlantas bancoDePlantas = new BancoDePlantas();
        planta = bancoDePlantas.getPlantaPorKey(keyPlanta);

        /*!< Verificando se há imagem ou colocar a padrao */
        ImageView fotoDaPlanta = findViewById(R.id.imagemPlanta);
        if(plantaImagemByte != null){
            Bitmap bmp = BitmapFactory.decodeByteArray(plantaImagemByte, 0, plantaImagemByte.length);
            fotoDaPlanta.setImageBitmap(bmp);
        } else {
            String uri = "@drawable/" + planta.getUrlImagemPadrao();
            int imageResource = getResources().getIdentifier(uri, null, getPackageName()); /*!< Pegando o resource da imagem */
            Drawable res = getResources().getDrawable(imageResource);
            /*!< Atualizando imagem no ImageView */
            fotoDaPlanta.setImageDrawable(res);
        }

        /*!< Colocando informacoes da planta na tela */
        TextView descricaoPlanta = findViewById(R.id.descricaoPlanta);
        descricaoPlanta.setText(planta.toString());
        TextView apelidoDaPlanta = findViewById(R.id.apelidoDaPlanta);
        apelidoDaPlanta.setText(plantaApelido);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(this, TelaInicial.class);
            startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }



}