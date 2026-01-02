package br.com.alura.screenmatch.exercicios.secao04.Exercicio01;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Exercicio01 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);

        DoubleSummaryStatistics est = numeros.stream()
                .collect(Collectors.summarizingDouble(n -> n));

        System.out.println(est.getMax());
    }
}
