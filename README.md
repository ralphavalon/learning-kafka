# Learning Kafka

* Note: Running in the same Docker network ( `docker network create kafka-network` )
* Run `docker-compose up` to start Kafka containers
* After they started, create a topic running this: `docker exec -it kafka kafka-topics --create --topic Samples --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:32181`
* Then, you will join the topic as a producer running this: `docker exec -it kafka kafka-console-producer -broker-list kafka:9092 -topic Samples`
* You'll see a prompt like this: `>` , then everything you type and press Enter will send a message to the topic.