/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.webapp.config;

import org.displaytag.filter.ResponseOverrideFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.DispatcherType;

/**
 * Created by meskoj on 25/05/16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        FilterRegistrationBean<CharacterEncodingFilter> registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC, DispatcherType.ERROR);
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        registration.setFilter(characterEncodingFilter);
        registration.addUrlPatterns("/*");
        registration.setName("responseOverrideFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registration;
    }

    @Bean
    public FilterRegistrationBean responseOverrideFilterRegistration() {
        FilterRegistrationBean<ResponseOverrideFilter> registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR);
        registration.setFilter(new ResponseOverrideFilter());
        registration.addUrlPatterns("/*");
        registration.setName("responseOverrideFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registration;
    }

    @Bean
    public FilterRegistrationBean urlRewriteFilter() {
        FilterRegistrationBean<UrlRewriteFilter> registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR);
        registration.addInitParameter(
                "modRewriteConfText",
                "RewriteRule public/([0-9]{1,2}).([0-9]{1,2}).([0-9]{1,2})(|-(rc|beta|alpha).([0-9]{1,2})|-SNAPSHOT)/(.+).(css|js|png|jpg|jpeg|gif|svg|xml|webmanifest|json|eot|woff|ttf)$ public/$7.$8 [L]");
        registration.addUrlPatterns("/public/*");
        registration.setName("urlRewriteFilter");
        registration.setOrder(1);
        registration.setFilter(new UrlRewriteFilter());
        return registration;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
            .authorizeRequests()
            .antMatchers(
                    "/Images/**",
                    "/Css/**",
                    "/External-Css/**",
                    "/External-Css/**",
                    "/Font/**",
                    "/Js/**",
                    "/External-Js/**",
                    "/login",
                    "/forgotten-password.html").permitAll()
            .antMatchers(
                    "/dispatch.html",
                    "/home.html",
                    "/home/**").hasAnyRole("USER","ADMIN")
            .antMatchers(
                    "/admin.html",
                    "/admin/**").hasAnyRole("ADMIN")
            .and()
            .exceptionHandling().accessDeniedPage("/acessDenied.html")
            .and()
            .formLogin()
                .usernameParameter("username") /* BY DEFAULT IS username!!! */
                .passwordParameter("password") /* BY DEFAULT IS password!!! */
                .loginProcessingUrl("/login")
                .loginPage("/login.html")
                .defaultSuccessUrl("/home.html")
                .failureUrl("/login.html?error=errorOnLogin")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(false)
                .permitAll()
                .and()
            .sessionManagement().maximumSessions(100).expiredUrl("/login.html");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/static/**");
    }

    //bean for md5 encryption
    @Bean
    public PasswordEncoder passwordEncoder() throws Exception {
        return new MessageDigestPasswordEncoder("MD5");
    }

}
