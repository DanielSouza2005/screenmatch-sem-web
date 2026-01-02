package br.com.alura.screenmatch.exercicios.secao03.Exercicio04;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercicio04 {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        Set<String> palavrasUnicas = new HashSet<>(palavras);

        System.out.println(palavrasUnicas);
    }
}
