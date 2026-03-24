Agent Service:

1) Description:

	The Agent Service is a standalone microservice responsible for managing agent data within the Alturion Policy Systems. It exposes REST APIs for agent creation, retrieval, and agent-specific operations, including aggregation of policy data associated with agents. It is designed using a layered architecture with standardized API responses and centralized exception handling to ensure maintainability and reliability.
	
2) Responsibilities:

	• Manage agent data and related operations
	
	• Provide aggregated data for agent dashboards
	
	• Retrieve policy owners associated with agents
	
	• Fetch policy information via inter-service communication
	
	• Support paginated retrieval of policy details for dashboards

3) Tech Stack:
 
	• Java 17
	
	• Maven
	
	• Spring Boot
	
	• Spring Data JPA 
	
	• SQL
	
	• REST API
	
	• RestTemplate (inter-service communication)
	
	• Global Exception Handling

4) Project Structure:

	• controller   → Exposes REST endpoints and handles HTTP requests/responses
	
	• service      → Contains core business logic
	
	• repository   → Handles database interactions using Spring Data JPA
	
	• domain       → Represents JPA entities mapped to database tables
	
	• dto          → Defines request and response payloads for APIs
	
	• client       → Handles inter-service communication using RestTemplate
	
	• exception    → Contains custom exceptions and global exception handling
	
	• config       → Includes application-level configurations & interceptor

5) API Endpoints:

	• POST  /api/agent/create
	
	• GET   /api/agent/{agentId}/{licenseNumber}
	
	• PATCH /api/agent/{agentId}/{licenseNumber}/deactivate 
	
	• GET   /api/agent/{agentId}/{licenseNumber}/dashboard
	
	• GET   /api/agent/{agentId}/{licenseNumber}/policies

6) Configuration:

	• Environment-specific properties are managed via application.properties which includes:
	
		• Database configurations
		 
		• Service URLs for inter-service communication

