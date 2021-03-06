# liveseats
Simple liveseats microservice which does liveSeats status update operations connecting to MYSQL/Kafka docker containers

# RUN mysql as docker container - here just use docker-compose for both mysql&kafka
https://hub.docker.com/_/mysql
<br />$ docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=D@tabase123 -e MYSQL_DATABASE=liveseats -e MYSQL_USER=varun -e MYSQL_PASSWORD=D@tabase123 --name=mysql mysql:8.0.28

# RUN kafka as docker container
https://www.baeldung.com/ops/kafka-docker-setup
create a folder name kafka and add the docker-compose.yml and then open GIT BASH - execute below command
<br />$ docker-compose up -d

# RUN kafka as docker container - multi-node
create a folder name kafka and add the docker-compose-multinode.yml and then open GIT BASH - execute below command
<br />$ docker-compose -f docker-compose-multinode.yml up -d

# Create docker image of liveseats microservice and push to docker repository
$ mvn clean install
<br />$ docker push devopsvarun/liveseats:0.0.1-SNAPSHOT

# RUN liveseats microservice as docker container - singlenode
$ docker run --network=kafka_default -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" -e MYSQL_HOSTNAME=mysql -e KAFKA_BOOTSTRAP_SERVERS=kafka:9092 --link=kafka --name=liveseats devopsvarun/liveseats:0.0.1-SNAPSHOT

# RUN liveseats microservice as docker container - multinode
$ docker run --network=kafka_default -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" -e MYSQL_HOSTNAME=mysql -e KAFKA_BOOTSTRAP_SERVERS=kafka-1:9092,kafka-2:9092 --link=kafka --name=liveseats devopsvarun/liveseats:0.0.1-SNAPSHOT

# EXECUTE dbscript - INSERT query
open sqldeveloper - new connection - mysql db - localhost:3306 - user: root/varun as we used while creation - login
open dbscript file, execute below queries:
use liveseats;
execute insert query to add data to table.

# EXECUTE Read/Update operations on postman
http://localhost:8080/liveSeatsStatus (GET/PUT)

# Swagger
http://localhost:8080/swagger-ui.html


