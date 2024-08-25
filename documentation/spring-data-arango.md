# @Document

- Marks the class as a candidate for mapping to the database
- `value` specifies the collection name in the database

```java

@Document(value="persons")
public class Person {
  ...
}

```

### Spring Expression Support

- Spring Data ArangoDB supports the use of SpEL expressions within @Document#value. This feature lets you define a dynamic collection name which can be used to implement multi tenancy applications.

```java

@Component
public class TenantProvider {

  public String getId() {
    // threadlocal lookup
  }

}

@Document("#{tenantProvider.getId()}_persons")
public class Person {
  ...
}

```

### @From and @To

- When applied on a collection/array field in a class with `@Document`, the nested edge objects are fetched from the database.
- Each of the nested edge objects has to be stored as a separate edge document in the edge collection described in the `@Edge` annotation of the nested object class with the `_id` of the parent document as field `_from` or `_to`

---

# @Edge

- Marks the class as a candidate for mapping to the databse.
- `value` defines the collection name in the database

### Spring Expression Support

- Spring Data ArangoDB supports the use of SpEL expressions within @Edge#value. This feature lets you define a dynamic collection name which can be used to implement multi tenancy applications.

```java

@Component
public class TenantProvider {

  public String getId() {
    // threadlocal lookup
  }

}

@Edge("#{tenantProvider.getId()}_relations")
public class Relation {
  ...
}

```

---

# @Relations

- With the annotation `@Relations` applied on a collection/array field in a class annotated with `@Document`, the nested objects are fetched from database over a graph traversal with your current object as the starting point

- Can define `edge` parameter with the collection you are using.

- Define `depth` which implies the maximum depth of traversal

- Define `direction` which is iether outgoing or incoming or ANY

```java

@Document(value="persons")
public class Person {
  @Relations(edge=Relation.class, depth=1, direction=Direction.ANY)
  private List<Person> friends;
}

@Edge(name="relations")
public class Relation {

}

```
