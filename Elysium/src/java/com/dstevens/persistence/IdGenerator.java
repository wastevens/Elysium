package com.dstevens.persistence;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class IdGenerator {

    public String createId() {
        UUID uuid = java.util.UUID.randomUUID();
        return uuid.toString();
    }
    
}
