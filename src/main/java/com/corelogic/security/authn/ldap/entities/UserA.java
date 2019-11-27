package com.corelogic.security.authn.ldap.entities;


import org.springframework.ldap.odm.annotations.*;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;

@Entry(objectClasses = {"Person", "top", "uidObject"})
public final class UserA {

    private String basedn = "dc=example,dc=com";
    private String username;
    private String emailAddress;
    private String orgid;
    private String applicationCode;
    private String applicationName;

    @Id
    private Name dn;

    @DnAttribute(value = "uid")
    private String uid;

    @Attribute(name = "cn")
    private String firstName;

    @Attribute(name = "sn")
    private String lastName;

    @Attribute(name = "userPassword")
    private String password;

    @Attribute(name = "telephoneNumber")
    private String phoneNumber;

    public UserA() {

    }

    public void buildDn() {
        Name dn = LdapNameBuilder.newInstance()
                .add("o", "corelogic")
                .add("uid", this.firstName + " " + this.lastName)
                .build();
        this.dn = dn;
        System.out.println("dn ==> " + dn);
    }

    public String getBasedn() {
        return basedn;
    }

    public void setBasedn(String basedn) {
        this.basedn = basedn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
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

    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

