# release-manager
A simple release manager to track of your deployments

# Data base
For quick boostrap, it is using H2 in memory database.

# Data Model
![alt text][logo]

[logo]: https://i.imgur.com/Eg8tKPE.png

# Deployment
- Perform a git checkout
- In the project root folder, execute the deploy.sh file
  - It will issue a `mvn clean install` followed by a `docker build ...` and `docker run -d ...`

# Documentation
Swagger documentation can be accessed at http://localhost:8080/api/swagger-ui.html

# Improvements
- Improve unit tests
- Create integration tests
- Use docker compose
- Improve documentation
