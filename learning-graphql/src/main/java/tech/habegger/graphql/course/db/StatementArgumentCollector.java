package tech.habegger.graphql.course.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatementArgumentCollector {
    private final List<Object> arguments = new ArrayList<>();

    public void addString(String string) {
        arguments.add(string);
    }

    public void addInt(int i) {
        arguments.add(i);
    }

    public void applyTo(PreparedStatement statement) throws SQLException {
        for (int i = 0; i < arguments.size(); i++) {
            if(arguments.get(i) instanceof String stringValue) {
                statement.setString(+1, stringValue);
            } else if(arguments.get(i) instanceof Integer intValue) {
                statement.setInt(i+1, intValue);
            } else {
                throw new RuntimeException("Invalid internal state.");
            }
        }
    }
}
