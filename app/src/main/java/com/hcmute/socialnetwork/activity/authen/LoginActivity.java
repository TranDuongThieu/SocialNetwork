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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Thiết lập null cho action bar
        getSupportActionBar().setTitle("");
        // Tham chiếu Id
        Button registerBtn = findViewById(R.id.btnRegister);
        Button loginBtn = findViewById(R.id.btnLogin);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                //finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailOrPhoneEditText = findViewById(R.id.edtLoginEmailorPhone);
                EditText passwordEditText = findViewById(R.id.edtLoginPass);
                String emailOrPhone = emailOrPhoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (emailOrPhone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập địa chỉ email hoặc số điện thoại và mật khẩu", Toast.LENGTH_SHORT).show();
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
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String phone = document.getString("phone");
                                String pass = document.getString("password");
                                Log.d(TAG, "Document" + phone);
                                Log.d(TAG, "finalEmailOrPhone" + emailOrPhone);
                                Log.d(TAG, "pass" + pass);
                                if (phone.equals(emailOrPhone) && pass.equals(passWord)) {
                                    // Nếu tìm thấy sự khớp, thực hiện hành động tương ứng ở đây
//                                    Log.d(TAG, "Match found for emailOrPhone: " + emailOrPhone);
                                    // Ví dụ: Gọi hàm xử lý đăng nhập
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    return; // Kết thúc vòng lặp khi đã tìm thấy sự khớp
                                }
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    public void signInWithEmailAndPassword(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                        } else {
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