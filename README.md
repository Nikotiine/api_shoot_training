# API Shoot Training

## Installation

* Telecharger les dependances Maven
* Avoir une instance PostgresSql ou utiliser le docker-compose du projet
```bash
docker-compose up -d
```
* Initialiser une nouvelle base de données
* Creer un fichier .env a la racine du projet et mettre les variable suivantes:
```
DB_PASSWORD=postgres
DB_USERNAME=postgres
DB_HOST=localhost
DB_PORT=5432
DB_NAME=
EMAIL_USERNAME=
EMAIL_PASSWORD=
EMAIL_HOST=
SECURITY_USERNAME=
SECURITY_PASSWORD=

OPEN_API_EMAIL=
OPEN_API_USERNAME=
OPEN_API_WEBSITE_URL=
```
* Demarrer le projet.
* Pour les utilisateur de Intellij telecharger le plugin JPA BUDDY
* Le swagger d'open api est sur l'url : (http://localhost:8080/swagger-ui/index.html#/)# api_shoot_training
