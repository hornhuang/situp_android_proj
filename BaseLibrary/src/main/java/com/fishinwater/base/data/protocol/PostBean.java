package com.fishinwater.base.data.protocol;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.fishinwater.base.ui.base.BaseCustomViewModel;

/**
 * @author fishinwater-1999
 * @version 2019-12-12
 */
public class PostBean extends BaseObservable {

    private String post_id;

    private String user_id;

    private String post_title;

    private String post_content;

    private String post_date;

    public PostBean() {
        super();
    }
    @Bindable
    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
        notifyPropertyChanged(BR.post_id);
    }
    @Bindable
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
        notifyPropertyChanged(BR.user_id);
    }
    @Bindable
    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
        notifyPropertyChanged(BR.post_title);
    }
    @Bindable
    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
        notifyPropertyChanged(BR.post_content);
    }
    @Bindable
    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
        notifyPropertyChanged(BR.post_date);
    }



}
