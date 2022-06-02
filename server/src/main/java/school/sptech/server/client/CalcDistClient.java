package school.sptech.server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "calcdist", url = "http://localhost:3001/calc/dist")
public interface CalcDistClient {
    @GetMapping("/{cep1}/{cep2}")
    ResponseEntity<Double> getDist(@PathVariable String cep1,
            @PathVariable String cep2);
}
