# Task_Java_Tran_Vu_Quyet
Tasks with Java 


Good day! I would like to share instructions about how to look up my codes and review:

The section covered up all these following RESTful endpoints:

GET /tasks : Get a list of all tasks

POST /tasks : Create a new task

GET /tasks/{id} : Get a single task by ID

PUT /tasks/{id} : Update a task by ID

DELETE /tasks/{id} : Delete a task by ID

And I also use Spring Security for authorization and authentication user, currently add role check if required.

# Run Application Without Docker
- Config JAVA_HOME in both User and Sysyem Variables (make sure that JDK(Java Development Kit) and Eclipse Adoptimum installed)

- Add path to bin folder of parent folder Eclipse Adoptimum in System Variables

# Run Application With Docker in Container
About using Docker to run the application in container:

- Run command in the root project: docker compose -f docker-compose.dev.yml up --build

- This will look in the Dockerfile, create container, pull images and run with CMD. For running in MySQL Database, need to verify in file application.properties: name of db, user, password of both settinggs

in docker-compose-yml and application.properties share the same.
