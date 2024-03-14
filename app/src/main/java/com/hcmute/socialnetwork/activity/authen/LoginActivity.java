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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.MainActivity;
import com.hcmute.socialnetwork.helper.Validate;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    Button registerBtn,loginBtn,btnForgotpassword;
    EditText emailOrPhoneEditText,  passwordEditText;
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
                        emailOrPhone = "+"+emailOrPhone;
                        // Đăng nhập bằng số điện thoại
                        signInWithPhoneNumber(emailOrPhone,password);
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

    public void init(){
        registerBtn = findViewById(R.id.btnRegister);
        loginBtn = findViewById(R.id.btnLogin);
        btnForgotpassword = findViewById(R.id.btnForgotpassword);
        emailOrPhoneEditText = findViewById(R.id.edtLoginEmailorPhone);
        passwordEditText = findViewById(R.id.edtLoginPass);
    }

    private void signInWithPhoneNumber(String emailOrPhone, String passWord) {
        // Tạo tham chiếu đến Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Thực hiện truy vấn để lấy dữ liệu từ Firestore
        db.collection("account")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String phone = document.getString("phone");
                                String pass = document.getString("password");
                                if (phone.equals(emailOrPhone) && pass.equals(passWord)) {
                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                    return;
                                }
                            }
                            Toast.makeText(LoginActivity.this,"Kiểm tra tài khoản hoặc mật khẩu của bạn",Toast.LENGTH_SHORT).show();
                        } else {
                            Exception e = task.getException();
                            Log.d(TAG, "error"+e.toString());
                        }
                    }
                });
    }

    public void signInWithEmailAndPassword(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                            Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                        else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
    public boolean isPhoneNumberValid(String phoneNumber) {
        // Kiểm tra số điện thoại có chứa 10 hoặc 11 ký tự và chỉ chứa các chữ số không
        return phoneNumber.matches("^\\d{10,11}$");
    }


}