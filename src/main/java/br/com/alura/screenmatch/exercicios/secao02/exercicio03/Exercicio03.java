package br.com.alura.screenmatch.exercicios.secao02.exercicio03;

public class Exercicio03 {
    public static void main(String[] args) {
        UpperCaseString reverseString = String::toUpperCase;

        System.out.println(reverseString.upperString("Daniel"));
    }
}
