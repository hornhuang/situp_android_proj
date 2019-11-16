package com.fishinwater.situp.classes.base;

import java.util.Date;
import java.util.List;


public class Day {

    private List<String> planList;

    private String conclusion;

    private int degree;

    private Date createDate;

    public Day() {
    }

    public List<String> getPlanList() {
        return planList;
    }

    public void setPlanList(List<String> planList) {
        this.planList = planList;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
