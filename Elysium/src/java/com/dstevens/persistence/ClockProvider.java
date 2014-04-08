package com.dstevens.persistence;

import java.time.Clock;

import org.springframework.stereotype.Service;

@Service
public class ClockProvider {

    public Clock getClock() {
        return Clock.systemUTC();
    }
    
}
