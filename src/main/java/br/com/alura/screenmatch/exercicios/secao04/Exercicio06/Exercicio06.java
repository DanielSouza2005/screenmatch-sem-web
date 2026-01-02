package br.com.alura.screenmatch.exercicios.secao04.Exercicio06;

import br.com.alura.screenmatch.exercicios.secao04.util.Produto;

import java.util.*;
import java.util.stream.Collectors;

public class Exercicio06 {
    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        Map<String, List<Produto>> produtosPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));

        System.out.println("Produtos X Categoria");
        System.out.println(produtosPorCategoria);

        Map<String, Long> quantidadePorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.counting()
                ));

        System.out.println("Quantidade de Produtos X Categoria");
        System.out.println(quantidadePorCategoria);

        Map<String, Optional<Produto>> produtoMaisCaroPorCategoria =
                produtos.stream()
                        .collect(Collectors.groupingBy(
                                Produto::getCategoria,
                                Collectors.maxBy(
                                        Comparator.comparing(Produto::getPreco)
                                )
                        ));

        System.out.println("Produtos mais caros por categoria: ");
        System.out.println(produtoMaisCaroPorCategoria);

        Map<String, Double> totalPrecosPorCategoria =
                produtos.stream()
                        .collect(Collectors.groupingBy(
                                Produto::getCategoria,
                                Collectors.summingDouble(Produto::getPreco))
                        );

        System.out.println("Somatório total de Produtos X Categoria");
        System.out.println(totalPrecosPorCategoria);
    }
}
