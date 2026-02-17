# homefeed-service

Ein API-Service, der Homefeed-Module sammelt und sie dem aufrufenden Client bereitstellt.

# Anleitung

Starten der Anwendung: `./gradlew bootRun`

Tests: `./gradlew test`

## Endpunkte

- `GET /homefeed/`
  - Liefert die Homefeed-Module als vorgeordnete JSON-Liste (Greeting, Product Promotion, Sale).

Der Endpunkt ist erreichbar unter: `http://localhost:8080/homefeed/`

Ohne jq:
```curl -X GET "http://localhost:8080/homefeed/" \
    -H "Accept: application/json" 
```

Mit jq:
```curl -X GET "http://localhost:8080/homefeed/" \
    -H "Accept: application/json" | jq
```


# Schritte

### Problemanalyse und Brainstorming

Problem: Wir möchten einen API-Endpunkt bereitstellen, um Homefeed-Module abzurufen. Es gibt keine feste Anzahl an Modulen im Homefeed, daher muss das System leicht erweiterbar sein. Die Module müssen für den aufrufenden Client eindeutig identifizierbar sein.

Tech-Stack: Kotlin, Spring Boot, Gradle

Ziel:
- Stateless Service
- Asynchrone API
- Asynchrone Datenbankaufrufe
- Alle Module sollen als JSON serialisiert werden
- Die zurückgegebene Liste ist für den Client vorgeordnet
- Tests

Nicht im Scope:
- Authentifizierung (für einen realen Use Case wahrscheinlich JWT)
- Caching (ist nicht notwendig und sollte nur bei echtem Bedarf ergänzt werden)
- Fallbacks/Retry-Mechanismen (für kritische Placements könnte es ein Fallback-Modul geben, wenn andere Services ausfallen oder Netzwerkprobleme auftreten)
- Versionierung (für produktive Umgebungen)
- OpenTelemetry
- Logging

### Struktur

- Controller
  - Pfad- und API-Methoden-Definition
  - Bekommt die Services als Dependencies und erhält über diese die Daten
- Repository
  - Definiert die Schicht, die auf die Datenbank zugreift
- Service
  - Jedes Modul bekommt einen eigenen Service, der das finale Ergebnis für den Controller erstellt
  - In diesem Beispiel haben die Services nur Dependencies zu Repositories. Je nach Anwendungsfall braucht man aber ggf. auch HTTP-Clients oder andere Integrationen
  - Es gibt ein Interface, das die Module-Services implementieren sollen, um die API aus Controller-Sicht zu vereinfachen
- Model
  - Hier liegen die Model-Definitionen
  - Das Datenbank-Model wird nicht direkt für die API genutzt
  - Für die API gibt es jeweils ein spezielles Model, das man erweitern kann und bei dem über eine Base-Klasse der Typ definiert werden muss

### Fokus

Mein Fokus lag darauf, einen Stateless Service mit einer durchgängigen non-blocking API zu erstellen.
Die Struktur sollte sehr einfach sein und wenig Komplexität enthalten.
Meine persönliche Schwierigkeit war, in das neue Framework und die Programmiersprache reinzukommen. Dadurch habe ich gerade beim ersten Modul deutlich länger gebraucht. Alle weiteren liefen danach aber nach einem Muster und ich war deutlich schneller. Trotzdem fehlte mir zum Ende hin dadurch etwas Zeit.
Deswegen habe ich den Fokus auf eine saubere Architektur und einen lauffähigen MVP gesetzt.

### Erweiterungen

Bei den Tests würde ich noch nachbessern, vor allem in Richtung Integrationstests für die API-Endpunkte, das Zusammenspiel der Schichten und die Datenbank. Mit Testcontainers habe ich bisher gute Erfahrungen gemacht, und man kann damit sehr realitätsnahe Tests schreiben.
Der Controller holt Daten aus drei Dependencies. Hier müsste man konzeptionell überlegen, ob partielle Ergebnisse auch okay sind, bzw. mit einem Prioritätensystem sagen: Wenn Modul A und B da sind, aber C nicht, ist das okay. Sollte aber A nicht da sein, braucht es dafür ein Fallback.
Ich habe für den ersten Schritt eine H2-Datenbank genommen. Mit einem schnellen Docker-Setup könnte man hier auf das Datenbankformat wechseln, das man in Produktion nutzt, z. B. Postgres.
Was ebenfalls noch fehlt, ist das Dockerfile, das das Fat-JAR nimmt und in ein minimales Docker-Image integriert.
Wichtig hinzuzufügen wäre auch Logging, gerade in Fehlerfällen. Fehlerfälle müssen insgesamt besser behandelt werden: Was ist, wenn die Datenbank für längere Zeit weg ist? Oder ein externer Service? Gibt es Retries oder Fallback-Values?

### Kubernetes

Das Deployment des Services in Kubernetes ist recht einfach. Dadurch, dass es ein Stateless Service ist, kann man mehrere Instanzen gleichzeitig laufen lassen.
Ich würde hier schauen, wie kritisch der Service im Gesamtbild ist. Wahrscheinlich aber min. 3, max. 10 Services, die basierend auf Load (CPU, Antwortzeit) hoch- und runterskalieren.
Außerdem könnte man schauen, dass die Pods sinnvoll auf die Nodes aufgeteilt werden, damit man nicht in die Gefahr läuft, dass alle 10 Services auf Node 1 laufen und diese ausfällt. 

  
