# learning_arango

This aims to document all learning notes for Arango

As of 19 August, I am learning this because of work

Please let it be a good one

---

# Resources

- [Setting up locally with docker compose](https://dev.to/sonyarianto/how-to-spin-arangodb-server-with-docker-and-docker-compose-3c00)

---

# To Start

Go to http://localhost:8529 for web-ui

```
docker-compose up -d

# to use cli
docker exec -it 8b3 arangosh
```

---

# AQL Basics

1. Query Document

```aql
db._query('RETURN DOCUMENT("users/624")')
```
