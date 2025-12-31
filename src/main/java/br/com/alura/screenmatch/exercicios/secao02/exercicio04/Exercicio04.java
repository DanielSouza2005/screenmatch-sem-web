package br.com.alura.screenmatch.exercicios.secao02.exercicio04;

public class Exercicio04 {
    public static void main(String[] args) {
        Palindromo palindromo = a -> a.contentEquals(new StringBuilder(a).reverse());

        System.out.println(palindromo.verificaPalindromo("radar"));
        System.out.println(palindromo.verificaPalindromo("java"));
        System.out.println(palindromo.verificaPalindromo("arara"));
    }
}
