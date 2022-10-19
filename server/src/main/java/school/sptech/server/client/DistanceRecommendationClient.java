package school.sptech.server.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import school.sptech.server.request.DistRecommendRequest;
import school.sptech.server.response.UsersDistance;

import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "distrecommend", url = "https://ujj2ngww5ua7tpxvpnnttzissu0ixiet.lambda-url.us-east-1.on.aws")
public interface DistanceRecommendationClient {
    @PostMapping
    ResponseEntity<List<UsersDistance>> getDist(@RequestBody DistRecommendRequest req);
}
