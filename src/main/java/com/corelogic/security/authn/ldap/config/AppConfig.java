package com.corelogic.security.authn.ldap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.corelogic.security.authn.ldap.*"})
@Profile("default")
@EnableLdapRepositories(basePackages = "com.corelogic.security.authn.ldap.**")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(env.getRequiredProperty("spring.ldap.urls"));
        contextSource.setBase(env.getRequiredProperty("spring.ldap.base"));
        contextSource.setUserDn(env.getRequiredProperty("spring.ldap.username"));
        contextSource.setPassword(env.getRequiredProperty("spring.ldap.password"));
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }

}

