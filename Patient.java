package com.example.myapplication;

public class Patient extends User{
    int age;
    int height;
    int weight;
    int minPulse;
    int maxPulse;

    public Patient(String userName, String password, int age, int height, int weight, int minPulse, int maxPulse) {
        super(userName, password);
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.minPulse = minPulse;
        this.maxPulse = maxPulse;
    }

    public Patient(int age, int height, int weight, int minPulse, int maxPulse) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.minPulse = minPulse;
        this.maxPulse = maxPulse;
    }

    public Patient(String userName, String password, String fName, String lName, String eMail, int age, int height, int weight, int minPulse, int maxPulse) {
        super(userName, password, fName, lName, eMail);
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.minPulse = minPulse;
        this.maxPulse = maxPulse;
    }

    public Patient() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMinPulse() {
        return minPulse;
    }

    public void setMinPulse(int minPulse) {
        this.minPulse = minPulse;
    }

    public int getMaxPulse() {
        return maxPulse;
    }

    public void setMaxPulse(int maxPulse) {
        this.maxPulse = maxPulse;
    }
}
