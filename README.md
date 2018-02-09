[![Build Status](https://travis-ci.org/bbarbs/spring-boot-jpa-rest.svg?branch=master)](https://travis-ci.org/bbarbs/spring-boot-jpa-rest)

## Getting Started
This project is sample implementation of Spring REST and uses Spring DATA(Jpa). It uses Swagger to expose the rest service and Mapstruct to mapping data transfer object to entity and vice versa.

## Features
* Swagger
* [Mapstruct](http://mapstruct.org/)
* Jpa

## Mapstruct configuration
To generate the mapper implementation do the following.
>gradle clean and gradle build

You can check the generated code in:
* For Intellij
```
- build
 - generated
  - source
   - apt
    - main
     - com.jparest.mapper
```
Note: Everytime changes is made in mapper run the gradle clean and gradle build to reflect the changes. 

## Swagger
* To view the swagger-ui it can be accessed in http://localhost:8080/swagger-ui.html.
* For api-docs in be viewed in http://localhost:8080/v2/api-docs.

## Endpoints
* Address
```
PATCH /v1/api/addresses/{addressId}
PUT   /v1/api/addresses/{addressId}
GET   /v1/api/customers/{customerId}/addresses
POST  /v1/api/customers/{customerId}/addresses
```
* Credentials
```
PATCH /v1/api/credentials/{credentialId}
PUT   /v1/api/credentials/{credentialId}
GET   /v1/api/customers/{customerId}/credentials
POST  /v1/api/customers/{customerId}/credentials
```
* Customer
```
PATCH  /v1/api/customer/{customerId}
GET    /v1/api/customers
POST   /v1/api/customers
DELETE /v1/api/customers/{customerId}
GET    /v1/api/customers/{customerId}
PUT    /v1/api/customers/{customerId}
```
* Item
```
GET    /v1/api/items
POST   /v1/api/items
DELETE /v1/api/items/{itemId}
GET    /v1/api/items/{itemId}
PATCH  /v1/api/items/{itemId}
PUT    /v1/api/items/{itemId}
```
* Order
```
GET    /v1/api/customers/{customerId}/orders
POST   /v1/api/customers/{customerId}/orders
GET    /v1/api/orders
DELETE /v1/api/orders/{orderId}
GET    /v1/api/orders/{orderId}
PATCH  /v1/api/orders/{orderId}
PUT    /v1/api/orders/{orderId}
GET    /v1/api/orders/{orderId}/items
GET    /v1/api/orders{?status}
```
