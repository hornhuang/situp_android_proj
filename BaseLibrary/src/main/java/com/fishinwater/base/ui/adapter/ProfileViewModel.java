package com.fishinwater.base.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fishinwater.base.R;

/**
 * @author fishinwater-1999
 * @version 2019-12-18
 */
public class ProfileViewModel extends BaseObservable {

    private String imageUrl = "https://img-blog.csdnimg.cn/20191218205438766.jpg";

    @Bindable
    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.place_houlder)
                .into(view);
    }

    @BindingAdapter({"circleImageUrl"})
    public static void loadCircleImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().circleCrop())
                .placeholder(R.drawable.place_houlder)
                .into(view);
    }
}
