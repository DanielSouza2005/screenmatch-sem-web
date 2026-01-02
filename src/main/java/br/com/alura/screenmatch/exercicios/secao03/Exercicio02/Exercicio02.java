package br.com.alura.screenmatch.exercicios.secao03.Exercicio02;

import java.util.Arrays;
import java.util.List;

public class Exercicio02 {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "stream", "lambda");
        palavras.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
