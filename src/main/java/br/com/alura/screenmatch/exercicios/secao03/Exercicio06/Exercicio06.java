package br.com.alura.screenmatch.exercicios.secao03.Exercicio06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exercicio06 {
    public static void main(String[] args) {
        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa("Alice", 22),
                new Pessoa("Bob", 17),
                new Pessoa("Charlie", 19)
        );

        List<String> pessoasMaioresIdade = pessoas.stream()
                .filter(p -> p.getIdade() > 18)
                .map(Pessoa::getNome)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(pessoasMaioresIdade);
    }
}
