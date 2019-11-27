package com.corelogic.security.authn.ldap.util;

import org.springframework.stereotype.Component;

@Component
public class SanitizerUtil {
    public String sanitizeInput(String inputString) {
        if(inputString == null ) {
            return "";
        }

        return inputString.replace("\n", "_").replace("\r", "_").replace("`", "_");
    }
}