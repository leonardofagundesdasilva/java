package tech.habegger.graphql.course;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import tech.habegger.graphql.course.fetchers.*;
import tech.habegger.graphql.course.model.Continent;
import tech.habegger.graphql.course.model.Country;
import tech.habegger.graphql.course.model.Province;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Map;

public class GraphQLRuntime {
    private final GraphQL graphQL;

    public GraphQLRuntime(Connection dbConnection) throws IOException {
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        TypeDefinitionRegistry typeRegistry = buildRegistry();
        RuntimeWiring wiring = buildWiring(dbConnection);
        GraphQLSchema schema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);

        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring(Connection dbConnection) {
        return RuntimeWiring.newRuntimeWiring()
                //Wire Enums
                .type("Continent", builder -> builder.enumValues(new NaturalEnumValuesProvider<>(Continent.class)))

                //Wire Data Fecthers
                //.type("Query", builder -> builder.dataFetcher("countries", new StaticCountriesDataFetcher()))
                //.type("Country", builder -> builder.dataFetcher("capital", new StaticCityDataFetcher<>(Country::capital)))
                //.type("City", builder -> builder.dataFetcher("province", new StaticProvinceDataFetcher()))
                //.type("Province", builder -> builder.dataFetcher("capital", new StaticCityDataFetcher<>(Province::capital)))
                .type("Query", builder -> builder.dataFetcher("countries", new DBCountriesDataFetcher(dbConnection)))
                .type("Country", builder -> builder.dataFetcher("capital", new DBCityDataFetcher<>(dbConnection, Country::capital)))
                .type("City", builder -> builder.dataFetcher("province", new DBProvinceDataFetcher(dbConnection)))
                .type("Province", builder -> builder.dataFetcher("capital", new DBCityDataFetcher<>(dbConnection, Province::capital)))
                .build();
    }

    private TypeDefinitionRegistry buildRegistry() throws IOException {
        try(InputStream is = GraphQLRuntime.class.getResourceAsStream("/schema.graphqls")) {
            assert is != null;
            Reader schemaReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            return new SchemaParser().parse(schemaReader);
        }
    }

    public ExecutionResult execute(String query) {
        return execute(query, null, null);
    }

    public ExecutionResult execute(String query, Map<String, Object> variables, String operation) {
        ExecutionInput.Builder builder = ExecutionInput.newExecutionInput()
                .query(query);

        if( variables != null ) {
            builder.variables(variables);
        }

        if(operation != null) {
            builder.operationName(operation);
        }

        return graphQL.execute(builder.build());
    }
}
