# 🛒 Artere E-Commerce Catalog API

API REST de gestion de catalogue pour une application e-commerce, développée avec **Spring Boot**.  
Elle permet de gérer dynamiquement des **catégories** (avec sous-catégories) et des **produits**

Une documentation Swagger a été rajouter pour les tests.

---

## ✨ Fonctionnalités

- 🔁 CRUD sur les **catégories** (hiérarchiques)
- 📦 CRUD sur les **produits**
- 🔗 Association/dissociation entre catégories et produits
- ⚙️ Console H2 activée
- 🌐 Documentation Swagger intégrée

---

## 🧱 Architecture

src/
├── controllers/ # REST controllers
├── services/ # Business logic
├── models/
│ ├── dto/ # Data Transfer Objects
│ ├── entity/ # JPA entities
├── repositories/ # Spring Data JPA
├── mappers/ # Conversions Entity <-> DTO



## 🚀 Lancement de l’application

### 1. Prérequis

- Java 17+
- Maven

### 2. Commande

```bash
mvn spring-boot:run

L'application démarre sur :
http://localhost:8080


📚 Swagger
Accessible sur :
http://localhost:8080/swagger-ui/index.html
Permet de tester tous les endpoints sans outil externe.


🛢️ Console H2
Accessible sur :

bash
Copier
Modifier
http://localhost:8080/h2-console

Paramètres :
JDBC URL : jdbc:h2:mem:catalogdb
User : frnana
Password : (laisser vide)

NB: Une catégorie "Catalogue Principal" est automatiquement insérée à chaque démarrage.
