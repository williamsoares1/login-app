package com.estudos.crt.service;

public interface SessionService {
    void setAttribute(String key, Object value);

    Object getAttribute(String key);

    void invalidate();
}
