package com.corelogic.security.authn.ldap.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.*;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Repository
public class LdapRepo {
    @Autowired
    private Environment env;

    @Autowired
    private ContextSource contextSource;

    @Autowired
    private LdapTemplate ldapTemplate;

/*
    public void authenticate(final String username, final String password) {
        contextSource.getContext("cn=" + username + ",ou=users," + env.getRequiredProperty("ldap.partitionSuffix"), password);
    }
*/
    public List<String> search(final String username) {
        return ldapTemplate.search(
                "o=corelogic",
                "cn=" + username,
                (AttributesMapper<String>) attrs -> (String) attrs
                        .get("cn")
                        .get());
    }

    public void create(final String username, final String password) {
        Name dn = LdapNameBuilder
                .newInstance()
                .add("o", "corelogic")
                .add("uid", username)
                .build();
        System.out.println("dn ==> " + dn);
        DirContextAdapter context = new DirContextAdapter(dn);

        System.out.println("username = " + username);
        context.setAttributeValues("objectclass", new String[] { "top", "person", "uidObject" });
        context.setAttributeValue("cn", username);
        context.setAttributeValue("sn", username);
        context.setAttributeValue("userPassword", digestSHA(password));

        System.out.println( context );
        ldapTemplate.bind(context);
    }

    public void modify(final String username, final String password) {
        Name dn = LdapNameBuilder
                .newInstance()
                .add("ou", "users")
                .add("cn", username)
                .build();
        DirContextOperations context = ldapTemplate.lookupContext(dn);

        context.setAttributeValues("objectclass", new String[] { "top", "person", "uidObject" });
        context.setAttributeValue("cn", username);
        context.setAttributeValue("sn", username);
        context.setAttributeValue("uid", "100");
        context.setAttributeValue("userPassword", digestSHA(password));

        ldapTemplate.modifyAttributes(context);
    }

    private String digestSHA(final String password) {
        String base64;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            base64 = Base64
                    .getEncoder()
                    .encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "{SHA}" + base64;
    }

}
