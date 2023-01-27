package com.example.meetior.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meetior.Misc.Checker;
import com.example.meetior.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private String n, u, p, role = "ind";
    private EditText en, eu, ep;
    private CheckBox ind,res;
    private FirebaseAuth mAuth;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        hidepass();
        ind = (CheckBox)findViewById(R.id.cbi);
        res = (CheckBox)findViewById(R.id.cbr);

        ind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    res.setChecked(false);
                }
            }
        });
        res.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ind.setChecked(false);
                }
            }
        });
    }

    private void hidepass() {

    }

    public void signup(View view) {
        en = findViewById(R.id.regname);
        eu = findViewById(R.id.regemail);
        ep = findViewById(R.id.regpass);
        n = en.getText().toString();
        u = eu.getText().toString();
        p = ep.getText().toString();
        if (infoOk(n,u,p)) {
            firebasereg(n, u, p);
        } else{
            Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_LONG).show();
        }
    }

    private void firebasereg(final String n, final String u, String p) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(u, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser curuser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = curuser.getUid();
                    db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Info");
                    HashMap<String, String> info = new HashMap<>();
                    if(!ind.isChecked()){
                        role = "res";
                    }
                    info.put("Name", n);
                    info.put("Email", u);
                    info.put("Role", role);
                    db.setValue(info);
                    Toast.makeText(getApplicationContext(), "Sign Up Successful", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Email is already in use", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error : " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean infoOk(String n,String u,String p) {
        Checker chk= new Checker();
        int wrong=0;
        if(!chk.name(n)){
            en.setError("Enter a valid name");
            en.requestFocus();
            wrong++;
        }
        if (u.isEmpty()) {
            eu.setError("Enter an email address");
            eu.requestFocus();
            wrong++;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(u).matches()) {
            eu.setError("Enter a valid email address");
            eu.requestFocus();
            wrong++;
        }
        if (p.isEmpty()) {
            ep.setError("Enter a password");
            ep.requestFocus();
            wrong++;
        }
        else if (p.length() < 6) {
            ep.setError("Password must be minimum of 6 characters");
            ep.requestFocus();
            wrong++;
        }
        else if (!chk.pass(p)) {
            ep.setError("Enter a valid password");
            ep.requestFocus();
            wrong++;
        }
        else if(!ind.isChecked() && !res.isChecked()){
            wrong++;
        }
        return wrong==0;
    }
}