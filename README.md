# Shop Application

Java RESTful application based on hexagonal architecture (ports & adapters), which provides high cohesion, low coupling, and allows for Behaviour Driven Development that has:
- allows for most tests to be run in milliseconds (unit tests without IO)
- while at the same time not falling into the trap of testing INTERNALS of a module (no test-per-class mistake)
- tests that focus on behaviour of each module (refactoring does not require changing test)
- just enough intergration/acceptance tests with focus on performance (minimum waiting for tests to pass)
- tests that describe requirements (living documentation)
- modules that have high cohesion (everything hidden except for APIs) and low coupling (modules connected via their APIs
- easy to explain, understand and follow (@jakubnabrdalik)

It's completely functional online store with admin management panel.

## Technologies used:

- Spring (Boot2, MVC, Data, Security, Session)

- Hibernate, JPA

- MySQL

- Spock

- Redis

- Angular, Bootstrap, CSS
