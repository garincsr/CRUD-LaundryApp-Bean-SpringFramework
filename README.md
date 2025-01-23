# CRUD Application for Customer, Product, and Transactions

This is a Spring Framework-based CRUD application that manages customers, products, and transactions. It demonstrates the use of both XML-based and annotation-based dependency injection configurations. The project is designed to showcase the flexibility of Spring Framework in handling various configurations while implementing common CRUD operations. The application also integrates with PostgreSQL using JPA and Hibernate to create and manage database tables.

---

## Features

- **Customer Management**
  - Create, Read, Update, Delete (CRUD) operations for customers.

- **Product Management**
  - Create, Read, Update, Delete (CRUD) operations for products.

- **Transaction Management**
  - Create and view transactions between customers and products.

- **Configuration**
  - XML-based configuration (`beans.xml`)
  - Annotation-based configuration (`@Configuration` in AppConfig.java)

- **Database Integration**
  - PostgreSQL database integration
  - JPA and Hibernate for ORM (Object-Relational Mapping)

---

## Prerequisites

Ensure you have the following installed:

- **Java 8+**
- **Maven**
- **PostgreSQL**
- **Spring Framework** (5.x or higher recommended)

---

## Project Structure

```
project-root/
|-- src/
|   |-- main/
|       |-- java/
|       |   |-- com.example/
|       |       |-- config/          # Annotation-based configuration
|       |       |-- console/         # Console-based UI handlers
|       |       |-- model/           # JPA entities for Customer, Product, Transaction, and TransactionDetail
|       |       |-- repository/      # Repositories for database access
|       |       |-- service/         # Business logic for CRUD operations
|       |-- resources/
|           |-- beans.xml           # XML-based configuration
|           |-- application.properties # Database configuration
|-- pom.xml                        # Maven dependencies
```

---

## Setup and Run

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name
   ```

2. Configure the PostgreSQL database in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

5. Access the application (if using REST endpoints):

   - API endpoints can be tested using Postman or any REST client.

---

## Configuration Details

### XML-based Configuration (`beans.xml`)

The `beans.xml` file defines beans and their dependencies using Spring's XML schema. For example:

```xml
<bean id="jpaUtils" class="com.enigmacamp.utils.JpaUtils"/>
<bean id="entityManager" factory-method="getEntityManager" class="com.enigmacamp.utils.JpaUtils"/>
<bean id="customerRepository" class="com.enigmacamp.repository.CustomerRepository">
    <constructor-arg ref="entityManager"/>
</bean>
```

### Annotation-based Configuration (`AppConfig.java`)

The `AppConfig.java` class uses the `@Configuration` and `@Bean` annotations to define beans:

```java
@Configuration
public class AppConfig {

    @Bean
    public JpaUtils jpaUtils() {
        return new JpaUtils();
    }

    @Bean
    public EntityManager entityManager() {
        return JpaUtils.getEntityManager();
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepository(entityManager());
    }

    @Bean
    public ProductServiceImpl productService() {
        return new ProductServiceImpl(productRepository());
    }
}
```

---

## Database Tables

### Customers Table
- Stores customer information such as name, email, and contact details.

### Products Table
- Stores product information such as name, price, and description.

### Transactions Table
- Stores transaction records linking customers and products.

### Transaction Details Table
- Stores detailed information about the products involved in a transaction.

---

## API Endpoints

### Customer Endpoints
- **Create Customer**: `POST /api/customers`
- **Get All Customers**: `GET /api/customers`
- **Get Customer by ID**: `GET /api/customers/{id}`
- **Update Customer**: `PUT /api/customers/{id}`
- **Delete Customer**: `DELETE /api/customers/{id}`

### Product Endpoints
- **Create Product**: `POST /api/products`
- **Get All Products**: `GET /api/products`
- **Get Product by ID**: `GET /api/products/{id}`
- **Update Product**: `PUT /api/products/{id}`
- **Delete Product**: `DELETE /api/products/{id}`

### Transaction Endpoints
- **Create Transaction**: `POST /api/transactions`
- **Get All Transactions**: `GET /api/transactions`

---

## Technologies Used

- **Spring Framework** (Core, Context, MVC, Data JPA)
- **Java**
- **Maven**
- **PostgreSQL**
- **Hibernate** (ORM)

---

## Future Enhancements

- Add user authentication and authorization (e.g., using Spring Security).
- Implement pagination and sorting for APIs.
- Add unit tests and integration tests.
- Enhance logging for better debugging.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Author

[Garin Caesar](https://github.com/garincsr)

