package com.dstevens.persistence;

import static com.dstevens.testing.Assertions.assertEqualValues;
import static org.mockito.Mockito.when;

import java.time.*;
import java.util.Date;

import org.junit.*;
import org.mockito.*;

public class AuditEventFactoryTest {
    
    private static final String ID = "some ID";
    private Instant NOW = Instant.ofEpochMilli(2357L);
    
    @Mock private IdGenerator idGenerator;
    @Mock private ClockProvider clockProvider;
    @Mock private Clock clock;
    
    private AuditEventFactory factory;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(idGenerator.createId()).thenReturn(ID);
        when(clockProvider.getClock()).thenReturn(clock);
        when(clock.instant()).thenReturn(NOW);
        
        factory = new AuditEventFactory(idGenerator, clockProvider);
    }
    
    @Test
    public void testFactory() {
        Object audited = new Object();
        assertEqualValues(new AuditEvent<>(ID, audited, Date.from(NOW), "audit message"), 
                          factory.auditableFor(audited, "audit message"));
    }
    
}
