# liveseats
Simple liveseats microservice which does liveSeats status update operations connecting to MYSQL/Kafka docker container

# RUN mysql as docker container - here just use docker-compose for both mysql&kafka
https://hub.docker.com/_/mysql
<br />$ docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=D@tabase123 -e MYSQL_DATABASE=liveseats -e MYSQL_USER=varun -e MYSQL_PASSWORD=D@tabase123 --name=mysql mysql:8.0.27

# RUN kafka as docker container
https://www.baeldung.com/ops/kafka-docker-setup
<br />$ docker-compose up -d

# Create docker image of liveseats microservice and push to docker repository
$ mvn clean install
<br />$ docker push devopsvarun/liveseats:0.0.1-SNAPSHOT

# RUN liveseats microservice as docker container
$ docker run --network=kafka_default -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" -e MYSQL_HOSTNAME=mysql -e KAFKA_BOOTSTRAP_SERVERS=kafka --link=kafka --name=liveseats devopsvarun/liveseats:0.0.1-SNAPSHOT

# EXECUTE Read/Update operations on postman
http://localhost:8080/liveSeatsStatus (GET/PUT)



