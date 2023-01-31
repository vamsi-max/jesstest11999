package com.jesstest11999.services;

import com.jesstest11999.models.Person;
import com.jesstest11999.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {


    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    private final UUID uuid = UUID.randomUUID();
    private final String name = "Alfredo";


    private final Person expectedPerson = new Person(uuid, name);

    @Test
    public void shouldCreatePersonWithGeneratedUuid() {
        when(personRepository.createPerson(any(UUID.class), eq(name)))
                .thenReturn(expectedPerson);

        Person actualPerson = personService.createPerson(name);

        assertEquals(expectedPerson.getUuid(), actualPerson.getUuid());
        assertEquals(expectedPerson.getName(), actualPerson.getName());
    }


    @Test
    public void shouldGetAllPersons() {
        when(personRepository.getAllPersons()).thenReturn(List.of(expectedPerson));

        List<Person> allPersons = personService.getAllPersons();
        assertEquals(1, allPersons.size());
        assertEquals(uuid, allPersons.get(0).getUuid());
        assertEquals(name, allPersons.get(0).getName());
    }

    @Test
    public void shouldGetAllPersonsAsEmptyListWhenThereAreNone() {
        when(personRepository.getAllPersons()).thenReturn(List.of());

        List<Person> allPersons = personService.getAllPersons();
        assertTrue(allPersons.isEmpty());
    }
    @Test
    public void shouldDeletePerson() {
        doNothing().when(personRepository).deletePerson(uuid);
        personService.deletePerson(uuid);
        verify(personRepository, times(1)).deletePerson(uuid);
    }
}