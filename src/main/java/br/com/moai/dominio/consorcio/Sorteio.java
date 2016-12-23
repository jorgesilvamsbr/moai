package br.com.moai.dominio.consorcio;

import java.util.Random;

public class Sorteio {
    private static Random gerador = new Random();

    public static int sortear(int limite) {
        return gerador.nextInt(limite);
    }
}