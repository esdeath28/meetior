package com.example.meetior.Authentication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.meetior.MainActivity;
import com.example.meetior.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthSt;
    private EditText eu, ep;
    private String u,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        signincheck();
    }

    private void signincheck() {
        mAuth = FirebaseAuth.getInstance();
        mAuthSt = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser FUser = mAuth.getCurrentUser();
                if (FUser != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        };
    }

    public void login(View view) {
        mAuth = FirebaseAuth.getInstance();
        eu = findViewById(R.id.logemail);
        ep = findViewById(R.id.passwordfield);
        u = eu.getText().toString();
        p = ep.getText().toString();
        if (infoOk(u,p)){
            firebaselog(u,p);
        }
    }

    private void firebaselog(String u, String p) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(u, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Sign In Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean infoOk(String u, String p) {
        int wrong=0;
        if (u.isEmpty()) {
            eu.setError("Enter an e-mail address");
            eu.requestFocus();
            wrong++;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(u).matches()) {
            eu.setError("Enter a valid e-mail address");
            eu.requestFocus();
            wrong++;
        } if (p.isEmpty()) {
            ep.setError("Password field can't be empty");
            ep.requestFocus();
            wrong++;
        } else if (p.length() < 6) {
            ep.setError("Password must be minimum of 6 characters");
            ep.requestFocus();
            wrong++;
        }
        return wrong==0;
    }

    public void signup(View view) {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(mAuthSt);
    }

    public void resetpass(View view) {
        startActivity(new Intent(getApplicationContext(), ForgetPassActivity.class));
    }
}
