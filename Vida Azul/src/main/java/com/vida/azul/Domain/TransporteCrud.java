/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.Domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "transportes")
public class TransporteCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transporte")
    private Long idTransporte;

    @Column(name = "id_usuario")
    private Long idUsuario;  // Se almacena el ID del usuario directamente

    @Column(name = "nombre_transporte")
    private String nombreTransporte;

    @Column(name = "ruta_transporte")
    private String rutaTransporte;

    @Column(name = "horario_transporte")
    private String horarioTransporte;

    @Column(name = "precio_transporte")
    private Double precioTransporte;
}