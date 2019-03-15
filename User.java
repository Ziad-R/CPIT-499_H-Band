package com.example.myapplication;

import android.media.Image;

public class User {
    String userName;
    String password;
    String fName;
    String lName;
    Image profilePicure;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        fName = "";
        lName = "";
        
        profilePicure = null;
    }

    public User(String userName, String password, String fName, String lName, String eMail, Image profilePicure) {
        this.userName = userName;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.eMail = eMail;
        this.profilePicure = profilePicure;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }



    public void setProfilePicure(Image profilePicure) {
        this.profilePicure = profilePicure;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }


    public Image getProfilePicure() {
        return profilePicure;
    }

    public void editThreshold(){

    }

    public void chat(){

    }

    public void editProfile(){

    }
}
