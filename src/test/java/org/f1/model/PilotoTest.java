package org.f1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Pruebas de la clase Piloto")
class PilotoTest {
    private Piloto piloto;
    private Equipo equipo;

    @BeforeEach
    void setUp() {
        equipo = new Equipo("Red Bull Racing", "Christian Horner", "Reino Unido", "Honda RBPT");
        piloto = new Piloto("Max", "Verstappen", "Holandés", 26, equipo, 1, "VER");
    }

    @Test
    @DisplayName("Calcular puntos por primer lugar debe retornar 25 puntos")
    void calcularPuntosPorPosicion_PrimerLugar_Retorna25Puntos() {
        assertEquals(25, piloto.calcularPuntosPorPosicion(1), 
            "El primer lugar debe obtener 25 puntos");
    }

    @Test
    @DisplayName("Calcular puntos por décimo lugar debe retornar 1 punto")
    void calcularPuntosPorPosicion_DecimoLugar_Retorna1Punto() {
        assertEquals(1, piloto.calcularPuntosPorPosicion(10), 
            "El décimo lugar debe obtener 1 punto");
    }

    @Test
    @DisplayName("Calcular puntos fuera del top 10 debe retornar 0 puntos")
    void calcularPuntosPorPosicion_FueraDePuntos_Retorna0Puntos() {
        assertEquals(0, piloto.calcularPuntosPorPosicion(11), 
            "Las posiciones fuera del top 10 no deben obtener puntos");
    }

    @Test
    @DisplayName("Obtener posición promedio sin carreras debe retornar 0")
    void obtenerPosicionPromedio_SinCarreras_RetornaCero() {
        assertEquals(0.0, piloto.obtenerPosicionPromedio(), 
            "Sin carreras disputadas, el promedio debe ser 0");
    }

    @Test
    @DisplayName("Obtener posición promedio con dos carreras debe retornar el promedio correcto")
    void obtenerPosicionPromedio_ConDosCarreras_RetornaPromedioCorrecto() {
        piloto.actualizarPuntos(1, "Bahrein");
        piloto.actualizarPuntos(3, "Arabia Saudita");
        
        assertEquals(2.0, piloto.obtenerPosicionPromedio(), 
            "El promedio de posiciones 1 y 3 debe ser 2.0");
    }

    @Test
    @DisplayName("Obtener información completa debe retornar el formato correcto")
    void obtenerInformacionCompleta_FormatoCorrecto() {
        piloto.setCampeonatosGanados(3);
        piloto.sumarPuntos(25);
        
        String esperado = "Max Verstappen - Red Bull Racing - Puntos 2024: 25 - Campeonatos: 3";
        assertEquals(esperado, piloto.obtenerInformacionCompleta(), 
            "El formato de la información debe coincidir exactamente");
    }
} 