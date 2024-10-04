package com.estudos.crt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.crt.service.ServletResponseService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ServletResponseServiceImpl implements ServletResponseService{

    @Autowired
    private HttpServletResponse response;

    @Override
    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }

}
