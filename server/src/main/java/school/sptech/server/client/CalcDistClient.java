package school.sptech.server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import school.sptech.server.request.DistRequest;
import school.sptech.server.response.DistResponse;

import org.springframework.web.bind.annotation.RequestBody;

// @FeignClient(value = "calcdist", url = "http://localhost:3001/calc/dist")
@FeignClient(value = "calcdist", url = "https://qu56ty27df2dwnnx6k4ktjrkmm0senfu.lambda-url.us-east-1.on.aws")
public interface CalcDistClient {
    @PostMapping
    ResponseEntity<DistResponse> getDist(@RequestBody DistRequest req);
}
