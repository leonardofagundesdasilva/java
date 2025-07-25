package tech.habegger.graphql.course.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import tech.habegger.graphql.course.db.Mapping;
import tech.habegger.graphql.course.model.City;
import tech.habegger.graphql.course.model.Province;

import java.sql.Connection;
import java.util.Map;

public class DBProvinceDataFetcher implements DataFetcher<Province> {
    private final Connection dbConnection;

    public DBProvinceDataFetcher(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Province get(DataFetchingEnvironment environment) throws Exception {
        City city = environment.getSource();

        var sql = """
                SELECT *
                FROM province
                WHERE name = ?
                """;

        var statement = dbConnection.prepareStatement(sql);
        statement.setString(1, city.province());
        var results = statement.executeQuery();

        if(results.next()) {
            return Mapping.provinceOf(results);
        }

        return null;
    }
}
