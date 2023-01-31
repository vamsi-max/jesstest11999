<a name="readme-top"></a>
<div align="center">
  <a href="https://github.com/platform-dx/template-spring-java">
    <img src="https://miro.medium.com/max/1400/1*RWc78aUjHJZp41lMxnFdyw.webp" alt="Logo" width="280" height="160">
  </a>
  <br />
  <br />
  <h1 align="center">jesstest11999</h1>
  <p align="center">
  <br />

    teste

  <br />
  <br />
  </p>
</div>

### Built With

* [![Java][java.js]][java-url]
* [![Maven][Maven.js]][Maven-url]
* [![Spring][Spring.js]][Spring-url]
* [![Flywaydb][Flywaydb.js]][Flywaydb-url]
* [![Docker][Docker.js]][Docker-url]

<br />

## Getting Started

Clean and install dependencies

```sh
./mvnw clean install
```

Run test

```sh
mvn test   
```

Run application locally

```sh
./mvnw spring-boot:run
```

### Using docker:

Build the image

```sh
docker image build -t docker-java-jar:latest .   
```

Run the application on docker

```sh
docker run --rm -it -p 8080:8080/tcp docker-java-jar:latest 
```

<!-- MARKDOWN LINKS & IMAGES -->
[Java.js]: https://img.shields.io/badge/Java-19.0.1-orange?style=plastic&logo=java
[Java-url]: https://java.com/
[Spring.js]: https://img.shields.io/badge/Spring%20Boot-3.0.1-green?style=plastic&logo=springboot
[Spring-url]: https://spring.io/projects/spring-boot
[Maven.js]: https://img.shields.io/badge/Maven-3.8.7-blue?style=plastic&logo=apachemaven
[Maven-url]: https://maven.apache.org/download.cgi
[Docker.js]: https://img.shields.io/badge/Docker-blue?style=plastic&logo=docker
[Docker-url]: https://www.docker.com/
[Flywaydb.js]: https://img.shields.io/badge/Flyway-latest-red?style=plastic&logo=flyway
[Flywaydb-url]: https://flywaydb.org/documentation/usage/maven/
