package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.SerieService;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    private final Scanner input = new Scanner(System.in);
    private final SerieService serieService = new SerieService();

    public void exibeMenu() {
        System.out.println("Digite o nome da Série");
        String nomeSerie = input.nextLine();

        DadosSerie dadosSerie = serieService.buscarDadosSerie(nomeSerie);
        DadosEpisodio episodioExemplo = serieService.buscarEpisodioExemplo(nomeSerie);

        List<DadosTemporada> temporadas =
                serieService.buscarTemporadas(nomeSerie, dadosSerie.totalTemporadas());

        List<Episodio> episodios =
                serieService.obterTodosEpisodios(temporadas);

        System.out.println(dadosSerie);
        System.out.println(episodioExemplo);

        DoubleSummaryStatistics est =
                serieService.calcularEstatisticas(episodios);

        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor Nota: " + est.getMax());
        System.out.println("Pior Nota: " + est.getMin());
        System.out.println("Quantidade de Episódios: " + est.getCount());

        System.out.println("\nTop 5 melhores episódios");
        System.out.println(serieService.obterTop5MelhoresEpisodios(episodios));

        System.out.println("\nMédia de avaliações por temporada");
        Map<Integer, Double> medias =
                serieService.obterMediaAvaliacoesPorTemporada(episodios);
        System.out.println(medias);

        System.out.println("\nA partir de qual ano deseja ver os episódios?");
        int ano = input.nextInt();

        List<Episodio> filtrados =
                serieService.filtrarEpisodiosPorAno(episodios, ano);
        System.out.println(filtrados);
    }
}
