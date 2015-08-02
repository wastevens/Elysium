package com.dstevens.event;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRepository {

    private EventDao dao;

    @Autowired
    public EventRepository(EventDao dao) {
        this.dao = dao;
    }

    public Event findWithId(Integer id) {
    	Event event = dao.findOne(id);
    	if(event == null) {
    		throw new UnknownEventException("Could not find event with id " + id);
    	}
		return event;
    }
    
    public Optional<Event> findOptionalWithId(Integer id) {
    	return Optional.ofNullable(dao.findOne(id));
    }
    
    public Iterable<Event> findAll() {
    	return dao.findAll();
    }
}
