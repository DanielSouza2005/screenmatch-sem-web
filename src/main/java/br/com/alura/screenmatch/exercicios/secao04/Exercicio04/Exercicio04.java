package br.com.alura.screenmatch.exercicios.secao04.Exercicio04;

import java.util.Arrays;
import java.util.List;

public class Exercicio04 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        int somaDosQuadradosDosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n * n)
                .sum();

        System.out.println(somaDosQuadradosDosPares);
    }
}
