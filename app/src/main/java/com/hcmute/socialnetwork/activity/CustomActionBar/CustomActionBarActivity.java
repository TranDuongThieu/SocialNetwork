package com.hcmute.socialnetwork.activity.CustomActionBar;

import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public abstract class CustomActionBarActivity extends AppCompatActivity {

    protected void setupActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

}
