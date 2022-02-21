package com.jb.SpringProject.CLR;

import com.jb.SpringProject.beans.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
public class TestRestTamplate implements CommandLineRunner {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        String allcatUrl = "http://localhost:8082/littleKitty/cat/all";
        ResponseEntity<Cat[]> catResult = restTemplate.getForEntity(allcatUrl,Cat[].class);
        List<Cat> allCats = Arrays.asList(catResult.getBody());
        System.out.println(catResult);
        System.out.println("===========================================================");
        System.out.println(allCats.get(1));
    }
}
