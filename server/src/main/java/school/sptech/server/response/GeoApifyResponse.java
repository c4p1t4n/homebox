package school.sptech.server.response;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;


public class GeoApifyResponse {
    private String longitude;
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void getLonLat(String logradouro,String cep, String localidade, String uf){
            try {
                String endpoint = String.format("https://api.geoapify.com/v1/geocode/search?street=%s&postcode=%s&city=%s&state=%s&country=Brazil&limit=1&format=json&apiKey=03197b59532d488d8529d2c3d663c4d5",
                        logradouro, cep, localidade,uf).replace(" ","%20");
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endpoint))
                        .header("Content-Type", "application/json")
                        .build();

                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString());

                JSONObject object = new JSONObject(response.body()); //jsonString = String from the file
                JSONArray array = object.getJSONArray("results");
                Iterator<Object> iterator = array.iterator();
                while(iterator.hasNext()){
                    JSONObject jsonObject = (JSONObject) iterator.next();
                    this.longitude = String.valueOf(jsonObject.get("lon"));
                    this.latitude = String.valueOf(jsonObject.get("lat"));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
              }
        }
    }
