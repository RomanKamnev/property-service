Kafka start
--If errors with previous images and volumes 
(docker-compose down --rmi all --volumes --remove-orphans)
- docker-compose up
- docker ps
- docker exec -it b29d1024340c /bin/sh
- cd bin/opt/kafka_2.13-2.8.1
- kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic customers

-- Read messages from Kafka
./bin/kafka-console-consumer.sh --bootstrap-server 0.0.0.0:9092 --topic customers --from-beginning

-- JWT usage
current user/pass - admin/admin
Call GET http://localhost:8080/api/auth/get-token
with JSON:
{
"username": "admin",
"password": "admin"
}

Get token like:
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjoiUk9MRV9BRE1JTixST0xFX1VTRVIiLCJpYXQiOjE3Mzg0OTk4MzAsImV4cCI6MTczODUwMjgzMH0.gdStqe4KDxmy1g6Xn8UC6CxXW4rKf9d0Qfp8WOQ5PL8
{
"header" : {
"alg" : "HS256"
},
"payload" : {
"sub" : "admin",
"roles" : "ROLE_ADMIN,ROLE_USER",
"iat" : 1738499830,
"exp" : 1738502830
},
"signature" : "gdStqe4KDxmy1g6Xn8UC6CxXW4rKf9d0Qfp8WOQ5PL8"
}

Use Postman Bearer Token(Not JWT Bearer for testing)

For git repositories use context: github-property
