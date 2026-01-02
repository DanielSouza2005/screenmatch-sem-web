package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private final Scanner input = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConverteDados conversor = new ConverteDados();
    private final List<DadosTemporada> temporadas = new ArrayList<>();

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

        System.out.println("Todos os Episódios");
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numeroTemporada(), d))
                )
                .collect(Collectors.toList());

        System.out.println(episodios);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor Nota: " + est.getMax());
        System.out.println("Pior Nota: " + est.getMin());
        System.out.println("Quantidade de Episódios: " + est.getCount());

//        System.out.println("Top 5 melhores episódios");
//        List<Episodio> melhoresEpisodios = episodios.stream()
//                .sorted(Comparator.comparing(
//                        Episodio::getAvaliacao,
//                        Comparator.reverseOrder()
//                ))
//                .limit(5)
//                .collect(Collectors.toList());
//        System.out.println(melhoresEpisodios);

//        System.out.println("Média de Avaliações por Temporada");
//        Map<Integer, Double> mediaAvaliacoesPorTemporada =
//                episodios.stream()
//                        .filter(e -> e.getAvaliacao() > 0)
//                        .collect(Collectors.groupingBy(
//                                Episodio::getTemporada,
//                                Collectors.averagingDouble(Episodio::getAvaliacao)
//                        ));
//
//        System.out.println(mediaAvaliacoesPorTemporada);

//        System.out.println("A partir de qual ano deseja ver os episódios?");
//        int ano = input.nextInt();
//
//        LocalDate dataFiltro = LocalDate.of(ano, 1, 1).minusDays(1); //ultimo dia do ano anterior
//        List<Episodio> episodiosFiltrados = episodios.stream()
//                .filter(e -> e.getDataLancamento().isAfter(dataFiltro))
//                .collect(Collectors.toList());
//
//        System.out.println(episodiosFiltrados);
    }
}
