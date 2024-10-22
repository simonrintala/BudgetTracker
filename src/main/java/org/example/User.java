package org.example;

public class User {
    private static int idCounter = 0;
    private int userID;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.userID = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
    } //vilkor för att skapa en user (ej i användning, hårkodat atm) FIX!!!!

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "userID=" + userID +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                '}';
//    }
}
