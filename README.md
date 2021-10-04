# POC

Customer Model

Id – Integer – Id of Customer
CustomerName – String
Phone - Integer

GET

/customers

Get all customers as JSON data.

GET

/customers/{id}

Get customer details by id as JSON data.

POST

/customers/

Create customer. Input data will be JSON.

Validations:
1. Customer Id not null and Integer and min length is 5.

2. Customer Name not null, minimum length is 8.

3. Phone is optional, if given minimum length is 10.

PUT

/customers/

Update existing customer with JSON as input.

Validations:
1. Customer Id not null and Integer and min length is 5.

Customer Id should not be updated in Database.

2. Customer Name not null, minimum length is 8.

3. Phone is optional, if given minimum length is 10.

DELETE

/customers/{id}

Delete customer of given id.

 
