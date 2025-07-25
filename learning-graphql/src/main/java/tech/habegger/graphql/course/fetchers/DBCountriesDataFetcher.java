package tech.habegger.graphql.course.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import tech.habegger.graphql.course.db.Mapping;
import tech.habegger.graphql.course.db.StatementArgumentCollector;
import tech.habegger.graphql.course.model.Continent;
import tech.habegger.graphql.course.model.Country;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBCountriesDataFetcher  implements DataFetcher<List<Country>> {
    private final Connection dbConnection;
    public DBCountriesDataFetcher(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }
    @Override
    public List<Country> get(DataFetchingEnvironment environment) throws Exception {
        Map<String, Object> criteria = environment.getArgument("criteria");

        PreparedStatement statement = queryOfCriteria(criteria);

        var results = statement.executeQuery();
        var mappedResults = new ArrayList<Country>();
        while (results.next()) {
            Country country = Mapping.countryOf(results);
            mappedResults.add(country);
        }

        return mappedResults;
    }

    private PreparedStatement queryOfCriteria(Map<String, Object> criteria) throws SQLException {
        StringWriter writer = new StringWriter();
        writer.append("""
                SELECT *
                FROM country c, encompasses e
                WHERE c.code = e.country    
                """);

        Continent continent = (Continent) criteria.get("continent");
        StatementArgumentCollector collector = new StatementArgumentCollector();
        if(continent != null) {
            writer.append(" AND e.continent = ?");
            collector.addString(continent.getDbName());
        }

        Map<String, Integer> populationRange = (Map<String, Integer>) criteria.get("population");
        if(populationRange != null) {
            if(populationRange.containsKey("above")) {
                writer.append(" AND c.population >= ?");
                collector.addInt(populationRange.get("above"));
            }

            if(populationRange.containsKey("below")) {
                writer.append(" AND c.population <= ?");
                collector.addInt(populationRange.get("below"));
            }
        }

        PreparedStatement statement = dbConnection.prepareStatement(writer.toString());
        collector.applyTo(statement);

        return statement;
    }
}
