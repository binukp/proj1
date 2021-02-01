<!DOCTYPE html>
<html lang="en">
<body>
  <h1>Test Project</h1>

  This is a test project to list,add,edit and delete persons and address assoicated with them

  Once the project is installed you can run this by

  mvn clean install spring-boot:run

  * The main index page of this app can browsed then by

  * http://localhost:8080/index

  * This will show the person list

  * The add icon below will add a person

  * The edit/delete person can be used to update and delete a person.

  * The edit person has option to view the address for the user

  * The address list has similar options for delete and update an address.

  * Deleting a person will delete the associated address as well.


  * A file db is used to save the date at ./data/userdb

  * The console for the db is at http://localhost:8080/h2-console/

  * username/password in properties file (sa/password)


  Tests:

  * mvn test is added for some unit tests. but not complete.

</body>
</html>
