package com.example.meetior.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.example.meetior.Authentication.LoginActivity;
import com.example.meetior.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseUser curuser;
    private DatabaseReference db;
    private TextView tvprofname,tvprofemail;
    private String sprofname,sprofemail;
    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        tvprofemail = root.findViewById(R.id.profemail);
        tvprofname = root.findViewById(R.id.profname);
        setinfo();
        return root;
    }

    private void setinfo() {
        FirebaseUser curuser = FirebaseAuth.getInstance().getCurrentUser();
        if (curuser != null) {
            String uid = curuser.getUid();
            db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Info");
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot childsnap : dataSnapshot.getChildren()) {
                        if (childsnap.getKey().equals("Name")) {
                            if (childsnap.getValue() != null) {
                                tvprofname.setText((CharSequence) childsnap.getValue());
                            }
                        } else if (childsnap.getKey().equals("Email")) {
                            if (childsnap.getValue() != null) {
                                tvprofemail.setText((CharSequence) childsnap.getValue());
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}