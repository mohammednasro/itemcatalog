# Item Catalog

This project provides the foundation of an item catalog microservice built with Spring Boot 3.3.3 and Java 17. The current scope covers the domain model, persistence configuration, and repository layer ready to be extended with business logic and API endpoints.

## Getting started

1. Ensure you have Java 17 and Maven installed.
2. Start a MySQL 8 instance (see Docker Compose snippet below).
3. Update the credentials in `src/main/resources/application.properties` if needed.
4. Build the project:
   ```bash
   mvn clean install
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Docker Compose (MySQL 8)

```yaml
services:
  mysql:
    image: mysql:8.4
    container_name: itemcatalog-mysql
    restart: unless-stopped
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: itemcatalog
      TZ: UTC
    ports:
      - "3306:3306"
    command: --default-authentication-plugin=mysql_native_password --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
    volumes:
      - mysql_data:/var/lib/mysql
volumes:
  mysql_data:
```

## Next steps

* Implement services, facades, and REST controllers.
* Introduce DTOs and mappers to expose the domain model safely.
* Extend validation, security, and documentation once the API layer is in place.
