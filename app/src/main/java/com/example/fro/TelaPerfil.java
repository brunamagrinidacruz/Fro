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
import com.google.firebase.auth.FirebaseAuth;

public class TelaPerfil extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);
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

        // Deixa seleção verdinha
        navigationView.setCheckedItem(R.id.itPerfil);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.itInicio: {
                Intent intent = new Intent(this, TelaInicial.class);
                startActivity(intent);

                break;
            }

            case R.id.itTarefas: {
                Intent intent = new Intent(this, TelaTarefas.class);
                startActivity(intent);

                break;
            }

            case R.id.itDoacoes: {
                Intent intent = new Intent(this, TelaDoacoes.class);
                startActivity(intent);

                break;
            }

            case R.id.itNotifAtiva: {
                Intent intent = new Intent(this, TelaNotificacoes.class);
                startActivity(intent);

                break;
            }

            case R.id.itAboutUs: {
                Intent intent = new Intent(this, TelaSobre.class);
                startActivity(intent);

                break;
            }

            case R.id.itAmigos: {
                Intent intent = new Intent(this, TelaAmigos.class);
                startActivity(intent);

                break;
            }

            case R.id.itPerfil: {
                Intent intent = new Intent(this, TelaPerfil.class);
                startActivity(intent);

                break;
            }

            case R.id.itPlantas: {
                Intent intent = new Intent(this, TelaPlantas.class);
                startActivity(intent);

                break;
            }

            case R.id.itLogout: {
                FirebaseAuth.getInstance().signOut();

                Intent intToLoginPage = new Intent(TelaPerfil.this, MainActivity.class);
                startActivity(intToLoginPage);

                break;
            }

        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);

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

    public void abrirTelaConquistas(View view){
        Intent intent = new Intent(this, TelaConquistas.class);
        startActivity(intent);
    }
}