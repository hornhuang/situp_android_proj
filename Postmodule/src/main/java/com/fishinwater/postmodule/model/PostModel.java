package com.fishinwater.postmodule.model;

import com.fishinwater.postmodule.Post;
import com.fishinwater.postmodule.callback.PostCallback;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class PostModel {

    public void post(String title, String content, PostCallback callback) {
        callback.failed(title + "|" + content);
    }

}
