package com.dstevens.players;

import static com.dstevens.collections.Lists.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.structures.messages.Message;
import com.dstevens.utilities.Validator;

@Service 
public class TroupeValidator extends Validator<Troupe> {
    
    private final TroupeDao dao;

    @Autowired
    public TroupeValidator(TroupeDao dao) {
        this.dao = dao;
    }
    
    @Override
    public List<Message> validate(Troupe e) {
        List<Message> validationMessages = list();
        if (!isNameAvailable(e)) {
            validationMessages.add(nameNotAvailable(e.getName()));
        }
        return validationMessages;
    }

    private Message nameNotAvailable(String name) {
        return new Message("Troupe.validation.name.in.use", name);
    }

    private boolean isNameAvailable(Troupe e) {
        return dao.countOfUndeletedNamed(e.getName()) == 0;
    }

}
