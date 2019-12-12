package com.fishinwater.base.data.protocol;

/**
 * @author fishinwater-1999
 * @version 2019-12-12
 */
public class DayBean {

    private String day_id;
    private String user_id;
    private String day_plans;
    private String day_date;

    public DayBean() {
        super();
    }
    public String getDay_id() {
        return day_id;
    }
    public void setDay_id(String day_id) {
        this.day_id = day_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getDay_plans() {
        return day_plans;
    }
    public void setDay_plans(String day_plans) {
        this.day_plans = day_plans;
    }
    public String getDay_date() {
        return day_date;
    }
    public void setDay_date(String day_date) {
        this.day_date = day_date;
    }
    @Override
    public String toString() {
        return "DayBean [day_id=" + day_id + ", user_id=" + user_id + ", day_plans=" + day_plans + ", day_date="
                + day_date + "]";
    }

}
