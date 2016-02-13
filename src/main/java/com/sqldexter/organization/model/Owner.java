package com.sqldexter.organization.model;

import com.google.gson.JsonObject;

/**
 * Created by HOME on 12-02-2016.
 */
public class Owner {

    private String name;
    private String email;
    private String contactNum;
    public Owner(String name, String email,String contactNum){
        this.name=name;
        this.email=email;
        this.contactNum=contactNum;
    }
    public Owner(JsonObject object){
        this.name=object.get("name").toString();
        this.email=object.get("email").toString();
        this.contactNum=object.get("contactNum").toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    @Override
    public String toString() {
        String str="name="+name+"\temail="+email+"\tcontactNum="+contactNum;
        return str;
    }
}
