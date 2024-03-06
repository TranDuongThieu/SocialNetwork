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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.CustomActionBar.CustomActionBarActivity;
import com.hcmute.socialnetwork.utils.AdroidUtils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class GetOTP extends CustomActionBarActivity {

    String phoneNumber, verificationCode;
    Long timeoutSecond = 60L;
    EditText otpInput;
    Button btnNext, btnResentOtp;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    PhoneAuthProvider.ForceResendingToken resendingToken;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_getotp);
        // Setup back và actionBar bên trên
        setupActionBar();
        // Bắt intent để lấy số điện thoại
        phoneNumber = getIntent().getExtras().getString("phone");
        //Toast.makeText(getApplicationContext(),phoneNumber, Toast.LENGTH_SHORT).show();
        // tham chiếu ID
        findID();
        // Gui OTP
        sendOTP(phoneNumber,false);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOtp = otpInput.getText().toString();
                Log.d(TAG, "onclick=> enterOTP----> " + enteredOtp);
                Log.d(TAG, "onclick=> verificationCode----> " + verificationCode);
                // code lỗi
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode,enteredOtp);
                Singin(credential);
            }
        });

        btnResentOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP(phoneNumber,true);
            }
        });
    }

    private void Singin(PhoneAuthCredential phoneAuthCredential) {
        // đăng nhập và đến activity tiếp theo
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // đặt intent nhầm để xíu sửa
                    AdroidUtils.showToast(getApplicationContext(),"Xác minh OTP thành công:)");
                    Intent myIntent = new Intent(GetOTP.this, InforLoginActivity.class);
                    myIntent.putExtra("phone",phoneNumber);
                    startActivity(myIntent);
                }else {
                    AdroidUtils.showToast(getApplicationContext(),"Xác minh OTP không thành công:(");
                }
            }
        });
    }

    void sendOTP(String phoneNumber, Boolean isResend){
        // hẹn giờ gửi lại OTP
        startResentTimer();
        // Tạo đối tượng cấu hình liên quan đến số dth
        PhoneAuthOptions.Builder builder = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(timeoutSecond, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        // ung dung tu nhap OTP
                        Singin(phoneAuthCredential);
                    }
                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        AdroidUtils.showToast(getApplicationContext(),"Xác minh OTP không thành công, kiểm tra lại số điện thoại của bạn");
                    }
                    // nếu mã gửi thành công
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s,forceResendingToken);
                        // s chứa mã xác thực
                        // forceResendingToken chứa thông tin việc gửi lại mã xác thực
                        resendingToken = forceResendingToken;
                        verificationCode = s;
                        AdroidUtils.showToast(getApplicationContext(),"OTP đã gửi thành công");
                    }
                });
        if(isResend){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        } else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }
    }

    private void startResentTimer() {
        btnResentOtp.setEnabled(false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeoutSecond--;
                btnResentOtp.setText("Gửi lại OTP sau " + timeoutSecond + " giây");
                if(timeoutSecond <= 0){
                    timeoutSecond = 60L;
                    timer.cancel();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnResentOtp.setText("Tôi không nhận được mã?");
                            btnResentOtp.setEnabled(true);
                        }
                    });
                }
            }
        },0,1000);
    }
    public void findID() {
        otpInput = findViewById(R.id.edtRegisterInputGetotp);
        btnNext = findViewById(R.id.btnRegisterNextOTP);
        btnResentOtp = findViewById(R.id.btnRegisterResentOtp);
    }
}
