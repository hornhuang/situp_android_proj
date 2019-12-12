package com.fishinwater.base.data.protocol;

/**
 * @author fishinwater-1999
 * @version 2019-12-12
 */
public class CollectionBean {

    private String collection_id;

    private String user_id;

    private String post_id;

    public CollectionBean() {
        super();
    }

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return "LikeBean [like_id=" + collection_id + ", user_id=" + user_id + ", post_id=" + post_id + "]";
    }

}
