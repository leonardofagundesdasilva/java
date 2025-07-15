package tech.habegger.graphql.course;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import graphql.ExecutionResult;
import graphql.validation.ValidationError;
import org.eclipse.jetty.util.IO;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
class GraphQLRuntimeTest {
    private final GraphQLRuntime runtime;

    GraphQLRuntimeTest() throws IOException {
        runtime = new GraphQLRuntime();
    }

    @Test
    public void validQuery() {
        //Given
        var query = """
                {
                    countries {
                        name
                        population
                        capital {
                            name
                            population
                            province {
                                name
                                population
                                capital {
                                    name
                                }
                            }
                        }
                    }
                }
                """;

        //When
        ExecutionResult result = runtime.execute(query);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isEmpty();

        var output = result.toSpecification();
        assertThat(output).isNotNull();
        var data = (Map<String, Object>) output.get("data");
        assertThat(data).isNotNull();
        assertThat(data).containsKey("countries").extracting("countries").isInstanceOf(List.class);
        var countries = (List<Map<String, Object>>) data.get("countries");
        assertThat(countries).hasSize(4);

        assertThat(countries).contains(Map.of(
                "name", "Switzerland",
                "population", 8670300,
                "capital", Map.of(
                        "name", "Bern",
                        "population", 134794,
                        "province", Map.of(
                                "name", "Bern",
                                "population", 1043132,
                                "capital", Map.of(
                                        "name", "Bern"
                                )
                        )
                )
        ));
    }

    @Test
    void invalidQuery() {
        //Given
        var query = """
                {
                    countries {
                        latitude #does not exist in schema
                        name
                        population
                        capital {
                            name
                            population
                        }
                    }
                }
                """;

        //When
        ExecutionResult result = runtime.execute(query);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotEmpty();
        assertThat(result.getErrors().get(0)).isInstanceOf(ValidationError.class);
    }
}