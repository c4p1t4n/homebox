package school.sptech.server.rest;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import school.sptech.server.response.GeoApifyResponse;
import school.sptech.server.response.ViaCepResponse;


@FeignClient(value = "geoapi", url = "https://api.geoapify.com/v1/geocode/")
public interface ClienteGeoApify {
    @GetMapping("search?street={logradouro}&postcode={cep}&city={localidade}&state={uf}&" +
                "country=Brazil&limit=1&format=json&apiKey=03197b59532d488d8529d2c3d663c4d5")

    GeoApifyResponse getLonLat(@PathVariable String logradouro,
                               @PathVariable String cep,
                               @PathVariable String localidade,
                               @PathVariable String uf);
}