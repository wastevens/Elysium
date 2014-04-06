package com.dstevens.players;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import com.dstevens.configuration.ApplicationConfiguration;

public class TroupeRepositoryTest {

    private static TroupeRepository repository;

    @BeforeClass
    public static void setUp() {
        repository = ApplicationConfiguration.appConfig().getBean(TroupeRepository.class);
    }
    
    @Test
    public void testTroupeRepository() {
        Troupe troupeToSave = Troupe.newTroupe("some name", Setting.ANARCH);
        Troupe savedTroupe = repository.save(troupeToSave);
        try {
            assertEquals(troupeToSave, savedTroupe);
        } finally {
            repository.delete(savedTroupe);
        }
    }
    
}
