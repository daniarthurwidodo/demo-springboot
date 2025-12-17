package com.example.demo.service;

import com.example.demo.model.Greeting;
import com.example.demo.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {
    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    public Greeting createGreeting(String message) {
        Greeting greeting = new Greeting(null, message);
        return greetingRepository.save(greeting);
    }

    public Greeting updateGreeting(Long id, String message) {
        Greeting greeting = new Greeting(id, message);
        return greetingRepository.save(greeting);
    }

    public void deleteGreeting(Long id) {
        greetingRepository.deleteById(id);
    }

    public String getDefaultGreeting() {
        List<Greeting> greetings = greetingRepository.findAll();
        if (!greetings.isEmpty()) {
            return greetings.get(0).getMessage();
        }
        return "Hello World";
    }
}

