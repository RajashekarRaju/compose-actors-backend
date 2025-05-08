# Compose Actors - Backend Service

#### A Kotlin Spring boot backend that powers [ComposeActors](https://github.com/RajashekarRaju/compose-actors). It provides secure REST endpoints for managing personal watchlist and is ready for cloud deployment.

## Overview

| Layer    | Implementation                                                   |
| -------- |------------------------------------------------------------------|
| Language | Kotlin                                                           |
| Framework | Spring boot (REST, Security, Data MongoDB)                       |
| Database | MongoDB Atlas                                                    |
| Auth/IAM | AWS Cognito • JWT access‑tokens (OAuth 2 Resource Server)        |
| Hosting  | Render.com • Docker container image        |
| CI       | Render continuous deploy (Docker build) • local`./gradlew build` |

---

## Environment variables

### For local runs create an **`env.properties`** file at project root.
### Spring Boot loads it with `spring.config.import=optional:file:env.properties`.

| Key                  | Description                                      | Example (dev)                                   |
| -------------------- |--------------------------------------------------|-------------------------------------------------|
| `COGNITO_ISSUER_URI` | OIDC discovery URL of your AWS Cognito user‑pool | `https://cognito-idp.domain/XXXXXXX` |
| `MONGO_URI`          | SRV connection string (includes credentials)     | `mongodb+srv://user:pw@cluster.mongodb.net`     |
| `MONGO_DATABASE`     | Database name to use                             | `compose-actors`                                |

---

## Running locally

```
# 1. clone & set vars
cp env.properties.sample env.properties

# 2. bootRun (hot‑reload)
./gradlew bootRun

# 3. open http://localhost:8080/actuator/health
```

If you prefer Docker:

```
docker build -t composeactors .
docker run -p 8080:8080 --env-file env.properties composeactors
```

## Deploying to Render

1. Create a new Web Service → Dockerfile on Render.
2. Add Environment - Secret Files or Env Variables for the three keys.
3. Allow your Render outbound IPs in MongoDB Atlas IP Access List.
4. Trigger the first deploy - Render builds the Dockerfile & exposes port `8080` automatically.

---

## License

```
Copyright 2025 Rajasekhar K E

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```