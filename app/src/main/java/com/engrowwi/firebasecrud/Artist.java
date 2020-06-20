package com.engrowwi.firebasecrud;


import java.util.List;

public class Artist  {


    private String id;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String gender1;
    private String country;
    private String  age;

    public Artist() {
    }

    public Artist(String id, String firstName, String secondName, String thirdName , String gender1, String country , String age) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.gender1 = gender1;
        this.country = country;
        this.age = age;

    }

    public String getAge() {
        return age;
    }

    public String getGender1() {
        return gender1;
    }


    public String getCountry() {
        return country;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

}
