package com.vida.azul;

import Oracle.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class vidaazul {

    public static void main(String[] args) {
        try (Connection connection = Conexion.getConnection()) {
            if (connection != null) {
                System.out.println("Conexion exitosa a la base de datos Oracle!");

                String query = "SELECT * FROM categoria";
                try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

                    // Obtener los nombres de las columnas
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    // Imprimir los nombres de las columnas
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.println("Columna " + i + ": " + metaData.getColumnName(i));
                    }

                    // Procesar los resultados
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(metaData.getColumnName(i) + ": " + resultSet.getString(i) + " ");
                        }
                        System.out.println();
                    }
                }

            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
