package tech.habegger.graphql.course.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import tech.habegger.graphql.course.model.City;
import tech.habegger.graphql.course.model.Province;

import java.util.Map;

public class StaticProvinceDataFetcher implements DataFetcher<Province> {
    private static final Map<String, Province> PROVINCES = Map.of(
            "Île-de-France", new Province("Île-de-France", 12082144, "Paris"),
            "Madrid", new Province("Madrid", 6726640, "Madrid"),
            "Bern", new Province("Bern", 1043132, "Bern"),
            "District of Columbia", new Province("District of Columbia", 689545, "Washington")
    );

    @Override
    public Province get(DataFetchingEnvironment environment) throws Exception {
        City city = environment.getSource();

        return PROVINCES.get(city.province());
    }
}
