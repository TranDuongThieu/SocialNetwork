package com.hcmute.socialnetwork.activity.fragment.editprofile;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.CustomActionBar.CustomActionBarActivity;

import java.util.Objects;


public class EditProfile extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chỉnh sửa thông tin cá nhân");
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
}
