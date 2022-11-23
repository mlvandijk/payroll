# Building REST services with Spring
Link: https://spring.io/guides/tutorials/rest/

## Start application

### From the IDE
Run `PayrollApplication`.

### From the command line
Run `./mvnw clean spring-boot:run` (Spring Initializr uses maven wrapper).

Alternatively, to use your own installed Maven version, run `mvn clean spring-boot:run`.

## Working with the API

### Employees
To get all employees: `curl -v localhost:8080/employees`
To get one employee by id: `curl -v localhost:8080/employees/1`

To create a new employee: `curl -X POST localhost:8080/employees -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "gardener"}'`

To update an employee: `curl -X PUT localhost:8080/employees/3 -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'`

To delete an employee: `curl -X DELETE localhost:8080/employees/3`

### Orders
`curl -v http://localhost:8080/orders`
`curl -v -X DELETE http://localhost:8080/orders/4/cancel`
`curl -v -X PUT localhost:8080/orders/4/complete`


## Curl prettier
Use the following to make your curl prettier:
# The indicated part pipes the output to json_pp and asks it to make your JSON pretty. (Or use whatever tool you like!)
#                                  v------------------v
`curl -v localhost:8080/employees/1 | json_pp`

More tips: https://stackoverflow.com/questions/27238411/display-curl-output-in-readable-json-format-in-unix-shell-script