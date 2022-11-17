package school.sptech.server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import school.sptech.server.request.UserIdRequest;

import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "distrecommendPut", url = "https://qu56ty27df2dwnnx6k4ktjrkmm0senfu.lambda-url.us-east-1.on.aws")
public interface DistanceRecommendationPutClient {
    @PostMapping
    ResponseEntity<Void> putDist(@RequestBody UserIdRequest req);
}
