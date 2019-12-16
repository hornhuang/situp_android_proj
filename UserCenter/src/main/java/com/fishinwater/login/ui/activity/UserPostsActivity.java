package com.fishinwater.login.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fishinwater.login.R;

@Route(path = "/usercenter/userposts")
public class UserPostsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);
    }

}
