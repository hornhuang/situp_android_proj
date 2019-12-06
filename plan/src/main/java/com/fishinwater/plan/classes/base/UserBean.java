package com.fishinwater.plan.classes.base;

import java.util.UUID;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class UserBean {

    private UUID id;

    private String name;

    private String password;

    public UserBean() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
