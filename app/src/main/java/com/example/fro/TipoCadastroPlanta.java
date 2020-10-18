package com.example.fro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class TipoCadastroPlanta extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_cadastro_planta);

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

    public void abrirTelaReconhecimentoAutomatico(View view){
        abrirTelaCadastroPlanta(1);
    }

    public void abrirTelaManual(View view){
        abrirTelaCadastroPlanta(2);
    }

    /*!< Tipo: 1 -> Reconhecimento por API */
    /*!< Tipo: 2 -> Manualmente */
    public void abrirTelaCadastroPlanta(int tipo) {
        Intent intent = new Intent(this, CadastroPlanta.class);
        intent.putExtra("tipo", tipo);
        startActivity(intent);
    }

}