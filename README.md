TechDrop

Project Description

TechDrop is an e-commerce web application for an online electronics store. Customers can browse products, search by category, add items to their shopping cart, place orders, and leave reviews.

Team Members

- Kanwarnoor Singh
- Moteen Raza Bag
- Shabnam Hajiyeva

Technologies

- Java
- Spring Boot
- Thymeleaf
- Spring Data JPA
- Spring Security
- MySQL

How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA or Eclipse.
3. Run the Spring Boot application.
4. Open http://localhost:8080 in your browser.

Config / Profiles

For Deliverable 3 we moved off application.properties and split everything into
YAML files instead (src/main/resources):

- application.yml - stuff every profile needs (app name, thymeleaf, jpa/hibernate
  logging, etc)
- application-dev.yml - dev profile, runs on an in-memory H2 db. This is the
  default so if you just clone the repo and hit run it works right away, no
  MySQL needed. H2 console is at localhost:8080/h2-console if you want to poke
  around the tables (jdbc url: jdbc:h2:mem:techdrop, user sa, no password)
- application-prod.yml - prod profile, points at a real MySQL db
  (jdbc:mysql://localhost:3306/techdrop)

You don't need to touch any code to switch between them, just pass the profile
flag:

    mvnw spring-boot:run -Dspring-boot.run.profiles=prod

or on the built jar:

    java -jar target/techdrop-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

leave it off and it defaults to dev/H2.

MySQL username/password default to root/root in application-prod.yml but
obviously that only works if your local MySQL actually uses that. Override
with env vars instead of hardcoding your password into the yml:

    export DB_USERNAME=root
    export DB_PASSWORD=yourpassword
    mvnw spring-boot:run -Dspring-boot.run.profiles=prod

couple things that wasted like an hour of our time figuring out, if this
happens to you too:

- passing -DDB_PASSWORD=xyz directly on the mvnw command doesn't actually
  work when running through spring-boot:run, it gets swallowed by maven and
  never reaches the app. has to be a proper env var (export on mac/git bash,
  set on windows cmd) set BEFORE you run the command, not as part of it
- also make sure MySQL is actually running (on windows check Services, MySQL80
  should say Running) and that the techdrop schema exists, otherwise you'll
  get connection errors that have nothing to do with the yml config:

      mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS techdrop"
