# bankAccount
Bank account kata
Spring boot Application with H2 embedded database.

Compile & install : mvn clean install

Run : mvn spring-boot:run

H2
http://localhost:8080/h2/

GET : RETURN ACCOUNT
http://localhost:8080/bankAccount/{ID_ACCOUNT}

US 1:

Make a deposit in my account

POST : ADD NEW RECORD WITH TYPE DEPOSIT
http://localhost:8080/bankAccount/{ID_ACCOUNT}/record/add/deposit

BODY : {"amount":1000, "type":"DEPOSIT"}

US 2:

Make a withdrawal from my account

POST : ADD NEW RECORD WITH TYPE withdrawal
http://localhost:8080/bankAccount/{ID_ACCOUNT}/record/add/withdrawal

BODY : {"amount":1000, "type":"WITHDRAWAL"}

US 3:

See the history (operation, date, amount, balance) of my operations

GET : RETRIEVE ALL OPERATIONS
http://localhost:8080/bankAccount/{ID}/records
