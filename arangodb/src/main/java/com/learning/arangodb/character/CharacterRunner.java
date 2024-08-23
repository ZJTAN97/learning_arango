package com.learning.arangodb.character;

import com.arangodb.springframework.core.ArangoOperations;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterRunner implements CommandLineRunner {

    private final ArangoOperations operations;

    private final CharacterRepository characterRepository;

    @Override
    public void run(String... args) throws Exception {

        operations.dropDatabase();

        CharacterDomain saved = characterRepository.save(CharacterDomain.builder()
                .name("Docker")
                .surname("Kubernetes")
                .alive(true)
                .age(10)
                .build());

        Optional<CharacterDomain> character = characterRepository.findById(saved.getId());
        System.out.println(character.toString());

        characterRepository.saveAll(this.createCharacters());

        Page<CharacterDomain> findALl =
                characterRepository.findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "name")));
        System.out.println(findALl.toString());

        Iterable<CharacterDomain> queryBySurname = characterRepository.getWithSurname("Kubernetes");
        System.out.println(queryBySurname.toString());
    }

    private Collection<CharacterDomain> createCharacters() {
        return Arrays.asList(
                CharacterDomain.builder()
                        .name("Docker 2")
                        .surname("Kubernetes 2")
                        .alive(true)
                        .age(11)
                        .build(),
                CharacterDomain.builder()
                        .name("Docker 3")
                        .surname("Kubernetes 3")
                        .alive(true)
                        .age(12)
                        .build());
    }
}
