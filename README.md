# homefeed-service

An api services that gathers homefeed modules and provides them to the caller. 

# Steps

### First 30 - 60 minutes Problem Analysis and Brainstorming

Problem: We want to have an api endpoint to retrieve homefeed modules. There is no fixed number of modules shown on the homefeed, therefore it needs to be easily extendable. Modules need to be identifiable by the caller. 

Tech-Stack: Kotlin, Spring Boot, Gradle

Goal:
- Stateless Service (Easy deployment and scaling)
- Async API (Better use of the provided resources and higher throughput)
- Async Database Calls (Shared Database connection pool)
- All Module should serialize to JSON (For the use case the easiest)
- The returning list will be preordered for the caller (In a future version it would be better when the caller sends the placement ids to the backend so the order service can determine the perfect placement the return type is then a map of placements with the content)
- Tests

Out-Of-Scope: 
- Authentication (For a real use case probably JWT)
- Caching (It is not necessary and should be only added if really needed)
- Fallbacks/Retries (For critical placement there could be a fallback module when there are outage from other services or network problems)
- Versioning (For production envs)