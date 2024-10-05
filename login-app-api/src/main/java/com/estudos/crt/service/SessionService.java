package com.estudos.crt.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import jakarta.servlet.http.Cookie;

public interface SessionService {
    void addCookie(Cookie cookie);

    void setAttribute(String key, Object value);

    Object getAttribute(String key) throws NotFoundException;

    void invalidate();
}
