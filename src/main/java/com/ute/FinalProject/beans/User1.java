package com.ute.FinalProject.beans;

import java.time.LocalDate;

public class User1 {
    private int ID;
    private String UserName, PassWord, Name, Email;
    private LocalDate Dob;
    private int Permission;
    private int Notify;

    public User1(int ID, String UserName, String PassWord, String Name, String Email, LocalDate Dob, int Permission, int Notify) {
        this.ID = ID;
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.Name = Name;
        this.Email = Email;
        this.Dob = Dob;
        this.Permission = Permission;
        this.Notify = Notify;
    }
    public User1(String Name, String UserName) {
        this.Name = Name;
        this.UserName = UserName;
    }
    public User1(String UserName, int Notify) {
        this.UserName = UserName;
        this.Notify = Notify;
    }

    public int getId() {
        return ID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public LocalDate getDob() {
        return Dob;
    }

    public int getPermission() {
        return Permission;
    }

    public int getNotify() {
        return Notify;
    }
}
