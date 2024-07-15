package br.com.maferfs.desafio_webclient.client;

import br.com.maferfs.desafio_webclient.response.CharacterResponse;
import br.com.maferfs.desafio_webclient.response.EpisodeResponse;
import br.com.maferfs.desafio_webclient.response.ListOfEpisodeResponse;
import br.com.maferfs.desafio_webclient.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {
    private final WebClient webClient;
    public RickAndMortyClient(WebClient.Builder builder){
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }
    public Mono<CharacterResponse> findACharacterById(String id){
        log.info("Buscando o personagem com o id [{}]", id);
        return webClient
                .get()
                .uri("/character/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(CharacterResponse.class);
    }
    public Mono<LocationResponse> findALocationById(String id){
        log.info("Buscando o localização com o id [{}]", id);
        return webClient
                .get()
                .uri("/location/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(LocationResponse.class);
    }
    public Mono<EpisodeResponse> findAnEpisodeById(String id){
        log.info("Buscando o episódio com o id [{}]", id);
        return webClient
                .get()
                .uri("/episode/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(EpisodeResponse.class);
    }
    public Flux<ListOfEpisodeResponse> findAllEpisodes(){
        log.info("Listando todos os episódios");
        return webClient
                .get()
                .uri("/episode/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToFlux(ListOfEpisodeResponse.class);
    }
}
