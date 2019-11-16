package com.fishinwater.situp.classes.base;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class PlanBean {

    private String userName;

    private String userPassword;

    public PlanBean() {

    }

    public PlanBean(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "PlanBean{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

}
