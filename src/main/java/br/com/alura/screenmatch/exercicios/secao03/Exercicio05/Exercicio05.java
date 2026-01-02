package br.com.alura.screenmatch.exercicios.secao03.Exercicio05;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Exercicio05 {
    public static void main(String[] args) {
        List<List<Integer>> listaDeNumeros = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12)
        );

        List<Integer> listaNumerosPrimos = listaDeNumeros.stream()
                .flatMap(Collection::stream)
                .filter(Exercicio05::ehPrimo)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(listaNumerosPrimos);
    }

    public static boolean ehPrimo(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
