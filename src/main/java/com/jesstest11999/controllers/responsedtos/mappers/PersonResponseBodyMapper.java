package com.jesstest11999.controllers.responsedtos.mappers;

import com.jesstest11999.controllers.responsedtos.PersonResponseBody;
import com.jesstest11999.models.Person;

public class PersonResponseBodyMapper {
    private PersonResponseBodyMapper(){}
    
    public static PersonResponseBody fromPerson(Person person) {
        return new PersonResponseBody(
                person.getUuid().toString(),
                person.getName()
        );
    }
}
