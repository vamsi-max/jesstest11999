package com.jesstest11999.repositories;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import com.jesstest11999.models.Person;
import com.jesstest11999.models.daos.PersonDao;
import com.jesstest11999.models.mappers.PersonMapper;

@Repository
public class PersonRepository {

    public static final String SELECT_FROM_PERSON_WHERE_UUID_UUID = "SELECT * FROM person WHERE uuid=:uuid";
    private final Jdbi jdbi;

    public PersonRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Person createPerson(UUID uuid,
                             String name) {

        PersonDao personDao = jdbi.withHandle(handle -> {
            handle
                    .createUpdate(
                            "INSERT INTO person(uuid, name)"
                                    + " VALUES (:uuid, :name)")
                    .bind("uuid", uuid)
                    .bind("name", name)
                    .execute();

            return handle
                    .createQuery(SELECT_FROM_PERSON_WHERE_UUID_UUID)
                    .bind("uuid", uuid)
                    .mapToBean(PersonDao.class)
                    .first();
        });

        return PersonMapper.toPerson(personDao);
    }

    public List<Person> getAllPersons() {
        List<PersonDao> personDaos = jdbi.withHandle(handle -> handle
                .createQuery("SELECT * FROM person")
                .mapToBean(PersonDao.class)
                .list());
        return personDaos.stream()
                .map(PersonMapper::toPerson)
                .collect(Collectors.toList());
    }

    public void deletePerson(UUID uuid) {
        jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM person WHERE uuid=:uuid")
                .bind("uuid", uuid)
                .execute()

        );

    }

}
