package com.learning.arangodb.character;

import com.arangodb.springframework.repository.ArangoRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends ArangoRepository<CharacterDomain, String> {

    Iterable<CharacterDomain> findBySurname(String surname);

    Collection<CharacterDomain> findTop2DistinctBySurnameIgnoreCaseOrderByAgeDesc(String surname);

    List<CharacterDomain> findBySurnameEndsWithAndAgeBetweenAndNameInAllIgnoreCase(
            String suffix, int lowerBound, int upperBound, String[] nameList);

    Optional<CharacterDomain> findByNameAndSurname(String name, String surname);
}
