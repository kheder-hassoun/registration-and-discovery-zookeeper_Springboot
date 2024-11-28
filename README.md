
# ZooKeeper Leader Election Spring Boot Application

This project demonstrates a **ZooKeeper-based leader election** mechanism implemented in **Spring Boot**. The application allows multiple distributed services to coordinate and elect a leader using ZooKeeper, ensuring high availability and fault tolerance.

## Project Structure

The project is organized as follows:

``` css
registration-and-discovery-zookeeper_Springboot
├── .idea/                     # IDE configuration files
├── .mvn/                      # Maven wrapper files
├── logs/                      # Log files generated by the application
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── me.zookeeper.leader_election/
│   │   │       ├── old/                    # (Optional) Previous non-Spring Boot classes
│   │   │       ├── Application.java        # Entry point for custom logic
│   │   │       ├── LeaderElection.java     # Leader election implementation
│   │   │       ├── OnElectionAction.java   # Callback action on leader election
│   │   │       ├── OnElectionCallback.java # Callback interface for election
│   │   │       ├── ServiceRegistry.java    # Service registry for nodes
│   │   │       ├── StatusController.java   # REST API to check the leader's status
│   │   │       ├── ZookeeperConfig.java    # Configuration for connecting to ZooKeeper
│   │   │       └── ZookeeperLeaderElectionApplication.java
│   │   └── resources/
│   │       ├── application.properties      # Application configuration
│   │       └── logback.xml                 # Logging configuration
├── pom.xml                   # Maven dependencies
└── README.md                 # Documentation
```

## Features

- **Leader Election:** Implements a ZooKeeper-based mechanism to elect a leader among distributed services.
- **Service Registry:** Nodes are dynamically registered with ZooKeeper.
- **Spring Boot Integration:** Utilizes Spring Boot for configuration and dependency injection.
- **REST API:** Exposes APIs to check the leader's status ⚠ and discover services.
- **Logging:** Configurable log levels with Logback.

## Prerequisites

1. **Java:** Ensure you have JDK 17 or higher installed.
2. **Maven:** Build and dependency management.
3. **ZooKeeper:** A running ZooKeeper instance is required for the application to function.

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd registration-and-discovery-zookeeper_Springboot
```

### 2. Configure ZooKeeper

Update the ZooKeeper connection details in `src/main/resources/application.properties`:

```properties
zookeeper.connect-string=localhost:2181
zookeeper.election-path=/election
server.port=8080
```

### 3. Build the Application

Run the following command to build the application using Maven:

```bash
mvn clean install
```

### 4. Run the Application

Start the application:

```bash
java -jar target/leader_election-0.0.1-SNAPSHOT.jar
```

### 5. Testing the Application

- **REST Endpoint:** Check the leader's status by accessing:

  ```bash
  curl http://localhost:8080/api/status
  ```
- **REST Endpoint:** Discover Services by accessing:

  ```bash
  curl http://localhost:8080/api/services 
  ```
- Run multiple instances of the application on different ports to observe leader election behavior.

## Key Classes

### 1. **LeaderElection.java**
Handles the leader election logic by interacting with ZooKeeper's ephemeral nodes.

### 2. **OnElectionAction.java**
Implements `OnElectionCallback` to define actions upon becoming the leader or follower.

### 3. **ZookeeperConfig.java**
Configures the ZooKeeper client and connection details.

### 4. **StatusController.java**
Exposes a REST API to report the leader's status.

### 5. **ServiceRegistry.java**
Manages the registration of nodes in ZooKeeper.

## Dependencies

The project uses the following dependencies:

- **Spring Boot Starter Web** - For RESTful APIs.
- **ZooKeeper Client** - For interacting with the ZooKeeper server.
- **Logback** - For logging.
- **Spring Boot Starter Test** - For unit testing.

Check `pom.xml` for the full list of dependencies.

## Logs

Logs are configured via `logback.xml` and stored in the `/logs` directory. You can adjust logging levels and appenders as needed.

## Troubleshooting

### Common Issues

1. **ZooKeeper not running:**
   Ensure ZooKeeper is running on the host and accessible on the configured port.

2. **Node creation errors:**
   Check if the `/election` node exists in ZooKeeper. Create it manually if necessary:
   ```bash
   zkCli.sh
   create /election ""
   ```

3. **Port conflicts:**
   If running multiple instances, set different ports in `application.properties`.

