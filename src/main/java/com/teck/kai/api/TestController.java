package com.teck.kai.api;

import com.teck.kai.rest.RestClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TestController {

    final RestClient restClient;

    public TestController(RestClient restClient){
        this.restClient= restClient;
    }

    @PostMapping("/in")
    public Map<String, String> request(@RequestBody Map<String, String> body, @RequestParam String param){
        return restClient.postByEntityAndParam("/out?param={param}", new ParameterizedTypeReference<Map<String, String>>() {}, body, param);
    }

    @PostMapping("/out")
    public Map<String, String> response(@RequestBody Map<String, String> body, @RequestParam String param){
        body.put("param", param);
        return body;
    }
}
