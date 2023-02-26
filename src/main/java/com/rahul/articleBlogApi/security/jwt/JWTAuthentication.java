package com.rahul.articleBlogApi.security.jwt;

import com.rahul.articleBlogApi.users.UserEntity;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;

public class JWTAuthentication implements Authentication {
    private String token;
    //private UserEntity userEntity;
    private Integer userId;

    public JWTAuthentication(String token) {
        this.token = token;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Integer getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return userId != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
