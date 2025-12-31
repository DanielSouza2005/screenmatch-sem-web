package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(
        @JsonAlias("Season") Integer numeroTemporada,
        @JsonAlias("Episodes") List<DadosEpisodioResumido> episodios
) {
    public Stream<?> epio() {
        return null;
    }
}
