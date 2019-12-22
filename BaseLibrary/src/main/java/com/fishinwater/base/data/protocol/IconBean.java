package com.fishinwater.base.data.protocol;

/**
 * @author fishinwater-1999
 * @version 2019-12-19
 */
public class IconBean {

    private String icon_id;

    private String icon_url;

    private String icon_flag;

    public String getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(String icon_id) {
        this.icon_id = icon_id;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getIcon_flag() {
        return icon_flag;
    }

    public void setIcon_flag(String icon_flag) {
        this.icon_flag = icon_flag;
    }

    @Override
    public String toString() {
        return "IconBean [icon_id=" + icon_id + ", icon_url=" + icon_url + ", icon_flag=" + icon_flag + "]";
    }

}
