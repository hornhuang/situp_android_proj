package com.fishinwater.base.ui.base;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author fishinwater-1999
 * @version 2019-12-03
 */
public class BaseActivity extends AppCompatActivity {

    public void loadRectImg(String url, ImageView view) {
        Glide.with(this)
                .load(url)
                .into(view);
    }

    public void loadCircleImg(String url, ImageView view) {
        Glide.with(this)
                .load(url)
                .apply(new RequestOptions().circleCrop())
                .into(view);
    }

}
