package com.hcmute.socialnetwork.activity.CustomActionBar;

import androidx.appcompat.app.AppCompatActivity;

public class CustomActionBarActivity extends AppCompatActivity {

    protected void setupActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

}
