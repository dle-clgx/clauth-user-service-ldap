package com.corelogic.security.authn.ldap.services;

import com.corelogic.security.authn.ldap.dtos.Users.UserResponse;
import com.corelogic.security.authn.ldap.entities.User;
import com.corelogic.security.authn.ldap.entities.UserA;
import com.corelogic.security.authn.ldap.repos.LdapRepo;
import com.corelogic.security.authn.ldap.repos.UserARepo;
import com.corelogic.security.authn.ldap.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private LdapRepo ldapRepo;

    @Autowired
    private UserARepo userARepo;

    public Boolean authenticate(final String username, final String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user != null;
    }

    public List<String> search(final String username) {
        List<User> userList = userRepository.findByUsernameLikeIgnoreCase(username);
        if (userList == null) {
            return Collections.emptyList();
        }

        return userList.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
/*
    public void create(final String username, final String password) {
        User newUser = new User(username,digestSHA(password));
        newUser.setId(LdapUtils.emptyLdapName());
        userRepository.save(newUser);

    }
 */


    public UserResponse create( final String firstName, final String lastName, final String password) {
        User newUser = new User(firstName + lastName, password);
        System.out.println( newUser.toString() );
        ldapRepo.create(firstName + lastName, digestSHA(password));
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(firstName);
        userResponse.setLastName(lastName);
        return userResponse;


    }

    public UserResponse createA( UserA usera ) {
        usera.buildDn();
        userARepo.create(usera);
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(usera.getFirstName());
        userResponse.setLastName(usera.getLastName());
        return userResponse;
    }

    public void modify(final String username, final String password) {
        User user = userRepository.findByUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    private String digestSHA(final String password) {
        String base64;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            base64 = Base64.getEncoder()
                    .encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "{SSHA256}" + base64;
    }
}
