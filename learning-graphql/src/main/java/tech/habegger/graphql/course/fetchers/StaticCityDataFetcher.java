package tech.habegger.graphql.course.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import tech.habegger.graphql.course.model.City;
import tech.habegger.graphql.course.model.Country;
import tech.habegger.graphql.course.model.GeoCoord;

import java.util.Map;
import java.util.function.Function;

public class StaticCityDataFetcher<T> implements DataFetcher<City> {
    private final static Map<String, City> CITIES = Map.of(
            "Paris", new City("Paris", 2249975, new GeoCoord(48.86,2.35), 28, "Île-de-France"),
            "Madrid", new City("Madrid", 3277451, new GeoCoord(40.38,	-3.72), 667, "Madrid"),
            "Bern", new City("Bern", 134794, new GeoCoord(46.95,	7.45), 542, "Bern"),
            "Washington", new City("Washington", 689545, new GeoCoord(38.5,	-77), 7, "District of Columbia")
    );

    private final Function<T, String> cityNameExtractor;

    public StaticCityDataFetcher(Function<T, String> cityNameExtractor) {
        this.cityNameExtractor = cityNameExtractor;
    }

    @Override
    public City get(DataFetchingEnvironment environment) throws Exception {
        //Country sourceCountry = environment.getSource();
        //var capitalName = sourceCountry.capital();

        var capitalName = cityNameExtractor.apply(environment.getSource());

        return CITIES.get(capitalName);
    }
}
