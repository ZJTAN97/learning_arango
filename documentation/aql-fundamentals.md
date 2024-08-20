# AQL Basics

1. Query Document

```sql
RETURN DOCUMENT("users/624")
```

2. Insert a document

```sql
INSERT { "name": "Kubernetes", "bio": "Testing Arango Insertion", "roles": ["ADMIN", "EDITOR", "VIEWER"] } INTO users

RETURN NEW
```

![Data Types in AQL](data-types-in-aql.png)

3. Iterate over documents

```sql

FOR user IN users
RETURN user

```

4. Sorting Operations

```sql

FOR user IN users
SORT user._key DESC /* Default is ASC */
RETURN user

```

5. Filter documents

```sql

FOR user IN users
FILTER user.age > 30
SORT user.age
RETURN user

```

6. Update existing documents

```sql

UPDATE "624" WITH { age: 13 } IN users
RETURN NEW

```

7. Replace existing documents

UPDATE allows to partially edit an existing document. There is also REPLACE, which would remove all attributes (except for \_key and \_id, which remain the same) and only add the specified ones. UPDATE on the other hand only replaces the specified attributes and keeps everything else as-is.

```sql

REPLACE "624" WITH { age: 13 } IN users
RETURN NEW

```

8. Computing new values

CONCAT() is a function that can join elements together to a string. We use it here to return a statement for every user. As you can see, the result set does not always have to be an array of objects:

```sql

FOR user IN users
RETURN CONCAT(user.name, "'s age is ", user.age)

```

The `LET` keyword is followed by the designated variable name (sumOfAges), then there’s a = symbol and the value or an expression to define what value the variable is supposed to have.

```sql
FOR user1 IN users
  FOR user2 IN users
    FILTER user1 != user2
    LET sumOfAges = user1.age + user2.age
    FILTER sumOfAges < 100
    RETURN {
        pair: [user1.name, user2.name],
        sumOfAges: sumOfAges
    }
```

9. Deleting documents

```sql

REMOVE "9883" IN users

```
