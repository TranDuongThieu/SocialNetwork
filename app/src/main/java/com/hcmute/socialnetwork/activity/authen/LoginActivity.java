package com.hcmute.socialnetwork.activity.authen;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.MainActivity;
import com.hcmute.socialnetwork.helper.Validate;
import com.hcmute.socialnetwork.model.User;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    Button registerBtn, loginBtn, btnForgotpassword;
    EditText emailOrPhoneEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Thiết lập null cho action bar
        getSupportActionBar().setTitle("");
        // Tham chiếu Id
        init();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailOrPhone = emailOrPhoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (emailOrPhone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin của bạn", Toast.LENGTH_SHORT).show();
                } else {
                    // Kiểm tra xem người dùng đã nhập số điện thoại hay địa chỉ email
                    if (isPhoneNumberValid(emailOrPhone)) {
                        emailOrPhone = "+" + emailOrPhone;
                        // Đăng nhập bằng số điện thoại
                        signInWithPhoneNumber(emailOrPhone, password);
                    } else if (Validate.isEmailValid(emailOrPhone)) {
                        // Đăng nhập bằng địa chỉ email
                        signInWithEmailAndPassword(emailOrPhone, password);
                    } else {
                        Toast.makeText(LoginActivity.this, "Vui lòng nhập địa chỉ email hoặc số điện thoại hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnForgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fgPass = "1";
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("fgPass", fgPass);
                startActivity(intent);

            }
        });
    }

    public void init() {
        registerBtn = findViewById(R.id.btnRegister);
        loginBtn = findViewById(R.id.btnLogin);
        btnForgotpassword = findViewById(R.id.btnForgotpassword);
        emailOrPhoneEditText = findViewById(R.id.edtLoginEmailorPhone);
        passwordEditText = findViewById(R.id.edtLoginPass);
    }

    private void signInWithPhoneNumber(String phone, String passwordInput) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Perform a query to retrieve the user document based on the provided email and password
        db.collection("account")
                .whereEqualTo("phone", phone)
                .whereEqualTo("password", passwordInput)
                .limit(1) // Limit the result to 1 document
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Check if any document is returned
                            if (!task.getResult().isEmpty()) {
                                // Retrieve the user document from the "user" collection based on the email
                                db.collection("user")
                                        .whereEqualTo("phoneNumber", phone)
                                        .limit(1) // Limit the result to 1 document
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (DocumentSnapshot userDocument : task.getResult()) {
                                                        // Convert the user document to a User object
                                                        User loginUser = userDocument.toObject(User.class);
                                                        setCurrentUser(loginUser);
                                                        // Set CurrentUser with the retrieved user information

                                                        // Set other user properties as needed
                                                    }
                                                    // Sign in successful, proceed to main activity
                                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                    finish();
                                                } else {
                                                    // Error occurred while retrieving user document
                                                    Toast.makeText(LoginActivity.this, "Lỗi khi lấy thông tin người dùng: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                // No user found with matching email and password
                                Toast.makeText(LoginActivity.this, "Kiểm tra tài khoản hoặc mật khẩu của bạn", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Error occurred while querying Firestore
                            Exception e = task.getException();
                            Log.d(TAG, "error" + e.toString());
                        }
                    }
                });
    }

    public void signInWithEmailAndPassword(String emailInput, String passwordInput) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Perform a query to retrieve the user document based on the provided email and password
        db.collection("account")
                .whereEqualTo("email", emailInput)
                .whereEqualTo("password", passwordInput)
                .limit(1) // Limit the result to 1 document
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Check if any document is returned
                            if (!task.getResult().isEmpty()) {
                                // Retrieve the user document from the "user" collection based on the email
                                db.collection("user")
                                        .whereEqualTo("email", emailInput)
                                        .limit(1) // Limit the result to 1 document
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (DocumentSnapshot userDocument : task.getResult()) {
                                                        // Convert the user document to a User object
                                                        User loginUser = userDocument.toObject(User.class);
                                                        setCurrentUser(loginUser);
                                                        // Set CurrentUser with the retrieved user information

                                                        // Set other user properties as needed
                                                    }
                                                    // Sign in successful, proceed to main activity
                                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                    finish();
                                                } else {
                                                    // Error occurred while retrieving user document
                                                    Toast.makeText(LoginActivity.this, "Lỗi khi lấy thông tin người dùng: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                // No user found with matching email and password
                                Toast.makeText(LoginActivity.this, "Kiểm tra tài khoản hoặc mật khẩu của bạn", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Error occurred while querying Firestore
                            Exception e = task.getException();
                            Log.d(TAG, "error" + e.toString());
                        }
                    }
                });
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        // Kiểm tra số điện thoại có chứa 10 hoặc 11 ký tự và chỉ chứa các chữ số không
        return phoneNumber.matches("^\\d{10,11}$");
    }

    public void setCurrentUser(User user) {
        User currentUser = User.getInstance();

        currentUser.setEmail(user.getEmail());
        currentUser.setUserName(user.getUserName());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setAvatar(user.getAvatar());
        currentUser.setBlogs(user.getBlogs());
        currentUser.setUserId(user.getUserId());
        currentUser.setFollowers(user.getFollowers());
        currentUser.setFollowings(user.getFollowings());
        currentUser.setGender(user.getGender());
        currentUser.setDateOfBirth(user.getDateOfBirth());
        currentUser.setPhoneNumber(user.getPhoneNumber());


    }
}