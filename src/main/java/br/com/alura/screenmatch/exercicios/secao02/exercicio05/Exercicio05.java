package br.com.alura.screenmatch.exercicios.secao02.exercicio05;

import java.util.ArrayList;
import java.util.List;

public class Exercicio05 {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        Integer multiplicador = 3;

        numeros.replaceAll(n -> n * multiplicador);

        System.out.println(numeros);
    }
}
