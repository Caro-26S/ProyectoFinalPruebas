package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/success")
    public ResponseEntity<String> getJoke() {
        String url = "https://api.chucknorris.io/jokes/random";
        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fail")
    public ResponseEntity<String> getInvalid() {
        try {
            String badUrl = "https://api.chucknorris.io/jokes/thisdoesnotexist";
            String response = restTemplate.getForObject(badUrl, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al consumir la API: " + e.getMessage());
        }
    }
}
