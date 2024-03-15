package com.hcmute.socialnetwork.activity.authen;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.CustomActionBar.CustomActionBarActivity;
import com.hcmute.socialnetwork.activity.MainActivity;
import com.hcmute.socialnetwork.helper.Validate;
import com.hcmute.socialnetwork.model.Account;
import com.hcmute.socialnetwork.model.User;

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
                EditText fNameEditText = findViewById(R.id.edtEmailRegisterFName);
                EditText lNameEditText = findViewById(R.id.edtEmailRegisterLName);
                String fName = fNameEditText.getText().toString();
                String lName = lNameEditText.getText().toString();
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
                    // Check if email already exists in Firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference accountsRef = db.collection("accounts");
                    Query query = accountsRef.whereEqualTo("email", email);
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().isEmpty()) {

                                    Account newAccount = new Account();
                                    newAccount.setEmail(email);
                                    newAccount.setPassword(password);
                                    User newUser = new User();
                                    newUser.setEmail(email);
                                    newUser.setFirstName((fName));
                                    newUser.setLastName(lName);
                                    CollectionReference accountRef = db.collection("account");

                                    CollectionReference usersRef = db.collection("user");
                                    accountRef.document(email).set(newAccount).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            usersRef.document(email).set(newUser)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            // User document successfully created
                                                            Toast.makeText(RegisterwithEmailActivity.this, "User document created successfully", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(RegisterwithEmailActivity.this, LoginActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            // Failed to create user document
                                                            Toast.makeText(RegisterwithEmailActivity.this, "Failed to create user document: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    });

                                    // Email does not exist, proceed with registration

                                } else {
                                    // Email already exists
                                    Toast.makeText(RegisterwithEmailActivity.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Error occurred while checking email existence
                                Toast.makeText(RegisterwithEmailActivity.this, "Lỗi khi kiểm tra email: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                    });
                }
            }
        });
    }
}