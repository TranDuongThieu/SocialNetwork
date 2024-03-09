package com.hcmute.socialnetwork.activity.authen;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.CustomActionBar.CustomActionBarActivity;
import com.hcmute.socialnetwork.activity.MainActivity;
import com.hcmute.socialnetwork.helper.Validate;
import com.hcmute.socialnetwork.model.Account;
import com.hcmute.socialnetwork.model.User;
import com.hcmute.socialnetwork.utils.AdroidUtils;
import com.hcmute.socialnetwork.utils.FirebaseUtils;

public class InforLoginActivity extends CustomActionBarActivity {
    EditText edtInforFName, edtInforLName, edtInforPass, edtInforCfPass;
    TextView txtInforLoginCreateName;
    Button btnNext;
    public static int i =0;
    String phoneNumber,otp, idFgPass;
    User userModel;
    Account accountModel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforlogin);
        setupActionBar();
        findID();
        idFgPass = getIntent().getExtras().getString("fgPass");
        Intent intent = getIntent();
        getMyIntent(intent);
        // Nếu đổi mật khẩu thì ẩn 2 thuộc tính này
        if(idFgPass.equals("1"))
        {
            edtInforFName.setVisibility(View.GONE);
            edtInforLName.setVisibility(View.GONE);
            txtInforLoginCreateName.setText("Tạo mật khẩu mới");
        }
        // lay du lieu do vao User
        //getInforUser();
        // click next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInforUser();
            }
        });
    }

    private void getMyIntent(Intent intent) {
        // Kiểm tra xem Intent có dữ liệu kèm theo hay không
        if (intent != null && intent.getExtras() != null) {
            // Trích xuất Bundle từ Intent
            Bundle bundle = intent.getExtras();
            // Kiểm tra xem Bundle có dữ liệu không
            if (bundle != null) {
                // Lấy ra số điện thoại từ Bundle
                phoneNumber = bundle.getString("phone");
                // Lấy ra mã OTP từ Bundle
                otp = bundle.getString("otp");
            }
        }
    }

    private void setInforUser() {
        String phoneNumber = getIntent().getExtras().getString("phone");
        String password = edtInforPass.getText().toString();
        String cfPass = edtInforCfPass.getText().toString();
        String firstName = edtInforFName.getText().toString();
        String lastName = edtInforLName.getText().toString();

        if(firstName.isEmpty() && !idFgPass.equals("1")) {
            edtInforFName.setError("Nhập họ của bạn");
            return;
        }
        if(lastName.isEmpty() && !idFgPass.equals("1")) {
            edtInforLName.setError("Nhập tên của bạn");
            return;
        }
        if(password.isEmpty()) {
            edtInforPass.setError("Nhập mật khẩu của bạn");
            return;
        }
        if(cfPass.isEmpty()) {
            edtInforCfPass.setError("Xác nhận mật khẩu của bạn");
            return;
        }
        if(!password.equals(cfPass)){
            edtInforPass.setError("Mật khẩu không trùng khớp");
            return;
        }
        // nếu có 1 đối tượng usermodel được tạo thì gán giá trị cho nó
        userModel = new User(phoneNumber,firstName,lastName, Timestamp.now());
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setPhoneNumber(phoneNumber);
        FirebaseUtils.curentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    createAccountinFireBase(phoneNumber,password,cfPass);
                }
            }
        });

    }

    private void findID() {
        edtInforFName = findViewById(R.id.edtInforLoginFName);
        edtInforLName = findViewById(R.id.edtInforLoginLName);
        edtInforPass = findViewById(R.id.edtInforLoginPass);
        edtInforCfPass = findViewById(R.id.edtInforLoginCfPass);
        btnNext = findViewById(R.id.btnInforLoginNext);
        txtInforLoginCreateName = findViewById(R.id.txtInforLoginCreateName);
    }


    public void createAccountinFireBase(String phoneNumber,String password,String cfPass) {
            if (password.equals(cfPass)) {
                // Hai chuỗi giống nhau
                if (!Validate.isPasswordValid(password)) {
                    Toast.makeText(InforLoginActivity.this, "Mật khẩu phải chứa ít nhất 6 kí tự", Toast.LENGTH_SHORT).show();
                } else {
                    accountModel = new Account(phoneNumber,password,otp, Timestamp.now());
                    accountModel.setPhone(phoneNumber);
                    accountModel.setOtp(otp);
                    accountModel.setPassword(password);
                    FirebaseUtils.accountDetail().set(accountModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent intent = new Intent(InforLoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    });
                }
            }
    }
}
