package com.corelogic.security.authn.ldap.dtos.Users;

public class UserResponse {

    //private Long id;
    //private String emailAddress;
    //private Boolean enabled;
    private String firstName;
    private String lastName;
    //private String username;
    //private String phoneNumber;

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

}
