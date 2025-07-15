# API reference

## Public Endpoints

These endpoints are accessible without authentication.

| Method | Path            | Purpose                                |
| ------ | --------------- | -------------------------------------- |
| `GET`  | `/public/health` | Health check endpoint, returns status |

## Authenticated Endpoints

All endpoints under `/watchlist` are secured with Bearer JWT.

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