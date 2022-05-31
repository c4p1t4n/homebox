package school.sptech.server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import school.sptech.server.response.DistResponse;

@FeignClient(value = "calcdist", url ="https://localhost:3001/calc/dist")
public interface CalcDistClient {
    @GetMapping("/{cep1}/{cep2}")
    DistResponse getDist(@PathVariable String cep1,
                         @PathVariable String cep2);
}
