package com.amigoscode.examples;


import com.amigoscode.beans.Car;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class StatisticsWithStreams {

    @Test
    public void count() throws Exception {
        List<Car> cars = MockData.getCars();

        System.out.println("count " +
                cars.stream()
                        .filter(car -> car.getMake().equalsIgnoreCase("Ford"))
                        .filter(car -> car.getYear() > 2010)
                        .count()
        );
    }

    @Test
    public void min() throws Exception {
        List<Car> cars = MockData.getCars();

        System.out.println("min " +
                cars.stream()
                        .mapToDouble(Car::getPrice)
                        .min()
                        .orElse(0)
        );
    }

    @Test
    public void max() throws Exception {
        List<Car> cars = MockData.getCars();

        System.out.println("max " +
                cars.stream()
                        .mapToDouble(Car::getPrice)
                        .max()
                        .orElse(0)
        );
    }


    @Test
    public void average() throws Exception {
        List<Car> cars = MockData.getCars();

        System.out.println("average " +
                cars.stream()
                        .mapToDouble(Car::getPrice)
                        .average()
                        .orElse(0)
        );
    }

    @Test
    public void sum() throws Exception {
        List<Car> cars = MockData.getCars();

        System.out.println("sum " +
                BigDecimal.valueOf(cars.stream()
                        .mapToDouble(Car::getPrice)
                        .sum())
        );
    }

    @Test
    public void statistics() throws Exception {
        List<Car> cars = MockData.getCars();

        DoubleSummaryStatistics statistics = cars.stream().mapToDouble(Car::getPrice).summaryStatistics();

        System.out.println(statistics.getCount());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getAverage());
        System.out.println(BigDecimal.valueOf(statistics.getSum()));
    }

}