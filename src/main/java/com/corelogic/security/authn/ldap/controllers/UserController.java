package com.corelogic.security.authn.ldap.controllers;

import com.corelogic.security.authn.ldap.dtos.Users.CreateUserRequest;
import com.corelogic.security.authn.ldap.dtos.Users.UserResponse;
import com.corelogic.security.authn.ldap.entities.UserA;
import com.corelogic.security.authn.ldap.services.UserService;
import com.corelogic.security.authn.ldap.util.SanitizerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private SanitizerUtil sanitizerUtil;
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController (SanitizerUtil sanitizerUtil,
                        UserService userService) {
        this.sanitizerUtil = sanitizerUtil;
        this.userService = userService;
    }

    /* JSON format
    {
        "firstname": "first",
        "lastname": "last",
        "applicationcode": "appcode",
        "applicationname": "appname"
    }
        */

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to home page!";
    }

    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public ResponseEntity echo(@RequestBody CreateUserRequest createUserRequest ) {
        System.out.println("...get request ...\n");
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName("jian");
        userResponse.setLastName("zhang");
        return new ResponseEntity<>( userResponse, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody CreateUserRequest createUserRequest ){
        System.out.println("...get request ...\n");
        String firstName = sanitizerUtil.sanitizeInput(createUserRequest.getFirstName());
        String lastName = sanitizerUtil.sanitizeInput(createUserRequest.getLastName());

        LOG.info("saving user info. ", firstName + lastName );
        UserResponse userResponse = userService.create(firstName, lastName, "12345678");
        return new ResponseEntity<>( userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/v2/users")
    public ResponseEntity createUserA(@RequestBody CreateUserRequest createUserRequest ) {
        LOG.info(createUserRequest.toString());
        UserA usera = new UserA();
        usera.setFirstName(sanitizerUtil.sanitizeInput(createUserRequest.getFirstName()));
        usera.setLastName(sanitizerUtil.sanitizeInput(createUserRequest.getLastName()));
        usera.setPhoneNumber(sanitizerUtil.sanitizeInput(createUserRequest.getPhoneNumber()));
        usera.setPassword(sanitizerUtil.sanitizeInput(createUserRequest.getPassword()));
        usera.setEmailAddress(sanitizerUtil.sanitizeInput(createUserRequest.getEmailAddress()));
        usera.setApplicationCode(sanitizerUtil.sanitizeInput(createUserRequest.getApplicationCode()));
        usera.setApplicationName(sanitizerUtil.sanitizeInput(createUserRequest.getApplicationName()));
        UserResponse userResponse = userService.createA(usera);
        return new ResponseEntity<>( userResponse, HttpStatus.CREATED);
    }


}
