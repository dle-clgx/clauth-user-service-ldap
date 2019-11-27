package com.corelogic.security.authn.ldap.entities;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import javax.persistence.GeneratedValue;

    @Entry(base = "o=corelogic", objectClasses = { "person", "uidObject", "top" })
    public class User  {

        @Id
        @GeneratedValue(generator = "UserIdSeq")
        private Name id;

        private @Attribute(name = "cn") String username;
        private @Attribute(name = "userPassword") String password;

        public User() {
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Name getId() {
            return id;
        }

        public void setId(Name id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return id + username;
        }

}
