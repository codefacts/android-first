package com.imslbd.android_first.model;

/**
 * Created by shahadat on 1/7/16.
 */
public class Contact {
    int id;
    String name;
    String phoneNumber;

    public Contact(String ravi, String s) {
        name = ravi;
        phoneNumber = s;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
