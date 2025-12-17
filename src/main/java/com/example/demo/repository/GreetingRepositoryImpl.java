package com.example.demo.repository;

import com.example.demo.model.Greeting;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class GreetingRepositoryImpl implements GreetingRepository {
    private final Map<Long, Greeting> greetings = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public GreetingRepositoryImpl() {
        // Initialize with some default greetings
        save(new Greeting(null, "Hello World"));
        save(new Greeting(null, "Hello Spring Boot"));
        save(new Greeting(null, "Hello Repository Pattern"));
    }

    @Override
    public List<Greeting> findAll() {
        return new ArrayList<>(greetings.values());
    }

    @Override
    public Optional<Greeting> findById(Long id) {
        return Optional.ofNullable(greetings.get(id));
    }

    @Override
    public Greeting save(Greeting greeting) {
        if (greeting.getId() == null) {
            greeting.setId(idGenerator.getAndIncrement());
        }
        greetings.put(greeting.getId(), greeting);
        return greeting;
    }

    @Override
    public void deleteById(Long id) {
        greetings.remove(id);
    }
}

