package com.example.myapplication;

import android.media.Image;

public class User {
    String userName;
    String password;
    String fName;
    String lName;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        fName = "";
        lName = "";


    }

    public User() {
    }

    public User(String userName, String password, String fName, String lName, String eMail) {
        this.userName = userName;
        this.password = password;
        this.fName = fName;
        this.lName = lName;


    }


    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }




    public void editThreshold(){

    }

    public void chat(){

    }

    public void editProfile(){

    }
}
