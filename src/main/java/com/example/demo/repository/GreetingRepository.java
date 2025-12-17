package com.example.demo.repository;

import com.example.demo.model.Greeting;
import java.util.List;
import java.util.Optional;

public interface GreetingRepository {
    List<Greeting> findAll();
    Optional<Greeting> findById(Long id);
    Greeting save(Greeting greeting);
    void deleteById(Long id);
}

