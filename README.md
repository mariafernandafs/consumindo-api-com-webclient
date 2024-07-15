## Desafio WebClient - Consumo de API Rick and Morty

Este projeto é uma aplicação Spring Boot que consome a API Rick and Morty usando o WebClient. O objetivo é demonstrar como configurar e utilizar o WebClient para realizar requisições a uma API externa e processar suas respostas.

### Tecnologias Utilizadas

- Java 11
- Spring Boot 2.5.x
- Spring WebFlux (WebClient)
- API Rick and Morty

### Configuração do Projeto

#### Pré-requisitos

- JDK 11 ou superior
- Maven 3.6.3 ou superior

### Estrutura do Projeto

- `src/main/java/com/exemplo/webclientrickandmorty/`
  - `controller/`: Contém os controladores REST.
  - `service/`: Contém as classes de serviço para consumir a API.
  - `model/`: Contém as classes de modelo para representar os dados da API.

### Implementação

#### Modelo

Crie um modelo para mapear a resposta da API Rick and Morty:

```java
package com.exemplo.webclientrickandmorty.model;

public class Character {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private Location location;
    private String image;
    private String[] episode;
    private String url;
    private String created;

    // Getters and Setters

    public static class Origin {
        private String name;
        private String url;

        // Getters and Setters
    }

    public static class Location {
        private String name;
        private String url;

        // Getters and Setters
    }
}
```
Serviço

Implemente um serviço para consumir a API Rick and Morty usando WebClient:
package com.exemplo.webclientrickandmorty.service;
```
import com.exemplo.webclientrickandmorty.model.Character;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RickAndMortyService {

    private final WebClient webClient;

    public RickAndMortyService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<Character> getCharacterById(Long id) {
        return this.webClient.get()
                .uri("/character/{id}", id)
                .retrieve()
                .bodyToMono(Character.class);
    }
}
```
Crie um controlador REST para expor um endpoint que consome a API Rick and Morty:
```
package com.exemplo.webclientrickandmorty.controller;

import com.exemplo.webclientrickandmorty.model.Character;
import com.exemplo.webclientrickandmorty.service.RickAndMortyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/rickandmorty")
public class RickAndMortyController {

    private final RickAndMortyService rickAndMortyService;

    public RickAndMortyController(RickAndMortyService rickAndMortyService) {
        this.rickAndMortyService = rickAndMortyService;
    }

    @GetMapping("/character/{id}")
    public Mono<Character> getCharacterById(@PathVariable Long id) {
        return rickAndMortyService.getCharacterById(id);
    }
}
```
Executando a Aplicação

Para executar a aplicação, utilize o comando Maven:
```mvn spring-boot:run```
