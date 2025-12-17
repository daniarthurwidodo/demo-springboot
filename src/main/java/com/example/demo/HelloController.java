package com.example.demo;

import com.example.demo.model.Greeting;
import com.example.demo.service.GreetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {

	private final GreetingService greetingService;

	public HelloController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@GetMapping
	public String hello() {
		return greetingService.getDefaultGreeting();
	}

	@GetMapping("/greetings")
	public List<Greeting> getAllGreetings() {
		return greetingService.getAllGreetings();
	}

	@GetMapping("/greetings/{id}")
	public ResponseEntity<Greeting> getGreetingById(@PathVariable Long id) {
		return greetingService.getGreetingById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/greetings")
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
		Greeting created = greetingService.createGreeting(greeting.getMessage());
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@PutMapping("/greetings/{id}")
	public ResponseEntity<Greeting> updateGreeting(@PathVariable Long id, @RequestBody Greeting greeting) {
		Greeting updated = greetingService.updateGreeting(id, greeting.getMessage());
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/greetings/{id}")
	public ResponseEntity<Void> deleteGreeting(@PathVariable Long id) {
		greetingService.deleteGreeting(id);
		return ResponseEntity.noContent().build();
	}

}

