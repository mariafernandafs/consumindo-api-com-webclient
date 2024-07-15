package br.com.maferfs.desafio_webclient.controller;

import br.com.maferfs.desafio_webclient.client.RickAndMortyClient;
import br.com.maferfs.desafio_webclient.response.CharacterResponse;
import br.com.maferfs.desafio_webclient.response.EpisodeResponse;
import br.com.maferfs.desafio_webclient.response.ListOfEpisodeResponse;
import br.com.maferfs.desafio_webclient.response.LocationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

    private RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id){
        return rickAndMortyClient.findACharacterById(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id){
        return rickAndMortyClient.findALocationById(id);
    }
    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id){
        return rickAndMortyClient.findAnEpisodeById(id);
    }

    @GetMapping("/episodes")
    public Flux<ListOfEpisodeResponse> getAllEpisode(){
        return rickAndMortyClient.findAllEpisodes();
    }
}
