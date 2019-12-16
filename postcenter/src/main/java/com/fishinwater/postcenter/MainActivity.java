package com.fishinwater.postcenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.fishinwater.postcenter.databinding.ActivityMainBinding;
import com.fishinwater.postcenter.ui.activity.UserCollectPostsActivity;
import com.fishinwater.postcenter.ui.activity.UserFavoritePostsActivity;
import com.fishinwater.postcenter.ui.fragment.SquareFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // startActivity(new Intent(this, UserPostsActivity.class));

//        UserFavoritePostsActivity.actionStart(this, "445417a0-560c-40c9-aeeb-bab98f501be1");

        // UserCollectPostsActivity.actionStart(this, "445417a0-560c-40c9-aeeb-bab98f501be1");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, new SquareFragment()).commit();
    }
}
