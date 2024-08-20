package com.learning.arangodb.character;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndex;
import lombok.*;
import org.springframework.data.annotation.Id;

@Document("characters")
@Data
@Builder
@PersistentIndex(fields = {"surname"})
public class CharacterDomain {

    @Id // db document field: _key
    private String id;

    @ArangoId // db document field: _id
    private String arangoId;

    private String name;

    private String surname;

    private boolean alive;

    private Integer age;
}
