package com.dstevens.players;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:spring-jpa.xml")
public class TroupeRepositoryTest {

    @Autowired TroupeRepository repository;
    
    @Test
    public void testFoo() {
        System.out.println(repository.toString());
    }
    
}
