package com.corelogic.security.authn.ldap.repos;

import com.corelogic.security.authn.ldap.entities.UserA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Repository
public class UserARepo {

    @Autowired
    private LdapTemplate ldaptemplate;

    public UserA create(UserA usera){
        DirContextAdapter context = new DirContextAdapter(usera.getDn());

        context.setAttributeValues("objectclass", new String[] { "top", "person", "uidObject" });
        context.setAttributeValue("cn", usera.getFirstName());
        context.setAttributeValue("sn", usera.getLastName());
        context.setAttributeValue( "userPassword", usera.getPassword());

        ldaptemplate.bind(context);
        return usera;
    }

    public UserA findByUid(String uid) {
        return ldaptemplate.findOne(query().where("uid").is(uid), UserA.class);
    }

    public void update (UserA usera) {
        ldaptemplate.update(usera);
    }

    public void delete (UserA usera) {
        ldaptemplate.delete(usera);
    }

    public List<UserA> findAll() {
        return ldaptemplate.findAll(UserA.class);
    }

    public List<UserA> findByLastName(String lastName) {
        return ldaptemplate.find(query().where("sn").is(lastName), UserA.class);
    }
}
