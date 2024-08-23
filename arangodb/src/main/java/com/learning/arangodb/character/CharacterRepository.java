package com.learning.arangodb.character;

import com.arangodb.springframework.annotation.BindVars;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends ArangoRepository<CharacterDomain, String> {

    Iterable<CharacterDomain> findBySurname(String surname);

    Collection<CharacterDomain> findTop2DistinctBySurnameIgnoreCaseOrderByAgeDesc(String surname);

    List<CharacterDomain> findBySurnameEndsWithAndAgeBetweenAndNameInAllIgnoreCase(
            String suffix, int lowerBound, int upperBound, String[] nameList);

    Optional<CharacterDomain> findByNameAndSurname(String name, String surname);

    // Retrieve Total count by Alive
    int countByAliveTrue();

    // Remove all characters except those specified in surname and who are alive
    void removeBySurnameNotLikeOrAliveFalse(String surname);

    // -- Query Methods --

    @Query("""
       FOR c in characters FILTER c.surname == @surname
        SORT c.age ASC RETURN c
     """)
    Iterable<CharacterDomain> getWithSurname(@Param("surname") String value);

    @Query("""
      FOR c IN @@col FILTER c.surname == @surname AND c.age > @age RETURN c
    """)
    Iterable<CharacterDomain> getWithSurnameOlderThan(@Param("age") int value, @BindVars Map<String, Object> bindVars);
}
