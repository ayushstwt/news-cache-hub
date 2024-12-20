# NewsCacheHub

**NewsCacheHub** is a Spring Boot-based microservices project that demonstrates how to connect two microservices using **Apache Kafka** and provides efficient news fetching and caching functionality using Redis.

## Project Architecture

This project follows a microservices architecture where:
- One microservice fetches news from a public news API.
- Another microservice handles caching the news in Redis for efficient access.
- Apache Kafka is used as the communication bridge between the microservices.

![image](https://github.com/user-attachments/assets/dda9d4f5-e4c9-4fe5-a0b3-9a548ab98e18)


## Features
- **News Fetching**: Retrieves news articles from a public API.
- **Caching**: Stores news articles in a Redis database to improve performance and reduce API calls.
- **Search by Date**: Users can search for news by the published date in the format `yyyy-MM-dd`.
- **Efficient Communication**: Uses Apache Kafka for reliable and scalable communication between microservices.
- **Smart Data Handling**: If news for a requested date is cached, it is served from Redis instantly. Otherwise, it fetches the news from the public API, stores it in Redis, and then serves it.

## Technologies Used
- **Spring Boot**: For developing microservices.
- **Apache Kafka**: For messaging and communication between microservices.
- **Redis**: For caching data.
- **Java**: Programming language.
- **Docker**: For containerization (optional).

## Prerequisites
- JDK 11 or higher
- Apache Kafka installed and running
- Redis installed and running
- Maven for dependency management

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/ayushstwt/news-cache-hub.git
cd news-cache-hub
```

### 2. Start Kafka and Redis
Ensure that Kafka and Redis are running on your system. You can use Docker to start them quickly:
```bash
docker run -d --name kafka -p 9092:9092 bitnami/kafka

docker run -d --name redis -p 6379:6379 redis
```

### 3. Configure Application Properties
Update the `application.properties` file in each microservice to include:
- Kafka configurations (e.g., `bootstrap.servers`)
- Redis configurations (e.g., `spring.redis.host`, `spring.redis.port`)

### 4. Build and Run the Services
Use Maven to build and start each microservice:
```bash
mvn clean install
mvn spring-boot:run
```

### 5. Test the Application
- Send requests to fetch news by date using a REST client (e.g., Postman).
- Verify that the data is being fetched from the public news API and cached in Redis.
- Use Kafka monitoring tools to observe the message flow between microservices.

## API Endpoints

### News Fetch Service
| Method | Endpoint                  | Description                |
|--------|---------------------------|----------------------------|
| GET    | `/news?date=yyyy-MM-dd`  | Fetch news by published date |

## How it Works
1. The client sends a request to fetch news for a specific date.
2. The News Fetch service checks if the data exists in Redis.
3. If the data is found in Redis, it is served directly.
4. If not, the service fetches the data from the public news API, stores it in Redis, and then serves it.
5. Apache Kafka facilitates communication between the services.

## Future Enhancements
- Add support for multiple public news APIs.
- Implement advanced search capabilities (e.g., by keyword or category).
- Containerize the entire application using Docker Compose.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Start fetching and caching news efficiently with **NewsCacheHub**! 🚀
