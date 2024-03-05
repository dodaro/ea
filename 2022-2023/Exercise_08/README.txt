Create a Spring Boot Application
Map the entities of the relational schema attached - then create the tables on an hsql database.
Create the repositories and the service layer (managing the transactions)
Create and manage as many queries:
a. find all account [query method OR query]
b. find all account by username and password [query method OR query]
c. find all account with creationDate between two date. The two dates are read as parameters [query method]
d. find all premiumAccount with a promotion, regionCode and point greatherThan (all read as parameter) order by
   username [query method]
e. find the bank with a name read as parameter. From this bank retrieve all teller without query the database
f. count all bank with location = 'Cosenza' and store the result into a variable numCosenzaBank into class Bank. The
    column numCosenzaBank must not be mapped to the database [query method]
g. find all contract of a customer [query method OR query]
h. create the method deleteAllCustomer(Long bankId) into BankDao that delete all the customer of the bank. The method
   must be transactional
i. find all the customer who have an age read as a parameter [specification]


To test you can create junit tests on the service layer, loading some object into database
