# API reference

## Public Endpoints

These endpoints are accessible without authentication.

| Method | Path            | Purpose                                |
| ------ | --------------- | -------------------------------------- |
| `GET`  | `/public/health` | Health check endpoint, returns status |

## Authenticated Endpoints (v1)

All endpoints under `/api/v1` are secured with Bearer JWT.

| Method                | Path                              | Purpose                                |
| --------------------- | --------------------------------- | -------------------------------------- |
| `GET`                 | `/api/v1/watchlist/movies`        | Paged list ⇒ `?page=1&size=20`         |
| `POST`                | `/api/v1/watchlist/movies`        | Add/update one movie (send `MovieDto`) |
| `DELETE`              | `/api/v1/watchlist/movies/{movieId}` | Remove a movie                     |
| `GET`/`POST`/`DELETE` | `/api/v1/watchlist/people`        | Same trio for person watch‑list        |
| `GET`                 | `/api/v1/profile`                 | Get user profile information           |

**Paged response structure**

```jsonc
{
  "page": 1,
  "totalPages": 3,
  "results": [ { /* MovieDto */ } ]
}
```