package com.hcmute.socialnetwork.activity.authen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.helper.Validate;

public class RegisterwithEmail_Activity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_email);
        // Thiết lập null cho action bar
        // Thiết lập button back cho register

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("");

        Button registerButton = findViewById(R.id.btnRegister);
        Button loginBtn = findViewById(R.id.btnlogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterwithEmail_Activity.this, LoginActivity.class));
                finish();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText emailEditText = findViewById(R.id.edtEmail);
                EditText passwordEditText = findViewById(R.id.edtPass);
                EditText confirmPasswordEditText = findViewById(R.id.edtCfPass);
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (!Validate.isEmailValid(email)) {
                    Toast.makeText(RegisterwithEmail_Activity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                } else if (!Validate.isPasswordValid(password)) {
                    Toast.makeText(RegisterwithEmail_Activity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterwithEmail_Activity.this, "Confirm Password does not match", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed with registration
                    auth = FirebaseAuth.getInstance();
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterwithEmail_Activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(RegisterwithEmail_Activity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterwithEmail_Activity.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(RegisterwithEmail_Activity.this, LoginActivity.class));
                                        finish();
                                    }
                                }
                            });

                }
            }
        });
    }
}
