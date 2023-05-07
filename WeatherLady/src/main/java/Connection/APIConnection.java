package Connection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class APIConnection {

    private static HttpURLConnection connection;
    private static BufferedReader reader;
    private static String line;
    private static StringBuilder content = new StringBuilder();

    public static void main(String[] args) {

        try {
            String city = "Tallinn";
            String apiAccessKey = "c9acb84362714bfbda769dd0ee6f8185";
            String sourceRequest = "https://api.openweathermap.org/data/2.5/weather?q=Tallinn&units=metric&appid=c9acb84362714bfbda769dd0ee6f8185";
            URL url = new URL(sourceRequest);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int status = connection.getResponseCode();
            System.out.println(status);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) { //clones api info
                content.append(line);
            }
            reader.close();
            System.out.println(content.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String urltest = "jdbc:mysql://localhost/weatherlady";
        String username = "root";
        String password = "1234";

        System.out.println("Connecting to the database...");

        try (Connection conn = DriverManager.getConnection(urltest, username, password);
             Statement stmt = conn.createStatement()
             ) {
            String sql = "CREATE TABLE locations(" +
                    "id binary(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID(), TRUE)), " +
                    " region VARCHAR(255), " +
                    " country VARCHAR(255) NOT NULL, " +
                    " city VARCHAR(255) NOT NULL, " +
                    " longitude DOUBLE NOT NULL, " +
                    " latitude DOUBLE NOT NULL," +
                    " PRIMARY KEY ( id ))";
            //PreparedStatement addDemo = conn.prepareStatement("Insert into LOCATIONS
            // (id, region, country, city, longitude, latitude) values (1, null, Finland, Helsinki, 24.95, 60.17");

            System.out.println();

            stmt.executeUpdate(sql);
            System.out.println("The table has been created into the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Map<String, Object> respMap = jsonToMap(content.toString());
        Map<String, Object> mainMap = (Map<String, Object>) respMap.get("main");
        Map<String, Object> windMap = (Map<String, Object>) respMap.get("wind");

        double windSpeed = (double) windMap.get("speed");
        double windAngle = (double) windMap.get("deg");
        double temperature = ((double) mainMap.get("temp"));
        double airPressure = (double) mainMap.get("pressure");


        System.out.println("Wind Speed: " + windSpeed + "m/s");
        System.out.println("Wind angle: " + windAngle);
        System.out.println("Temperature: " + temperature + "C");
        System.out.println("Air Pressure: " + airPressure);
    }

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
    }
}

