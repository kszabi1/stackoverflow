# AskMate OOP TW Project

## Story
It is time to connect your previous front end and newly acquired database knowledge, creating a full product.

Your next big task is to implement a crowdsourced Q&A site, similar to Stack Overflow. Use your PostgreSQL and Spring Boot knowledge.

## Learn
- How to write Spring Boot Web Applications
- The Repository Pattern
- How to connect a backend server to a frontend website

## Tasks
### All Questions page
Create a page to display all the questions.

Requirements:
- endpoint-exists: An endpoint with `api/question/all` url exists.
- get-all-questions-frontend: All questions coming from the endpoint are displayed on the frontend page.
- details-frontend: Title, creation date and the number of answers is displayed for each question.
- endpoint-uses-get-method: The endpoint uses the `GET` request method.
- data-is-sorted: The data is sorted by starting with the most recent one.

### Question detail page
Clicking on a question on the main page shows a details page for that question and it's related answers.

Requirements:
- endpoint-exists: An endpoint called `api/question/{id}` exists.
- endpoint-uses-get-method: The endpoint uses the `GET` request method.
- get-one-question-frontend: The data of the question and the related answers coming from the endpoint are displayed on a frontend page.

### Create new question
Create an option on the site to add a new question.

Requirements:
- frontend-shows: Input fields to type in the new question and a save button is displayed on the frontend.
- endpoint-exists: An endpoint called `api/question` exists.
- endpoint-uses-post-method: The endpoint uses the `POST` request method.
- attributes-exist: A question has at least a `title` and a `description`.
- endpoint-returns-id: The endpoint returns the `id` created for the new question entry.
- redirects: The page redirects to the details of the created question after saving.

### Create new answer
Create an option on the site to add a new answer for a question.

Requirements:
- frontend-shows: Input fields to type in the new answer for a question and a save button is displayed on the frontend.
- endpoint-exists: An endpoint called `api/answer/{question_id}` exists.
- endpoint-uses-post-method: The endpoint uses the `POST` request method.
- attributes-exist: An answer has at least an `id`, a `message`, a `question_id`, and a `submission_time`.
- endpoint-returns-id: The endpoint returns the `id` of the new answer entry.

### Delete a question
Create an option for the logged-in user to delete their question on the site.

Requirements:
- frontend-shows: Delete button is displayed for each question on the frontend.
- endpoint-exists: An endpoint called `api/question/{id}` exists.
- endpoint-uses-delete-method: The endpoint uses the `DELETE` request method.
- endpoint-deletes-question: The endpoint deletes an existing question.
- own-question: Only logged-in users can delete and only their questions.

### Delete an answer
Create an option for the logged-in user to delete their answer on the site.

Requirements:
- frontend-shows: A button is displayed to delete an answer on the site.
- endpoint-exists: An endpoint called `api/answer/{id}` exists.
- endpoint-uses-delete-method: The endpoint uses the `DELETE` request method.
- endpoint-deletes-answer: The endpoint deletes an existing answer.
- own-answer: Only logged-in users can delete and only their answers.

### Register a user
Create an option to register a new user for the site.

Requirements:
- frontend-shows: Input fields and a button is displayed on the site to register a user.
- endpoint-exists: An endpoint called `api/user` exists. 
- endpoint-uses-post-method: The endpoint uses the `POST` request method.
- attributes-exist: At least an `id`, a `username`, and a `registration_time` is stored about a user.

### Log in a user
Create an option to log in an existing user.

Requirements:
- frontend-shows: Input fields and a button is displayed on the site to log in an existing user.
- endpoint-exists: An endpoint called `api/user/login` exists.
- endpoint-uses-post-method: The endpoint uses the `POST` request method.
- endpoint-credentials: The endpoint accepts a username and a password.
- endpoint-restrictions: It is only possible to ask or answer a question when logged in.

### Log out a user
Create an option to log out a logged-in user.

Requirements:
- frontend-shows: A logout button is displayed on the site for a logged-in user.
- endpoint-exists: An endpoint called `api/user/logout` exists.
- endpoint-uses-post-method: The endpoint uses the `POST` request method.
- endpoint-logs-out-user: The endpoint logs out the logged-in user.

### Write unit tests
Cover the application with unit tests.

Requirements:
- unit-tests-exist: The service layer of the application is covered with unit tests.


### Optional: Accept an answer 
Create an option to mark an answer as accepted for the user who created the question.

