package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.beans.PersonDTO;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformationsMapAndReduce {

    @Test
    void yourFirstTransformationWithMap() throws IOException {
        List<Person> people = MockData.getPeople();

        Function<Person, PersonDTO> personMapFunction = PersonDTO::map;
        List<PersonDTO> peopleDTOs = people.stream()
                //.map(person -> new PersonDTO(person.getId(), person.getFirstName(), person.getAge()))
                .map(personMapFunction)
                .collect(Collectors.toList());
    }

    @Test
    void mapToDoubleAndFindAverageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    @Test
    public void reduce() {
        int[] integers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};

        System.out.println("sum " + Arrays.stream(integers).reduce(0, Integer::sum));
        System.out.println("sub " + Arrays.stream(integers).reduce(0, (a, b) -> a - b));
    }
}

