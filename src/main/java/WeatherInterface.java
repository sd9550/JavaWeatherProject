import com.google.gson.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.*;

public class WeatherInterface {

    final private String URL = "https://open-weather13.p.rapidapi.com/city/";
    final private String API_KEY = System.getenv("API_KEY");
    final private String API_HOST = "open-weather13.p.rapidapi.com";
    private String city_name;
    private double wind_speed;
    private String description;
    private double temperature;

    public WeatherInterface(String loc) throws Exception {
        // get weather data for entered location
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je;
        HttpResponse <JsonNode> response = Unirest.get(URL + loc)
                .header("x-rapidapi-host", API_HOST)
                .header("x-rapidapi-key", API_KEY)
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));

        je = jp.parse(response.getBody().toString());
        String prettyJsonString = gson.toJson(je);

        // extract data from json object
        JSONObject obj = new JSONObject(prettyJsonString);
        double wind = obj.getJSONObject("wind").getDouble("speed");
        double temp = obj.getJSONObject("main").getDouble("temp");
        JSONObject desc_obj = obj.getJSONArray("weather").getJSONObject(0);
        String desc = desc_obj.getString("description");
        String city = obj.getString("name");

        // setup get/set methods
        setCity(city);
        setWind(wind);
        setDescription(desc);
        setTemperature(temp);

    }

    private void setCity(String n) {
        city_name = n;
    }

    public String getCity() {
        return city_name;
    }

    private void setWind(double w) {
        wind_speed = w;
    }

    public double getWind() {
        return wind_speed;
    }

    private void setDescription(String d) {
        description = d;
    }

    public String getDescription() {
        return description;
    }

    private void setTemperature(double t) {
        temperature = t;
    }

    public double getTemperature() {
        return temperature;
    }

}
