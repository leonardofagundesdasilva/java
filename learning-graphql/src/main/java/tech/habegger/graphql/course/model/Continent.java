package tech.habegger.graphql.course.model;

public enum Continent {
    Europe,
    Asia,
    Australia("Australia/Oceania"),
    Africa,
    NorthAmerica("North America"),
    SouthAmerica("South America");

    private final String dbName;

    Continent(String dnName) {
        this.dbName = dnName;
    }

    Continent() {
        this.dbName = this.name();
    }

    public String getDbName() {
        return dbName;
    }
}
