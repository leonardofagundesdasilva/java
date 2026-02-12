package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorting {

    @Test
    public void sortingSteamOfElements() throws IOException {
        List<Person> people = MockData.getPeople();

        System.out.println(
                people.stream()
                    .map(Person::getFirstName)
                        .sorted()
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void sortingSteamOfElementsReverse() throws IOException {
        List<Person> people = MockData.getPeople();

        System.out.println(
                people.stream()
                        .map(Person::getFirstName)
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void sortingSteamOfObjets() throws IOException {
        List<Person> people = MockData.getPeople();

        System.out.println("sortingSteamOfObjets " +
                people.stream()
                        .sorted(Comparator.comparing(Person::getEmail).reversed().thenComparing(Person::getFirstName))
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void topTenMostExpensiveBlueCars() throws IOException {
        List<Car> cars = MockData.getCars();

        System.out.println("topTenMostExpensiveBlueCars " +
                cars.stream()
                        .filter( car -> car.getColor().equalsIgnoreCase("blue"))
                        .sorted(Comparator.comparing(Car::getPrice).reversed())
                        .limit(10)
                        .collect(Collectors.toList())
        );

    }

}
