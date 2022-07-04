package dataStore.db.memory;

import com.google.common.collect.ImmutableList;
import dataStore.db.DataRepository;
import dataStore.model.Person;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MemoryDb implements DataRepository {
    private final Set<Person> people = new HashSet<>();

    volatile long lastPersonId = 0L;

    public MemoryDb() {
        setupTestData();
    }

    //<editor-fold desc="Persons">

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableList<Person> allPersons() {
        return ImmutableList.copyOf(people);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Person> findPerson(String email) {
        return people.stream()
                .filter(Person -> Person.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person addPerson(Person person) {
        Optional<Person> alreadyExists = findPerson(person.getEmail());
        if (alreadyExists.isPresent()) {
            return alreadyExists.get();
        }
        person.setId(nextPersonId());
        people.add(person);
        return person;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePerson(Person person) {
        people.remove(person);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePerson(Person updatedPerson) {
        Optional<Person> PersonOpt = people.stream().filter(Person -> Person.getEmail().equalsIgnoreCase(updatedPerson.getEmail())).findFirst();
        if (PersonOpt.isPresent()) {
            people.remove(PersonOpt.get());
            people.add(updatedPerson);
        }
    }


    /**
     * Answer the next available ID for a Person.
     * <p>
     * Incrementing a {@code long} value has to be synchronized because it is not an atomic
     * operation. See the Java Language Specification (§17.7 in Java SE17 Ed.) for details.
     *
     * @return The new ID.
     */
    private long nextPersonId() {
        synchronized (this) {
            return ++lastPersonId;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Test Data">
    private void setupTestData() {
        Person herman = addPerson(new Person("herman@wethinkcode.co.za"));
        Person mike = addPerson(new Person("mike@wethinkcode.co.za"));
        addPerson(new Person("sett@wethinkcode.co.za"));

    }
    //</editor-fold>
}