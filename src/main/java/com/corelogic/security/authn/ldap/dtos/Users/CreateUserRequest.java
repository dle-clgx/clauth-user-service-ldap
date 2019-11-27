package com.corelogic.security.authn.ldap.dtos.Users;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateUserRequest extends UserRequest {

    private String emailAddress;
    private String applicationCode;
    private String applicationName;

    //private String customCreateUserEmailText;


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /*
    public String getCustomCreateUserEmailText() {
        return customCreateUserEmailText;
    }

    public void setCustomCreateUserEmailText(String customCreateUserEmailText) {
        this.customCreateUserEmailText = customCreateUserEmailText;
    }

     */

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                ", applicationCode='" + applicationCode + '\'' +
                ", applicationName='" + applicationName + '\'' +
                '}' +
                super.toString();
    }
}