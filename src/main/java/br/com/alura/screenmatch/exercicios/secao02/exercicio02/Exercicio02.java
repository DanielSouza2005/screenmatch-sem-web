package br.com.alura.screenmatch.exercicios.secao02.exercicio02;

public class Exercicio02 {
    public static void main(String[] args) {
        Primo primo = n -> {
            if (n <= 1) return false;

            for (int i = 2; i < Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }

            return true;
        };

        System.out.println(primo.verificaPrimo(1));
        System.out.println(primo.verificaPrimo(11));
        System.out.println(primo.verificaPrimo(12));
    }
}
