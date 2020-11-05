package com.ucb.medicalnow.Model;

public class UserAvatarModel {

    private String userFirstName;
    private String userFirstSurname;
    private String userSecondSurname;
    private char firstLetter;

    public UserAvatarModel() {
    }

    public UserAvatarModel(String userFirstName, String userFirstSurname, String userSecondSurname, char firstLetter) {
        this.userFirstName = userFirstName;
        this.userFirstSurname = userFirstSurname;
        this.userSecondSurname = userSecondSurname;
        this.firstLetter = firstLetter;
    }

    public UserAvatarModel(String userFirstName, String userFirstSurname, String userSecondSurname) {
        this.userFirstName = userFirstName;
        this.userFirstSurname = userFirstSurname;
        this.userSecondSurname = userSecondSurname;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserFirstSurname() {
        return userFirstSurname;
    }

    public void setUserFirstSurname(String userFirstSurname) {
        this.userFirstSurname = userFirstSurname;
    }

    public String getUserSecondSurname() {
        return userSecondSurname;
    }

    public void setUserSecondSurname(String userSecondSurname) {
        this.userSecondSurname = userSecondSurname;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }
}
