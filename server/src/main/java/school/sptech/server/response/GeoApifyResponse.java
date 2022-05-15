package school.sptech.server.response;

public class GeoApifyResponse {
    private String results;
    private String lon;
    private String lat;

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String result) {
        this.results = result;
    }
}


/*
* {"results":[{"datasource":{"sourcename":"openstreetmap","attribution":"© OpenStreetMap contributors","license":"Open Database License","url":"https://www.openstreetmap.org/copyright"},"name":"Rua Tomás de Sousa Vila Real","street":"Rua Tomás de Sousa Vila Real","suburb":"Ermelino Matarazzo","city":"São Paulo","county":"Região Metropolitana de São Paulo","state":"São Paulo","postcode":"03805-100","country":"Brazil","country_code":"br","lon":-46.4805177,"lat":-23.5041115,"state_code":"SP","formatted":"Rua Tomás de Sousa Vila Real, Ermelino Matarazzo, São Paulo - SP, 03805-100, Brazil","address_line1":"Rua Tomás de Sousa Vila Real","address_line2":"Ermelino Matarazzo, São Paulo - SP, 03805-100, Brazil","result_type":"street","rank":{"importance":0.71,"popularity":6.112645424335111,"confidence":1,"confidence_city_level":1,"confidence_street_level":1,"match_type":"full_match"},"place_id":"5117539f9a813d47c0599a0986730d8137c0f00102f9013d1c330500000000c0020492031d52756120546f6dc3a17320646520536f7573612056696c61205265616c","bbox":{"lon1":-46.4817322,"lat1":-23.5063885,"lon2":-46.4789245,"lat2":-23.5017637}}],"query":{"text":"","parsed":{"street":"Rua Tomas de Sousa Vila Real","postcode":"03805310","city":"Sao Paulo","state":"SP","country":"Brazil","expected_type":"street"}}}
*
*
*
* */