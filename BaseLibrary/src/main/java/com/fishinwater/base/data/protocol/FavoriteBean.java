package com.fishinwater.base.data.protocol;

/**
 * @author fishinwater-1999
 * @version 2019-12-12
 */
public class FavoriteBean {

    private String favorite_id;

    private String post_id;

    private String user_id;

    public FavoriteBean() {
        super();
    }

    public String getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(String favorite_id) {
        this.favorite_id = favorite_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "FavoriteBean [favorite_id=" + favorite_id + ", post_id=" + post_id + ", user_id=" + user_id + "]";
    }

}
