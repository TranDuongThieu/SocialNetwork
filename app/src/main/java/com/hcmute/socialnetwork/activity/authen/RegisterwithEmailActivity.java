package com.hcmute.socialnetwork.activity.authen;

import androidx.annotation.NonNull;

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
import com.hcmute.socialnetwork.activity.CustomActionBar.CustomActionBarActivity;
import com.hcmute.socialnetwork.helper.Validate;

public class RegisterwithEmailActivity extends CustomActionBarActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_email);
        // Thiết lập null cho action bar
        // Thiết lập button back cho register
        setupActionBar();

        Button registerButton = findViewById(R.id.btnRegister);
        Button loginBtn = findViewById(R.id.btnlogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterwithEmailActivity.this, LoginActivity.class));
                finish();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText emailEditText = findViewById(R.id.edtRegisteremailMail);
                EditText passwordEditText = findViewById(R.id.edtRegisteremailPass);
                EditText confirmPasswordEditText = findViewById(R.id.edtRegisteremailCfpass);
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (!Validate.isEmailValid(email)) {
                    Toast.makeText(RegisterwithEmailActivity.this, "Địa chỉ email không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (!Validate.isPasswordValid(password)) {
                    Toast.makeText(RegisterwithEmailActivity.this, "Mật khẩu phải chứa ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterwithEmailActivity.this, "Xác nhận mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed with registration
                    auth = FirebaseAuth.getInstance();
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterwithEmailActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(RegisterwithEmailActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterwithEmailActivity.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(RegisterwithEmailActivity.this, LoginActivity.class));

                                    }
                                }
                            });
                }
            }
        });
    }
}
