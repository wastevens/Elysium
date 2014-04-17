package com.dstevens.persistence;

import static com.dstevens.testing.Assertions.assertEqualValues;
import static org.mockito.Mockito.when;

import java.time.*;
import java.util.Date;

import org.junit.*;
import org.mockito.*;

import com.dstevens.persistence.auditing.*;
import com.dstevens.suppliers.*;

public class AuditEventFactoryTest {
    
    private static final String ID = "some ID";
    private Instant NOW = Instant.ofEpochMilli(2357L);
    
    @Mock private IdSupplier idSupplier;
    @Mock private ClockSupplier clockSupplier;
    @Mock private Clock clock;
    
    private AuditEventFactory factory;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(idSupplier.get()).thenReturn(ID);
        when(clockSupplier.get()).thenReturn(clock);
        when(clock.instant()).thenReturn(NOW);
        
        factory = new AuditEventFactory(idSupplier, clockSupplier);
    }
    
    @Test
    public void testFactory() {
        Object audited = new Object();
        assertEqualValues(new AuditEvent<>(ID, audited, Date.from(NOW), "audit message"), 
                          factory.auditableFor(audited, "audit message"));
    }
    
}
