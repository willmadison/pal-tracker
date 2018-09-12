package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final EnvironmentConfiguration envConfig;

    public SecurityConfiguration(EnvironmentConfiguration envConfig) {
        this.envConfig = envConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (!envConfig.disableHttps) {
            http.requiresChannel().anyRequest().requiresSecure();
        }

        http.authorizeRequests().antMatchers("/**").hasRole("USER")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetails userDetails = User.withUsername("user").password("{noop}password").roles("USER").build();
        auth.inMemoryAuthentication()
                .withUser(userDetails);
    }
}