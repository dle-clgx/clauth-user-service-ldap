package com.corelogic.security.authn.ldap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.ldap.urls}")
    private String ldapUrl;

    @Value("${spring.ldap.base}")
    private String baseDn;

    @Value("${spring.ldap.username}")
    private String managerDn;

    @Value("${spring.ldap.password}")
    private String managerPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/users").permitAll()
                .antMatchers("/welcome").permitAll()
                .antMatchers("/echo").permitAll()
                .antMatchers("/v2/users").permitAll()
                .and()
                .csrf().disable();
    }

    /*
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        System.out.println(ldapUrl + baseDn + managerDn + managerPassword);
        auth.
                ldapAuthentication()
                        .userDnPatterns("uid={0},ou=People")
                        .groupSearchBase("ou=People")
                        .contextSource()
                                .url(ldapUrl + baseDn)
                                .managerDn(managerDn)
                                .managerPassword(managerPassword);
    }

     */

}
