package tech.habegger.graphql.course.db;

import tech.habegger.graphql.course.model.City;
import tech.habegger.graphql.course.model.Country;
import tech.habegger.graphql.course.model.GeoCoord;
import tech.habegger.graphql.course.model.Province;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapping {
    public static Country countryOf(ResultSet result) throws SQLException {
        return new Country(
                result.getString("code"),
                result.getString("name"),
                result.getInt("population"),
                result.getInt("area"),
                result.getString("capital")
        );
    }

    public static City cityOf(ResultSet result) throws SQLException {
        GeoCoord geoCoord = null;
        if(result.getObject("latitude") != null
        && result.getObject("longitude") != null) {
            var latitude = result.getDouble("latitude");
            var longitude = result.getDouble("longitude");

            geoCoord = new GeoCoord(latitude, longitude);
        }

        return new City(
                result.getString("name"),
                result.getInt("population"),
                geoCoord,
                result.getInt("elevation"),
                result.getString("province")
        );
    }

    public static Province provinceOf(ResultSet result) throws SQLException {
        return new Province(
                result.getString("name"),
                result.getInt("population"),
                result.getString("capital")
        );
    }
}
