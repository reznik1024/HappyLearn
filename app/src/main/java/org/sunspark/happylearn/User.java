package org.sunspark.happylearn;

public class User {
    String PhoneNumber,Name,FamilyName,Email,Password;

    public User() {

    }

    public User(String phoneNumber, String name, String familyName, String email, String password) {
        PhoneNumber = phoneNumber;
        Name = name;
        FamilyName = familyName;
        Email = email;
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getName() {
        return Name;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
}
