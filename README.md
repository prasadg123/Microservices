# Microservices

Author:Prasad Gorrepati

Description: This repository contains small 5 small microservices

student-service: This service contains all the details of the student and their corresponding school information.It exposes rest endpoints to add and get the student details.This service checks whether all the incoming requests are authenticated or not by invoking call back-url and passing the token received.This service implements fallback pattern if request gets timeout.

school-service: This service pulls the students information based on the school name and gives the response in json format. This service checks whether all the incoming requests are authenticated or not by invoking call back-url and passing the token received.

Authentication-service: This service implements OAuth2 to authenticate the request coming to all the services via API Gateway(gateway-service). It authenticates all the requets coming from m the registered client and generates the token based on the scope of the request.

Gateway-service: This service acts as API Gateway for all the request coming in from the clients to the producer services. This service communicates with the service discovery(eureka-service) to get all the services registered in the regestry to cache the service id's in order to provide client side loadbalancing of the Gateway-service to work even service discovery is down.

Eureka-service: This acts as a service dicovery, where all the services registers themselves to the eureka-service and polls the status os the service for every particular interval of time. Also, the services registered with eureka-service use client side loadbalancing using
Netflix Ribbon libraries to communicate the api's even if there are some network glithes in conncting to service discovery.

I am using Spring Cloud/Netflix Eureka as service discovery and registering all the services using Netflix Eureka client.
For client side loadbalancing Netflix ribbon libraries are being used.
For API Gateway,I am using Netflix Zuul services with ribbon for clientside load balancing. For implementing circuit breaker pattern and fallout mechanism,Spring cloud/Netflix Hystrix is being used.





