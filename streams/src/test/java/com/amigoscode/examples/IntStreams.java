package com.amigoscode.examples;


import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreams {

    @Test
    public void range() throws Exception {
        System.out.println("exclusive range " + IntStream.range(0, 5).sum());

        System.out.println("inclusive range " + IntStream.rangeClosed(0, 5).sum());
    }

    // Loop through people using IntStream
    @Test
    public void rangeIteratingLists() throws Exception {
        List<Person> people = MockData.getPeople();

        IntStream.range(0, people.size())
                .forEach(index -> System.out.println(people.get(index)));
    }

    @Test
    public void intStreamIterate()  {
        IntStream.iterate(0, value -> value +2)
                .limit(11)
                .forEach(System.out::println);
    }
}
