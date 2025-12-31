package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Released") String dataLancamento,
        @JsonAlias("Season") String temporada,
        @JsonAlias("Episode") String numeroEpisodio,
        @JsonAlias("Runtime") String duracao,
        @JsonAlias("Plot") String sinopse,
        @JsonAlias("imdbRating") String avaliacao
) {
}
