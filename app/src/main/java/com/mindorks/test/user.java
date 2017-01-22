package com.mindorks.test;

/**
 * Created by Maddy on 21/01/17.
 */

public class user {


    private String id;
    private String name;
    private String emailId;
    private String password;
    private String url;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString() {
        return "LoginDetails [id=" + id +  ", name= " + name + ", emailId=" + emailId + ", password=" + password + ", url=" + url + "]";
    }
}
