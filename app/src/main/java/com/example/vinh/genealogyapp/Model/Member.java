package com.example.vinh.genealogyapp.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by VINH on 4/3/2016.
 */
public class Member implements Serializable{

    private int userID;
    private int memberID;
    private String name;
    private Date birthDate;
    private String address;
    private String birthPlace;
    private String gender;
    private int father;
    private String avatar;
    private boolean alive;

    public Member() {
    }

    public Member(int userID, int memberID, String name, Date birthDate, String address, String birthPlace, String gender, int father, String avatar, boolean alive) {
        this.userID = userID;
        this.memberID = memberID;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.birthPlace = birthPlace;
        this.gender = gender;
        this.father = father;
        this.avatar = avatar;
        this.alive = alive;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFather() {
        return father;
    }

    public void setFather(int father) {
        this.father = father;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


}
