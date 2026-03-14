Agent Service:

Description:

The Agent Service is a standalone microservice responsible for managing insurance agent information and providing agent-centric operations within Alturion Policy Systems. It exposes REST APIs for agent creation, retrieval of agent details, and aggregation of policy data associated with agents. The service follows microservice architecture principles with standardized API responses, global exception handling, and a clear separation between controller, service, repository, and integration layers.

The service collaborates with:

->Policy Owner Service for retrieving policy owners mapped under an agent

->Policy Info Service for retrieving policy details and policy statistics associated with the agent’s owners

Responsibilities of this service include Managing agent information, Providing aggregated dashboard data for agents, Retrieving policy owners mapped under agents, Fetching policy information through inter-service communication and Supports paginated retrieval of policy details for agent dashboards.

Tech Stack: Java 17, Maven, Spring Boot, Spring Data JPA, SQL, REST API, RestTemplate (inter-service communication), Global Exception Handling, Pagination and Sorting.

Project Structure:

controller   → REST API endpoints

service      → business logic

repository   → database access layer

entity       → JPA entity classes

dto          → request and response models

client       → communication with other microservices

exception    → custom exceptions and global exception handling

config       → application configurations

API Endpoints:

POST   - /create

GET    - /{agentId}/{licenseNumber}

PATCH  - /{agentId}/{licenseNumber}/deactivate 

GET    - /{agentId}/{licenseNumber}/dashboard

GET    - /{agentId}/{licenseNumber}/policies

These endpoints support agent creation, agent information retrieval, dashboard aggregation, and paginated retrieval of policy details associated with the agent.

