package com.jesstest11999.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.jesstest11999.models.Person;
import com.jesstest11999.services.PersonService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    public static final String PERSONS_ENDPOINT = "/persons";
    public static final String PERSON_REQUEST_BODY = """
            {
                "name": "%s"
            }
            """;

    private final UUID personId = UUID.randomUUID();
    private final String name = "Alfredo";
    private final String message = "$.message";
    private final String error = "$.error";
    private final String badRequest = "Bad Request";
    private final String responseStatus = "$.status";

    private final String stringEmpty = "";

    private final Person person =
            new Person(personId, name);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    public void shouldCreatePerson() throws Exception {
        String createPersonRequestJson = String.format(PERSON_REQUEST_BODY, name);
        when(personService.createPerson(eq(name))).thenReturn(person);

        mockMvc.perform(
                            post(PERSONS_ENDPOINT)
                                .content(createPersonRequestJson)
                                .contentType(MediaType.APPLICATION_JSON)

                        ).andExpect(status().isCreated())
                        .andExpect(header().string("location", "person/" + personId));

        verify(personService).createPerson(name);
    }

    @Test
    public void shouldDeletePersonById() throws Exception {

        doNothing().when(personService).deletePerson(personId);

        mockMvc.perform(
                        delete("/persons/delete/" + personId)
                ).andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllPersons() throws Exception {
        UUID personId1 = UUID.randomUUID();
        String name1 = "Person 1";

        UUID personId2 = UUID.randomUUID();
        String name2 = "Person 2";

        Person expectedPerson1 = new Person(personId1, name1);
        Person expectedPerson2 = new Person(personId2, name2);
        List<Person> expectedListOfPersons = List.of(expectedPerson1, expectedPerson2);
        when(personService.getAllPersons()).thenReturn(expectedListOfPersons);

        mockMvc.perform(
                        get(PERSONS_ENDPOINT)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.persons", hasSize(2)))
                .andExpect(jsonPath("$.persons[0].name").value("Person 1"))
                .andExpect(jsonPath("$.persons[1].name").value("Person 2"));
    }

    @Test
    public void shouldGetAllPersonsWhenNoneExist() throws Exception {
        when(personService.getAllPersons()).thenReturn(List.of());

        mockMvc.perform(
                        get(PERSONS_ENDPOINT)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.persons", hasSize(0)));
    }

}
