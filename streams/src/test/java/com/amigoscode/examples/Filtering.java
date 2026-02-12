package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filtering {

    @Test
    public void filter() throws Exception {
        List<Car> cars = MockData.getCars();

        Predicate<Car> yellowPredicate = car -> car.getColor().equals("Yellow");

        List<Car> yellowCarsLessThan20k = cars.stream()
                .filter(c -> c.getPrice() < 20_000.00)
                .filter(yellowPredicate)
                .collect(Collectors.toList());

        System.out.println(yellowCarsLessThan20k);
    }

    @Test
    public void dropWhile() throws Exception {
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        System.out.println("using dropWhile");

        System.out.println(Stream.of(2,4,6,7,8,10,12).dropWhile(n -> n % 2 == 0).collect(Collectors.toList()));
    }

    @Test
    public void takeWhile() throws Exception {
        // using filter
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();
        System.out.println("using take while");

        System.out.println(Stream.of(2,4,6,7,8,10,12).takeWhile(n -> n % 2 == 0).collect(Collectors.toList()));
    }

    @Test
    public void findFirst() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println("findFirst " + Arrays.stream(numbers)
                .filter(n -> n == 50)
                .findFirst()
                .orElse(-1));
    }

    @Test
    public void findAny() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10};

        System.out.println("findAny " + Arrays.stream(numbers)
                .filter(n -> n == 9)
                .findAny()
                .orElse(-1));
    }

    @Test
    public void allMatch() throws Exception {
        int[] even = {2, 4, 6, 8, 10};

        System.out.println("allMatch " + Arrays.stream(even).allMatch(n -> n % 2 == 0));
    }

    @Test
    public void anyMatch() throws Exception {
        int[] evenAndOneOdd = {2, 4, 6, 8, 10, 11};

        System.out.println("anyMatch " + Arrays.stream(evenAndOneOdd).anyMatch(n -> !(n % 2 == 0)));
    }

}



