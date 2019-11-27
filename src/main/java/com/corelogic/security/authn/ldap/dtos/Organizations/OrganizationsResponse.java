package com.corelogic.security.authn.ldap.dtos.Organizations;

import java.util.List;

public class OrganizationsResponse {
    private List<OrganizationResponse> organizations;

    public List<OrganizationResponse> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrganizationResponse> organizations) {
        this.organizations = organizations;
    }
}

