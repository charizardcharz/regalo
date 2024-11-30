<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MPL License][license-shield]][license-url]

⚠️ WARNING -- This application is in development and not production ready. Things may break. Expect data loss between versions due to database schema changes.

<!-- PROJECT LOGO -->
<div align="center">
<h3 align="center">Regalo</h3>

  <p align="center">
    A basic wishlisting app written in Java
    <br />
    <br />
    <a href="https://github.com/charizardcharz/regalo/issues">Report Bug</a>
    ·
    <a href="https://github.com/charizardcharz/regalo/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

<!--[![Product Name Screen Shot][product-screenshot]](https://example.com)  -->

Regalo is a basic wishlisting app built using Java Spring Boot with simplicity in mind.
Wishlists are stored in plaintext in a sqlite database.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started
Deployment via docker is highly recommended.

### Docker Deploymment

The latest docker image is available at `ghcr.io/charizardcharz/regalo:latest`

### JAR deployemnt

You must have sqlite 3.47+ installed before running the application.

A jar is not provided, however it can be built folowing the build instructions below.

To run the application, ensure that you correctly set the path where you would like to store the database in application.yml before building.

You can also set the environment variable `SRPING_DATASOUCE_URL=jdbc:sqlite:/path/to/database.sqlite3` to override the preset database location.


<!-- USAGE EXAMPLES -->
## Usage

Example docker-compose
```yaml
services:
  regalo:
    image: ghcr.io/charizardcharz/regalo:latest
    container_name: regalo
    ports:
      - 6969:6969
    restart: unless-stopped
    volumes:
      - PATH/TO/CONFIG:/config
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- BUILDING -->
## Build Dependencies

Requires Java 21+ and Maven 3+

Build a jar using Maven `mvn clean package`

Build a docker image `docker build -t regalo .`

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Mozilla Public License Version 2.0

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Albert Ybanez

Project Link: [https://github.com/charizardcharz/regalo](https://github.com/charizardcharz/regalo)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/charizardcharz/regalo.svg?style=for-the-badge
[contributors-url]: https://github.com/charizardcharz/regalo/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/charizardcharz/regalo.svg?style=for-the-badge
[forks-url]: https://github.com/charizardcharz/regalo/network/members
[stars-shield]: https://img.shields.io/github/stars/charizardcharz/regalo.svg?style=for-the-badge
[stars-url]: https://github.com/charizardcharz/regalo/stargazers
[issues-shield]: https://img.shields.io/github/issues/charizardcharz/regalo.svg?style=for-the-badge
[issues-url]: https://github.com/charizardcharz/regalo/issues
[license-shield]: https://img.shields.io/github/license/charizardcharz/regalo.svg?style=for-the-badge
[license-url]: https://github.com/charizardcharz/regalo/blob/master/LICENCE
