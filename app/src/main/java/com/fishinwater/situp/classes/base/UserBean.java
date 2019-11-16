package com.fishinwater.situp.classes.base;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class UserBean {

    private int id;

    private String name;

    private String password;

    public UserBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
