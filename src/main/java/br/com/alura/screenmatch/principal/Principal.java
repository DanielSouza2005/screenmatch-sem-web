package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosEpisodioResumido;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner input = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private List<DadosTemporada> temporadas = new ArrayList<>();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=15fd6310";

    public void exibeMenu() {
        System.out.println("Digite o nome da Série");
        String nomeSerie = input.nextLine();

        String json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dadosSerie = conversor.converterDados(json, DadosSerie.class);

        json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=1&episode=1" + APIKEY);
        DadosEpisodio dadosEpisodio = conversor.converterDados(json, DadosEpisodio.class);

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + APIKEY);
            DadosTemporada dadosTemporada = conversor.converterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        System.out.println(dadosSerie);
        System.out.println(dadosEpisodio);
        temporadas.forEach(System.out::println);

        temporadas.stream()
                .flatMap(temporada -> temporada.episodios().stream())
                .map(DadosEpisodioResumido::titulo)
                .forEach(System.out::println);
    }
}
