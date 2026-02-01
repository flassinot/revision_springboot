package com.example.taskflow;


import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.net.http.*;
import java.net.URI;
import java.util.*;
import java.util.concurrent.*;

public class JavaFeaturesTest {

    // --- Record ---
    record User(int id, String name) {}

    // --- Sealed classes ---
    sealed interface Shape permits Circle, Square {}
    final class Circle implements Shape { double r; Circle(double r){this.r=r;} }
    final class Square implements Shape { double s; Square(double s){this.s=s;} }

    @Test
    public void test() throws InterruptedException {

            // --- Generics ---
        List<User> users = List.of(new User(1,"Alice"), new User(2,"Bob"));

        // --- Lambdas & Streams ---
        List<String> names = users.stream()
                .map(User::name)
                .filter(n -> n.startsWith("A"))
                .toList();

        System.out.println("Names: " + names);

        // --- Switch expressions ---
        Shape shape = new Circle(2.0);
        double area = switch (shape) {
            case Circle c -> Math.PI * c.r * c.r;
            case Square s -> s.s * s.s;
        };
        System.out.println("Area: " + area);

        // --- try-with-resources + HttpClient ---
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com"))
                .build();

        try (client) { // oui, HttpClient est AutoCloseable depuis Java 21
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + res.statusCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // --- Virtual threads ---
        try (ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Callable<String>> tasks = List.of(
                    () -> "Task 1",
                    () -> "Task 2"
            );
            exec.invokeAll(tasks).forEach(f -> {
                try { System.out.println(f.get()); }
                catch (Exception ignored) {}
            });
        }
    }
}
