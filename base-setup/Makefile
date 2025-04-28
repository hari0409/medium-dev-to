.PHONY: run_spring db stop_db clean_db run_all
# Default target: runs the Spring Boot application

run_spring:
	mvn spring-boot:run

# Target to start the MySQL Docker container
db:
	docker run -d \
  		--name springboot-mysql \
		-e MYSQL_ROOT_PASSWORD=admin \
		-e MYSQL_USER=admin \
		-e MYSQL_PASSWORD=admin \
		-p 3306:3306 \
		-v ./data:/var/lib/mysql \
		mysql:latest

# Target to stop the Docker container
stop_db:
	docker stop springboot-mysql && docker rm springboot-mysql

# Target to clean up database data
clean_db:
	rm -rf ./data/*
# Combined target to start both the database and Spring Boot
run_all: db run_spring