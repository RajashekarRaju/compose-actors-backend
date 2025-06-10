# Compose Actors - Backend Service

#### A Kotlin Spring boot backend that powers [ComposeActors](https://github.com/RajashekarRaju/compose-actors). It provides secure REST endpoints for managing personal watchlist and is ready for cloud deployment.

## Overview

| Layer      | Implementation                                                      |
| ---------- | ------------------------------------------------------------------- |
| Language   | Kotlin                                                              |
| Framework  | Spring Boot (REST, Security, Data MongoDB)                          |
| Database   | MongoDB Atlas                                                       |
| Auth/IAM   | AWS Cognito • JWT access-tokens (OAuth 2 Resource Server)           |
| Hosting    | Google Cloud Run • Serverless containers (managed, autoscaling)     |
| CI         | GitHub Actions for tests • Cloud Build trigger for deploy          |

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

```bash
# 1. clone & set vars
cp env.properties.sample env.properties

# 2. bootRun (hot‑reload)
./gradlew bootRun

# 3. verify
curl http://localhost:8080/public/health
```

## Deploying to Google Cloud Run

1. **Containerize via Cloud Build Trigger**
   Your trigger will build and push `gcr.io/YOUR_PROJECT/compose-actors-backend`.

2. **Environment Variables in Cloud Run**
   In the Cloud Run console, under **Variables & Secrets**, add:

    * `COGNITO_ISSUER_URI`
    * `MONGO_URI`
    * `MONGO_DATABASE`

3. **Always-warm instance** - To eliminate cold starts, edit the service’s **Autoscaling** and set **Min instances** = 1.

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