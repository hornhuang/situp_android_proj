package com.fishinwater.postcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fishinwater.postcenter.ui.activity.UserFavoritePostsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // startActivity(new Intent(this, UserPostsActivity.class));
        UserFavoritePostsActivity.actionStart(this, "445417a0-560c-40c9-aeeb-bab98f501be1");
    }
}
