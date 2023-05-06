package Connection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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


        Map<String, Object> respMap = jsonToMap(content.toString());
        Map<String, Object> mainMap = (Map<String, Object>) respMap.get("main");
        Map<String, Object> windMap = (Map<String, Object>) respMap.get("wind");

        double windSpeed = (double) windMap.get("speed");
        double windTemp = (double) windMap.get("deg");
        double temperature = ((double) mainMap.get("temp"));
        double airPressure = (double) mainMap.get("pressure");


        System.out.println("Wind Speed: " + windSpeed);
        System.out.println("Wind temperature: " + windTemp);
        System.out.println("Temperature: " + temperature + " C");
        System.out.println("Air Pressure: " + airPressure);
    }
public static Map<String, Object> jsonToMap(String str) {
        Map<String,Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String,Object>>() {
        }.getType());
        return map;
        }
}

