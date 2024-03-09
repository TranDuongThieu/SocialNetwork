package com.hcmute.socialnetwork.activity.authen;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.CustomActionBar.CustomActionBarActivity;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends CustomActionBarActivity {
    EditText edtRegisterPhone;
    Button btnRegisterNext,btnRegisterEmail,btnRegisterHaveAccount;
    CountryCodePicker cpp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupActionBar();
        // tham chiêu ID
        findID();
        // lấy mã intent để phân biệt là đổi quên mk hay đăng kí tài khoản
        Intent intent = getIntent();
        String id = intent.getStringExtra("fgPass");
        // đăng kí mã quốc gia
        cpp.registerCarrierNumberEditText(edtRegisterPhone);
        btnRegisterNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtRegisterPhone.getText().toString().isEmpty() && edtRegisterPhone.getText().toString().length() >= 9 && edtRegisterPhone.getText().toString().length() <=11) {
                        // Tạo Intent và đặt dữ liệu vào nó

                        Intent intent = new Intent(RegisterActivity.this, GetOTP.class);
                        intent.putExtra("phone", cpp.getFullNumberWithPlus());
                        intent.putExtra("fgPass", id);
                        // Mở GetOTP Activity với Intent đã được đặt dữ liệu
                        startActivity(intent);
                }else {
                    edtRegisterPhone.setError("Số điện thoại không hợp lệ");
                }
            }
        });
        // Bắt Lệnh click Email
        btnRegisterEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, RegisterwithEmailActivity.class));
            }
        });
        btnRegisterHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void findID() {
        edtRegisterPhone = findViewById(R.id.edtRegisterPhone);
        btnRegisterNext = findViewById(R.id.btnRegisterNext);
        btnRegisterEmail = findViewById(R.id.btnRegisterEmail);
        btnRegisterHaveAccount = findViewById(R.id.btnRegisterHaveAccount);
        cpp = findViewById(R.id.cpp);

    }

}
