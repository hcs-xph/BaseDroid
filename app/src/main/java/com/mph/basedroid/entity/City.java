package com.mph.basedroid.entity;

/**
 * Created by：hcs on 2016/12/26 10:55
 * e_mail：aaron1539@163.com
 */
public class City {

    private String parentCode;
    private String parentName;

    public City(String parentCode, String parentName) {
        this.parentCode = parentCode;
        this.parentName = parentName;
    }

    public City() {
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
