package com.estudos.crt.service.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.crt.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private HttpSession session;

    @Override
    public void setAttribute(String key, Object value) {
        session.setAttribute(key, value);
    }

    @Override
    public Object getAttribute(String key) {
        return session.getAttribute(key);
    }

    @Override
    public void invalidate() {
        session.invalidate();
    }
}

