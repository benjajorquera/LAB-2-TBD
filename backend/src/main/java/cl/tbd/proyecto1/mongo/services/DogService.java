package cl.tbd.proyecto1.mongo.services;

import cl.tbd.proyecto1.mongo.models.Dog;
import cl.tbd.proyecto1.mongo.repositories.DogRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogService {

    private final DogRepository dogRepository;
    DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @GetMapping("/dogs")
    public List<Dog> getDogs() {
        return dogRepository.getDogs();
    }
    
}
