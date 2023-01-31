package com.jesstest11999.models.mappers;

import com.jesstest11999.models.Person;
import com.jesstest11999.models.daos.PersonDao;

public class PersonMapper {
    private PersonMapper(){}

    public static Person toPerson(PersonDao eventDao) {
        return new Person(
                eventDao.getUuid(),
                eventDao.getName()
        );
    }
    
}
