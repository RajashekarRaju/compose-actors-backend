# API reference

All endpoints are under `/watchlist` and secured with Bearer JWT.

| Method                | Path                          | Purpose                                |
| --------------------- | ----------------------------- | -------------------------------------- |
| `GET`                 | `/watchlist/movies`           | Paged list ⇒ `?page=1&size=20`         |
| `POST`                | `/watchlist/movies`           | Add/update one movie (send `MovieDto`) |
| `DELETE`              | `/watchlist/movies/{movieId}` | Remove a movie                         |
| `GET`/`POST`/`DELETE` | `/watchlist/people`           | Same trio for person watch‑list        |

**Paged response structure**

```jsonc
{
  "page": 1,
  "totalPages": 3,
  "results": [ { /* MovieDto */ } ]
}
```