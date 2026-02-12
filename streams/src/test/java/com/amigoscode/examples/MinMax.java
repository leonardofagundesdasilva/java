package com.amigoscode.examples;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinMax {

    @Test
    public void min() {
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);

        System.out.println(numbers.stream().min(Comparator.naturalOrder()).get());

        System.out.println(Arrays.stream(new int[]{3,6,2,7}).min().getAsInt());
    }

    @Test
    public void max() {
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
        System.out.println(numbers.stream().max(Comparator.naturalOrder()).get());

        System.out.println(Arrays.stream(new int[]{3,5,6,1,2}).max().getAsInt());
    }
}
