package com.hcmute.socialnetwork.activity.CustomActionBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class CustomActionBarActivity extends AppCompatActivity {

    protected void setupActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }
}
