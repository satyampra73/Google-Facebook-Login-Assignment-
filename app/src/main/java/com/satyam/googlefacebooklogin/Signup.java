package com.satyam.googlefacebooklogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private Button btnSign, btnRegis;
    EditText etEmail, etPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSign = findViewById(R.id.btnSignin);
        btnRegis = findViewById(R.id.btnRegis);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        auth = FirebaseAuth.getInstance();
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = etEmail.getText().toString().trim();
                String password1 = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email1) && TextUtils.isEmpty(password1)) {
                    Toast.makeText(Signup.this, "Field Can't be empty ", Toast.LENGTH_SHORT).show();
                } else {
                    regis(email1, password1);
                }

            }
        });
    }

    private void regis(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Signup.this, "Registration Completed Now Login with same", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Signup.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}