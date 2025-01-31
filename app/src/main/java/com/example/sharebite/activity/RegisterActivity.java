package com.example.sharebite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharebite.R;
import com.example.sharebite.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.ProgressBar;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mAuth = FirebaseAuth.getInstance();


        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        progressBar = findViewById(R.id.progressBar);


        binding.alreadyhavebutton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });


        binding.createAccountButton.setOnClickListener(v -> createAccount());
    }

    private void createAccount() {
        String name = binding.name.getText().toString();
        String email = binding.editTextTextEmailAddress2.getText().toString();
        String password = binding.editTextTextPasswordd2.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    progressBar.setVisibility(View.GONE);

                    if (task.isSuccessful()) {

                        startActivity(new Intent(RegisterActivity.this, ChooseLocationActivity.class));
                        finish();
                    } else {

                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