Requirements:
- frontend-shows: A button to accept the answer is displayed on the site.
- frontend-shows-accepted-answer: The accepted answer is marked on the site.
- endpoint-exists: An endpoint called `api/answer/accept/{id}` exists.
- endpoint-uses-patch-method: The endpoint uses the `PATCH` request method.
- endpoint-restrictions: It is only possible to accept an answer when the logged-in user is the same one who asked the question.

### Optional: Search:
Create an option to search for a specific phrase among the data.

Requirements:
- frontend-shows: Input field and a button to search for a phrase is displayed on the site.
- endpoint-exists: An endpoint called `api/search/{search-phrase}` exists.
- endpoint-uses-get-method: The endpoint uses the `GET` request method.
- questions-contain-search-phrase: The endpoint returns questions for which the title or description contains the searched phrase.
- answers-contain-search-phrase: The endpoint also returns questions which have answers for which the message contains the searched phrase.
- frontend-shows-results: The result of the search is displayed on the site.

### Optional: Edit a question
Create an option to edit a question by its `id`.

Requirements:
- frontend-shows: A button to edit a question is displayed on the site.
- endpoint-exists: An endpoint called `api/question/{id}` exists.
- endpoint-uses-put-method: The endpoint uses the `PUT` request method.
- endpoint-restrictions: It is only possible to update a question when the logged-in user is the same one who asked the question.

### Optional: Edit an answer
Create an option to edit an answer.

Requirements:
- frontend-shows: A button to edit an answer is displayed on the site.
- endpoint-exists: An endpoint called `api/answer/{id}` exists.
- endpoint-uses-put-method: The endpoint uses the `PUT` request method.
- endpoint-restrictions: It is only possible to update an answer when the logged-in user is the same one who wrote the answer.

### Optional: Add tags to a question
Create an option to add a tag to a question.

Requirements:
- frontend-shows: The tags for a question is displayed on the site.
- endpoint-exists: An endpoint called `api/tag` exists.
- endpoint-uses-post-method: The endpoint uses the `POST` request method.
- attributes-exist: A tag has at least an `id` and a `name`.
- endpoint-restrictions: It is only possible to add a tag to a question when logged in.
- tags-returned-with-questions: The `[GET] api/question` endpoint returns all questions, their answers, and the related tags as well.

### Optional: Retrieve the most active users
Create an option to retrieve the 3 most active users from the database.

Requirements:
- frontend-shows: The result of the most active user search is displayed on the site.
- endpoint-exists: An endpoint called `api/user/most-active` exists.
- endpoint-uses-get-method: The endpoint uses the `GET` request method.
- endpoint-returns-most-active-users: The endpoint returns the 3 most active users (who have the most questions and/or answers).

## General Requirements
- use-postgresql: The application uses a PostgreSQL database.
- use-repository-pattern: The application uses the Repository pattern.
- follow-solid-oop: The design follows the SOLID, OOP, and clean code principles.

## Hints
- Stick to Model View Controller layers to increase the flexibility and testability of your code. (Frontend doesn't do calculations, only displays the data, SQL handles the data manipulation)
- Do the base data features first, add user management later, and extend already existing ones if necessary.
- Focus on the Java & SQL parts and do minimal frontend as it is not the goal of this project.
- Vanilla Javascript and HTML are suggested (not mandatory ) for frontend, anything more complicated, like frameworks, is not necessary.
- If you run into Spring errors, ask mentors for help instead of spending a lot of time fixing them, because the focus of the task is not to get confident with Spring yet. You will have time to learn Spring in the next module.
- Distribute the tasks among teammates with the priority of everyone getting at least some backend tasks.



# Help

#### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.0/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.0/reference/htmlsingle/#web)

#### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Background materials
- :book-open: [The Model View Controller Pattern](https://www.freecodecamp.org/news/the-model-view-controller-pattern-mvc-architecture-and-frameworks-explained/)
- :video: [Repository Pattern](https://youtu.be/x6C20zhZHw8)
- :book-open: [What Is Swagger?](https://swagger.io/docs/specification/2-0/what-is-swagger/)
- :book-open: [Environment variables in Spring Boot](https://www.baeldung.com/spring-boot-properties-env-variables)
- :book-open: [Spring ResponseEntity](https://www.baeldung.com/spring-response-entity)
- :book-open: [Thymeleaf: Standard Expression Syntax (in case you don't want to use frontend framework)](https://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html#standard-expression-syntax)
