# PetStore
Pet Store Spring Boot Application that can store/manage pets. 

## Setting up and running the application:
 - Clone or download the application.
 - Run Docker Desktop on your PC.
 - Run the docker-compose.yml file
 - Conntect to the database (make sure to have the postgres service ready to be initialized)
 - Start the application from PetStoreApplication or the PetStoreApplication saved configuration.
 
## Commands:
### With POSTMAN:
- create-users localhost:/9090/create/users POST
- create-pets  localhost:/9090/create/pets POST
- buy localhost:9090/users/buy POST
- list-users localhost:/9090/users GET
- list-pets localhost:/9090/pets GET
- history-log localhost:/9090/history-log
### With GraphQL:
Go to localhost:9090/graphiql
- create-users mutation createUsers: [User]
- create-pets  mutation createPets: [Pet]
- buy mutation buy: [User]
- list-users query listUsers: [User]
- list-pets query listPets: [Pet]
- history-log query historyLog
    
