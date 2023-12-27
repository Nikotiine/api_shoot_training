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
DB_NAME=db_shoot_training
EMAIL_USERNAME= votre email associer au serveur smtp
EMAIL_PASSWORD= le password du serveur smtp
EMAIL_HOST= le serveur smtp
SECURITY_USERNAME= user pour connection a springboot security
SECURITY_PASSWORD= password pour connection a springboot security

OPEN_API_EMAIL=contact@nicolas-godin.fr
OPEN_API_USERNAME=Nicolas
OPEN_API_WEBSITE_URL=https://nicolas-godin.fr/

JWT_SECRET_KEY= clef secrete pour signature du token HS512
```
* Demarrer le projet.
* Pour les utilisateur de Intellij telecharger le plugin JPA BUDDY
* Le swagger d'open api est sur l'url : (http://localhost:8080/swagger-ui/index.html#/)# api_shoot_training
