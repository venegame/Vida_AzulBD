package com.vida.azul;

import Oracle.Conexion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VidaAzulApplication {

    public static void main(String[] args) {
        SpringApplication.run(VidaAzulApplication.class, args);
        Conexion conexion = new Conexion();

    }

}
