package com.example.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.*;

@SpringBootApplication
@RestController
public class DemoApplication {

    @GetMapping("/")
    String home() {
        return "Java Frontend is running!";
    }

    @GetMapping("/users")
    List<String> getUsers() {
        List<String> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                System.getenv("DB_URL"),
                System.getenv("DB_USER"),
                System.getenv("DB_PASSWORD"))) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username FROM users");
            while (rs.next()) {
                users.add(rs.getString("username"));
            }
        } catch (Exception e) {
            users.add("DB Error: " + e.getMessage());
        }
        return users;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

