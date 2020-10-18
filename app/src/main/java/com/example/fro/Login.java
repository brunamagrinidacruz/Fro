package com.example.fro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    EditText email, pass;
    ImageButton btnEntrarBr;

    FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Hooks */
        Toolbar toolbar = findViewById(R.id.customToolbar);

        /* Toolbar */
        setSupportActionBar(toolbar);
        setTitle(" ");

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);

                /* Troca a direção do slide de transição: esquerda para direita */
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
            }
        });

        fAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.txtvEmail);
        pass = findViewById(R.id.txtvPass);
        btnEntrarBr = findViewById(R.id.imgbtnEntrar);


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = fAuth.getCurrentUser();

                if(mFirebaseUser != null){
                    Intent i = new Intent(Login.this, TelaInicial.class);
                    startActivity(i);
                }
            }
        };

        btnEntrarBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email = email.getText().toString();
                String _pass = pass.getText().toString();

                if(_email.isEmpty()){
                   email.setError("Por favor, forneça um email.");
                   email.requestFocus();
                }

                else if(_pass.isEmpty()){
                    pass.setError("Por favor, insira sua senha.");
                    pass.requestFocus();
                }

                else if(_email.isEmpty() && _pass.isEmpty()){
                    Toast.makeText(Login.this, "Os campos estão vazios.", Toast.LENGTH_SHORT).show();
                }

                else if(!(_email.isEmpty() && _pass.isEmpty())){
                    fAuth.signInWithEmailAndPassword(_email, _pass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this, "Ocorreu um erro de login. Tente novamente.", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intToHome = new Intent(Login.this, TelaInicial.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }

                else{
                    Toast.makeText(Login.this, "Um erro ocorreu e não foi possível logar.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(mAuthStateListener);
    }
}
