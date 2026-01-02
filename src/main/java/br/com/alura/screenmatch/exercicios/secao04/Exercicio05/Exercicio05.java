package br.com.alura.screenmatch.exercicios.secao04.Exercicio05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exercicio05 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        List<Integer> numerosImpares = numeros.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());

        System.out.println("Pares: " + numerosPares);
        System.out.println("Ímpares: " + numerosImpares);
    }
}
