package br.com.maferfs.desafio_webclient.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ListOfEpisodeResponse {
    private List<EpisodeResponse> results;
}
