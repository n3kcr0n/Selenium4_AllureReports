package org.coreFramework.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.coreFramework.generator.fakeDataGenerator;

public class TestUser {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @JsonIgnore
    private String accessToken;


    public TestUser(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public TestUser(){
        this.firstName = fakeDataGenerator.dataGen.name().firstName();
        this.lastName = fakeDataGenerator.dataGen.name().lastName();
        this.email = fakeDataGenerator.dataGen.internet().safeEmailAddress();
        this.password =firstName+"Test@1234";

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pw) {
        this.password = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
