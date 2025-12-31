package br.com.alura.screenmatch.exercicios.secao02.exercicio06;

import java.util.ArrayList;
import java.util.List;

public class Exercicio06 {
    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>(List.of("Maria", "João", "Gabriel", "Daniel"));

        nomes.sort(String::compareTo);
        nomes.sort((a, b) -> a.compareTo(b)); //IDE sugere com ref. ao metodo
        System.out.println(nomes);
    }
}
