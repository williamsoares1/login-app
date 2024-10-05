package com.estudos.crt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.estudos.crt.service.SessionService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Override
    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }

    @Override
    public void setAttribute(String key, Object value) {
        HttpSession session = request.getSession(true);
        if (session != null) {
            session.setAttribute(key, value);
        }
    }

    @Override
    public Object getAttribute(String key) throws NotFoundException {
        HttpSession session = request.getSession(false);
        
        if (session == null) {
            throw new NotFoundException();
        }

        return session.getAttribute(key);
    }

    @Override
    public void invalidate() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
