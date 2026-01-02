package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SerieService {

    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=15fd6310";

    public DadosSerie buscarDadosSerie(String nomeSerie) {
        String json = consumoAPI.obterDados(
                ENDERECO + formatarNome(nomeSerie) + APIKEY
        );
        return conversor.converterDados(json, DadosSerie.class);
    }

    public DadosEpisodio buscarEpisodioExemplo(String nomeSerie) {
        String json = consumoAPI.obterDados(
                ENDERECO + formatarNome(nomeSerie) + "&season=1&episode=1" + APIKEY
        );
        return conversor.converterDados(json, DadosEpisodio.class);
    }

    public List<DadosTemporada> buscarTemporadas(String nomeSerie, int totalTemporadas) {
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= totalTemporadas; i++) {
            String json = consumoAPI.obterDados(
                    ENDERECO + formatarNome(nomeSerie) + "&season=" + i + APIKEY
            );
            DadosTemporada temporada =
                    conversor.converterDados(json, DadosTemporada.class);
            temporadas.add(temporada);
        }

        return temporadas;
    }

    public List<Episodio> obterTodosEpisodios(List<DadosTemporada> temporadas) {
        return temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numeroTemporada(), d)))
                .collect(Collectors.toList());
    }

    public DoubleSummaryStatistics calcularEstatisticas(List<Episodio> episodios) {
        return episodios.stream()
                .filter(e -> e.getAvaliacao() > 0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
    }

    public List<Episodio> obterTop5MelhoresEpisodios(List<Episodio> episodios) {
        return episodios.stream()
                .filter(e -> e.getAvaliacao() > 0)
                .sorted(Comparator.comparing(
                        Episodio::getAvaliacao,
                        Comparator.reverseOrder()
                ))
                .limit(5)
                .collect(Collectors.toList());
    }

    public Map<Integer, Double> obterMediaAvaliacoesPorTemporada(List<Episodio> episodios) {
        return episodios.stream()
                .filter(e -> e.getAvaliacao() > 0)
                .collect(Collectors.groupingBy(
                        Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)
                ));
    }

    public List<Episodio> filtrarEpisodiosPorAno(List<Episodio> episodios, int ano) {
        LocalDate dataFiltro = LocalDate.of(ano, 1, 1).minusDays(1);

        return episodios.stream()
                .filter(e -> e.getDataLancamento() != null)
                .filter(e -> e.getDataLancamento().isAfter(dataFiltro))
                .collect(Collectors.toList());
    }

    private String formatarNome(String nomeSerie) {
        return nomeSerie.replace(" ", "+");
    }
}
