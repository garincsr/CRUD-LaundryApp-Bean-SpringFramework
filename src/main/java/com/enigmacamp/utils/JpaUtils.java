package com.enigmacamp.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JpaUtils {
    public static EntityManagerFactory emf;

    private static Map<String, String> loadEnv() {
        Map<String, String> env = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && line.contains("=")) {
                    String[] parts = line.split("=", 2); // Memisahkan key dan value
                    env.put(parts[0].trim(), parts[1].trim()); // Menyimpan ke dalam map
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading .env file", e);
        }
        return env;
    }


    private static void getEntityManagerFactory(){
        Map<String, String> env = loadEnv();

        Map<String, String> properties = Map.of(
                "jakarta.persistence.jdbc.user", env.getOrDefault("DB_USERNAME","admin"),
                "jakarta.persistence.jdbc.password", env.getOrDefault("DB_PASSWORD", "123")
        );
        emf = Persistence.createEntityManagerFactory("enigma-persistence", properties);
    }

    public static EntityManager getEntityManager(){
        if (emf == null){
            getEntityManagerFactory();
        }
        return emf.createEntityManager();
    }

    public static void closeEntityManager(){
        if(emf != null){
            emf.close();
            emf = null;
        }
    }

}
