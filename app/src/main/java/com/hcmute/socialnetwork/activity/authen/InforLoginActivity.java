package com.hcmute.socialnetwork.activity.authen;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.CustomActionBar.CustomActionBarActivity;
import com.hcmute.socialnetwork.activity.MainActivity;
import com.hcmute.socialnetwork.model.User;
import com.hcmute.socialnetwork.utils.FirebaseUtils;

public class InforLoginActivity extends CustomActionBarActivity {
    EditText edtInforFName, edtInforLName, edtInforPass, edtInforCfPass;
    Button btnNext;
    String phoneNumber;
    User userModel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforlogin);
        setupActionBar();
        findID();
        // lấy ID
        phoneNumber = getIntent().getExtras().getString("phone");
        // lay du lieu do vao User
        getInforUser();
        // click next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInforUser();
            }
        });
    }

    private void setInforUser() {

        String firstName = edtInforFName.getText().toString();
        String lastName = edtInforLName.getText().toString();

        if(firstName.isEmpty() && lastName.isEmpty()){
            edtInforFName.setError("Nhập họ của bạn");
            edtInforLName.setError("Nhập tên của bạn");
            return;
        } else if(firstName.isEmpty() && !lastName.isEmpty()){
            edtInforFName.setError("Nhập họ của bạn");
            return;
        } else if(!firstName.isEmpty() && lastName.isEmpty()){
            edtInforLName.setError("Nhập tên của bạn");
            return;
        }
        if(userModel!=null){
            userModel.setFirstName(firstName);
            userModel.setLastName(lastName);
        } else{
            userModel = new User(phoneNumber,firstName,lastName, Timestamp.now());
        }

        FirebaseUtils.curentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(InforLoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }

    private void getInforUser() {
        FirebaseUtils.curentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    userModel = task.getResult().toObject(User.class);
                    if(userModel !=null){
                        edtInforFName.setText(userModel.getFirstName());
                        edtInforLName.setText(userModel.getLastName());
                    }
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
    }
}
