package com.corelogic.security.authn.ldap.dtos.Organizations;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateOrganizationRequest {

    @NotEmpty(message = "organization name is required")
    private String name;
    private String applicationCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }
}

