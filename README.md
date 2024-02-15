# Grades API

#### Grades API provides API to make store, search, delete operations easily.


## Techs
- Spring Boot 3
- Java 17
- Maven
- Spring Security
- Spring Validation
- Flyway
- PostgreSQL
- Lombok
- OpenAPI Generator
- QueryDSL

## Install
#### Clone repository to your machine
```
git clone https://github.com/hasangurbuzz/grades-api.git
```

#### Navigate to project root directory
```
cd grades-api
```

#### Compile to generate classes
```
mvn clean compile
```

### ! Make sure you have set [Environment Variables](#env-vars) !

#### Run project
```
mvn spring-boot:run
```


<h3 id="env-vars">Environment Variables</h3>

| Variable          	| Type     	| Required 	|
|-------------------	|----------	|----------	|
| POSTGRES_HOST     	| string   	| true     	|
| POSTGRES_PORT     	| number   	| true     	|
| POSTGRES_DB_NAME  	| string   	| true     	|
| POSTGRES_USERNAME 	| string   	| true     	|
| POSTGRES_PASSWORD 	| string   	| true     	|
| ALLOWED_ORIGINS   	| string[] 	| false    	|

#### [Navigate Grades Application](https://github.com/hasangurbuzz/grades)
#### [See OpenAPI Documentation](https://github.com/hasangurbuzz/grades-api/blob/master/src/main/resources/openapi/openapi.yaml)