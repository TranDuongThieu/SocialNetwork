package com.hcmute.socialnetwork.activity.fragment.editprofile;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.CustomActionBar.CustomActionBarActivity;
import com.hcmute.socialnetwork.activity.fragment.ProfileFragment;
import com.hcmute.socialnetwork.model.User;
import com.hcmute.socialnetwork.utils.AdroidUtils;
import com.hcmute.socialnetwork.utils.FirebaseUtils;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class EditProfile extends AppCompatActivity {

    ActivityResultLauncher<Intent> imagePickLauncher;
    CircleImageView imgEditProfileAvatar;
    Uri selectedImageUri;
    User currentUserModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chỉnh sửa thông tin cá nhân");
        findID();
        setupImagePicker();
    }
    // Hàm tạo trình quản lý kết quả cho hoạt động chọn ảnh
    private void setupImagePicker() {
        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode()== Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data!=null && data.getData()!=null){
                            selectedImageUri = data.getData();
                            setProfilePicAndUpload(selectedImageUri);
                        }
                    }
                }
        );
        imgEditProfileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(EditProfile.this).cropSquare().compress(512).maxResultSize(512,512)
                        .createIntent(new Function1<Intent, Unit>() {
                            @Override
                            public Unit invoke(Intent intent) {
                                imagePickLauncher.launch(intent);
                                return null;
                            }
                        });
            }
        });
    }

    private void setProfilePicAndUpload(Uri imageUri) {
        if(imageUri != null) {
            AdroidUtils.setProfilePic(EditProfile.this, imageUri, imgEditProfileAvatar);
            FirebaseUtils.getCurrentAvatar().putFile(imageUri);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Kết thúc hoạt động chỉnh sửa Profile
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void findID(){
        imgEditProfileAvatar = findViewById(R.id.imgEditProfileAvatar);
    }
}
