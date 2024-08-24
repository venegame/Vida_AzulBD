package com.vida.azul;

import Oracle.Conexion;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class VidaAzulApplication {

    public static void main(String[] args) {
        // Inicia la aplicación Spring Boot
        SpringApplication.run(VidaAzulApplication.class, args);
    }

    // Bean que se ejecuta después de que la aplicación Spring Boot haya sido iniciada
    @Bean
    public CommandLineRunner run() {
        return args -> {
            // Lógica para establecer la conexión a la base de datos
            try (Connection connection = Conexion.getConnection()) {
                if (connection != null) {
                    System.out.println("Conexión exitosa a la base de datos Oracle!");
                } else {
                    System.out.println("No se pudo establecer la conexión a la base de datos.");
                }
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos:");
                e.printStackTrace();
            }
        };
    }
}

