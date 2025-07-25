package tech.habegger.graphql.course.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import tech.habegger.graphql.course.db.Mapping;
import tech.habegger.graphql.course.model.City;
import tech.habegger.graphql.course.model.Country;
import tech.habegger.graphql.course.model.GeoCoord;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

public class DBCityDataFetcher<T> implements DataFetcher<City> {


    private final Function<T, String> cityNameExtractor;
    private final Connection dbConnection;

    public DBCityDataFetcher(Connection dbConnection, Function<T, String> cityNameExtractor) {
        this.dbConnection = dbConnection;
        this.cityNameExtractor = cityNameExtractor;
    }

    @Override
    public City get(DataFetchingEnvironment environment) throws Exception {
        var capitalName = cityNameExtractor.apply(environment.getSource());

        var sql = """
                SELECT *
                FROM city
                WHERE name = ?
                """;

        var statement = dbConnection.prepareStatement(sql);
        statement.setString(1, capitalName);
        var results = statement.executeQuery();

        if(results.next()) {
            return Mapping.cityOf(results);
        }

        return null;
    }
}
