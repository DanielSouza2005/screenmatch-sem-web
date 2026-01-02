package br.com.alura.screenmatch.exercicios.secao04.Exercicio02;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercicio02 {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");

        Map<Integer, List<String>> agrupamentoPorTamanhoDaString = palavras.stream()
                .collect(Collectors.groupingBy(
                        String::length
                ));

        System.out.println(agrupamentoPorTamanhoDaString);
    }
}
