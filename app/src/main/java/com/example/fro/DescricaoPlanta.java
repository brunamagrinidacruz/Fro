package com.example.fro;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
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
        String nomePlanta = (String) intent.getSerializableExtra("planta");
        BancoDePlantas bancoDePlantas = new BancoDePlantas();
        planta = bancoDePlantas.getPlantaPorNome(nomePlanta);

        /*!< Colocando informacoes da planta na tela */
        TextView descricaoPlanta = findViewById(R.id.descricaoPlanta);
        descricaoPlanta.setText(planta.toString());
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


}