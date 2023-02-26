package com.rahul.articleBlogApi.security.jwt;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JWTServiceTest {

    private JWTService jwtService = new JWTService();
    @Test
    void canCreateJWTFromUserId() {
        var userId = 1122;
        long timeStampOfTomorrow = new Date().getTime() + 86400000L;
        var jwt = jwtService.createJWT(userId, new Date(0),new Date(timeStampOfTomorrow));
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTIyIiwiZXhwIjoxNjc3OTIyMjg4LCJpYXQiOjE2NzczMTc0ODh9.JKborkNkoFejE6PMvTWujmDruOWD9O0yY5V-gX-6RnQ",jwt);
    }

    @Test
    void canVerifyJWT(){
        var jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTIyIiwiZXhwIjoxNjc3OTIyMjg4LCJpYXQiOjE2NzczMTc0ODh9.JKborkNkoFejE6PMvTWujmDruOWD9O0yY5V-gX-6RnQ";
        var userId = jwtService.getUserIdFromJWT(jwt);
        assertEquals(1122,userId);
    }
}
