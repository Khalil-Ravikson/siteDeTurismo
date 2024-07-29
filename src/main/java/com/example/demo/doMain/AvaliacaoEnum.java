package com.example.demo.doMain;

public enum AvaliacaoEnum {
    UM(1),
    DOIS(2),
    TRES(3),
    QUATRO(4),
    CINCO(5);

    private final int valor;

    AvaliacaoEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static AvaliacaoEnum fromValue(int valor) {
        for (AvaliacaoEnum avaliacao : values()) {
            if (avaliacao.valor == valor) {
                return avaliacao;
            }
        }
        throw new IllegalArgumentException("Valor de avaliação inválido: " + valor);
    }
}
