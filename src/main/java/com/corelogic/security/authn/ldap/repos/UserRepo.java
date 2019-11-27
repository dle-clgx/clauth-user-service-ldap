package com.corelogic.security.authn.ldap.repos;

import java.util.List;

import com.corelogic.security.authn.ldap.entities.User;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends LdapRepository<User> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findByUsernameLikeIgnoreCase(String username);

}