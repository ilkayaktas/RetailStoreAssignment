## Assessment Goals:
- Low Level Design
- Software Development Practices
- Hands-on Programming

### Technologies & Technical Activities:
- Maven Project
- Java
- Spring boot
- Spring Security
- Hibernate JPA
- Restful APIs
- Docker
- Use MongoDB for persistence
- Deliver docker-compose file with all required services
- Unit testing using Mockito
  - Unit tests to achieve good code coverage, good use of Mocking
- Object oriented programming approach.  
  - provide a high level UML class diagram of all the key classes in your solution. This can either be on paper or using a tool (e.g. Draw io)
- Code to be generic and simple
- Leverage today's best coding practices
- Clear README.md that explains how the code and the test can be run and how the coverage reports can be generated.
- Create a GitHub repository, ensure the name is generic and doesn’t have any company names. Commit your code to the GitHub repository and share the link with us.

## Business Requirements:

### Provide logic for a Retail Store Discounts

On a retail website, the following discounts apply:
1. If the user is an employee of the store, he gets a 30% discount.
2. If the user is an affiliate of the store, he gets a 10% discount.
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.

Write a program with Restful APIs, such that given a bill, it finds the net payable amount.

Please note the stress is on object oriented approach and test coverage. User interface is not required.


### Bonus Activities
- Create scripts, e.g. Maven, etc. to:
  - Build the project from the command-line
  - Run static code analysis such as linting or jacoco o Run unit tests and code coverage
- SonarQube report for the code showing its quality summary
