package tech.habegger.graphql.course.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import tech.habegger.graphql.course.model.Country;

import java.util.ArrayList;
import java.util.List;

public class StaticCountriesDataFetcher implements DataFetcher<List<Country>> {

    private final static List<Country> COUNTRIES = List.of(
            new Country("F", "France", 64300821, 547030, "Paris"),
            new Country("E", "Spain", 47400798, 504750, "Madrid"),
            new Country("CH", "Switzerland", 8670300, 41290, "Bern"),
            new Country("USA", "United States", 331449201, 9372610, "Washington")
    );

    @Override
    public List<Country> get(DataFetchingEnvironment environment) throws Exception {
        return COUNTRIES;
    }
}
