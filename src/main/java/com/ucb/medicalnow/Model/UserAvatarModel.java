package com.ucb.medicalnow.Model;

public class UserAvatarModel {

    private char firstLetter;
    private String firstName;
    private String firstSurname;
    private String secondSurname;

    public UserAvatarModel() {
    }

    public UserAvatarModel(char firstLetter, String firstName, String firstSurname, String secondSurname) {
        this.firstLetter = firstLetter;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
    }

    public UserAvatarModel(String firstName, String firstSurname, String secondSurname) {
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    @Override
    public String toString() {
        return "UserAvatarModel{" +
                "firstLetter=" + firstLetter +
                ", firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                '}';
    }
}
