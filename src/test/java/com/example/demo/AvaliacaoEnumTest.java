package com.example.demo;

import com.example.demo.doMain.AvaliacaoEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoEnumTest {

    @Test
    void testEnumValues() {
        assertEquals(1, AvaliacaoEnum.UM.getValor());
        assertEquals(2, AvaliacaoEnum.DOIS.getValor());
        assertEquals(3, AvaliacaoEnum.TRES.getValor());
        assertEquals(4, AvaliacaoEnum.QUATRO.getValor());
        assertEquals(5, AvaliacaoEnum.CINCO.getValor());
    }

    @Test
    void testFromValue() {
        assertEquals(AvaliacaoEnum.UM, AvaliacaoEnum.fromValue(1));
        assertEquals(AvaliacaoEnum.DOIS, AvaliacaoEnum.fromValue(2));
        assertEquals(AvaliacaoEnum.TRES, AvaliacaoEnum.fromValue(3));
        assertEquals(AvaliacaoEnum.QUATRO, AvaliacaoEnum.fromValue(4));
        assertEquals(AvaliacaoEnum.CINCO, AvaliacaoEnum.fromValue(5));
    }

    @Test
    void testInvalidFromValue() {
        assertThrows(IllegalArgumentException.class, () -> AvaliacaoEnum.fromValue(0));
        assertThrows(IllegalArgumentException.class, () -> AvaliacaoEnum.fromValue(6));
    }
}