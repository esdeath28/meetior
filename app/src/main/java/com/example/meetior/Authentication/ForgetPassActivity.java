package com.example.meetior.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meetior.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText forpassemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        mAuth = FirebaseAuth.getInstance();
    }
    public void sendforpassemail(View view) {
        forpassemail = findViewById(R.id.forpassemail);
        String email = forpassemail.getText().toString();
        if (email.isEmpty()) {
            forpassemail.setError("Enter an email address");
            forpassemail.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            forpassemail.setError("Enter a valid email address");
            forpassemail.requestFocus();
        } else {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Password reset link sent. Check your inbox", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ForgetPassActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
