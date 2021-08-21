# Restaurant-voting-system

## Task
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

## API Guide

**HTTP verbs**

| Verb | Usage |
| :---: | :---: |
| `GET` | Used to retrieve a resource |
| `POST` | Used to create a new resource |
| `PUT` | Used to update an existing resource |
| `DELETE` | Used to delete an existing resource |

**HTTP status codes**

| Status code | Usage |
| :---: | :---: |
| `200 OK` | The request completed successfully |
| `201 Created` | A new resource has been created successfully |
| `400 Bad Request` | The request was malformed. The response body will include an error providing further information |
| `403 Forbidden` | Authentication was provided, but the authenticated user is not permitted to perform the requested operation. |
| `404 Not Found` | The requested resource did not exist |
| `405 Method Not Allowed` | The requested resource does not support method |
| `409 Conflict` | The request could not be completed due to a conflict with the current state of the target resource |
| `500 Internal Server Error` | the server encountered an unexpected condition that prevented it from fulfilling the request |

### Requests example

**Get data for voting**

    $ curl 'http://localhost:8080/rest/restaurant/menu?date=2018-01-01' -i -u 'ivan@mail.ru:ivan_pass'
    
*Response body*

```json
[ {
  "id" : 100011,
  "name" : "Китайский",
  "menuId" : 100015,
  "meals" : [ {
    "id" : 100004,
    "name" : "хлеб",
    "price" : 250
  }, {
    "id" : 100008,
    "name" : "мясо",
    "price" : 125
  } ]
}, {
  "id" : 100010,
  "name" : "Морской",
  "menuId" : 100013,
  "meals" : [ {
    "id" : 100005,
    "name" : "вода",
    "price" : 150
  }, {
    "id" : 100004,
    "name" : "хлеб",
    "price" : 100
  } ]
}, {
  "id" : 100012,
  "name" : "Французкий",
  "menuId" : null,
  "meals" : [ ]
} ]
```

**Vote for restaurant**

    $ curl 'http://localhost:8080/rest/restaurant/menu/100015/voting' -i -u 'ivan@mail.ru:ivan_pass' -X POST

### Resources

#### Restaurant

*Create*

    $ curl 'http://localhost:8080/rest/restaurant/' -i -u 'petr@gmail.com:petr_pass' -X POST \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "name" : "new_restaurant"
    }'
    
*Update*

    $ curl 'http://localhost:8080/rest/restaurant/100010' -i -u 'petr@gmail.com:petr_pass' -X PUT \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "id" : 100010,
      "name" : "new_restaurant_name"
    }'

*Get*

    $ curl 'http://localhost:8080/rest/restaurant/100010' -i -u 'ivan@mail.ru:ivan_pass'
    
*Get all*

    $ curl 'http://localhost:8080/rest/restaurant/' -i -u 'ivan@mail.ru:ivan_pass'
    
 *Get one with meals*
 
    $ curl 'http://localhost:8080/rest/restaurant/100010/menu?date=2018-01-01' -i -u 'ivan@mail.ru:ivan_pass'
 
 *Get all with meals*
 
    $ curl 'http://localhost:8080/rest/restaurant/menu?date=2018-01-01' -i -u 'ivan@mail.ru:ivan_pass'
 
 *Get one with votes*
 
    $ curl 'http://localhost:8080/rest/restaurant/100010/vote?date=2018-01-01' -i -u 'ivan@mail.ru:ivan_pass'
 
 *Get all with votes*

    $ curl 'http://localhost:8080/rest/restaurant/vote?date=2018-01-01' -i -u 'ivan@mail.ru:ivan_pass'

#### Menu

*Create*

    $ curl 'http://localhost:8080/rest/restaurant/100012/menu' -i -u 'petr@gmail.com:petr_pass' -X POST \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "id" : 0,
      "restaurantId" : 100012,
      "date" : "2018-01-01",
      "meals" : [ {
        "id" : 100005,
        "name" : "вода",
        "price" : 130
      }, {
        "id" : 100004,
        "name" : "хлеб",
        "price" : 100
      } ]
    }'
    
 *Update*
 
    $ curl 'http://localhost:8080/rest/restaurant/100012/menu' -i -u 'petr@gmail.com:petr_pass' -X PUT \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "id" : 100013,
      "restaurantId" : 100012,
      "date" : "2018-01-01",
      "meals" : [ {
        "id" : 100004,
        "name" : "хлеб",
        "price" : 777
      } ]
    }'

#### Meal

*Create*

    $ curl 'http://localhost:8080/rest/meal/' -i -u 'petr@gmail.com:petr_pass' -X POST \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "name" : "ананас"
    }'
    
*Update*

    $ curl 'http://localhost:8080/rest/meal/100004' -i -u 'petr@gmail.com:petr_pass' -X PUT \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "id" : 100004,
      "name" : "новое имя еды"
    }'
    
*Delete*

    $ curl 'http://localhost:8080/rest/meal/100004' -i -u 'petr@gmail.com:petr_pass' -X DELETE
    
*Get*

    $ curl 'http://localhost:8080/rest/meal/100004' -i -u 'petr@gmail.com:petr_pass'
    
*Get all*

    $ curl 'http://localhost:8080/rest/meal/' -i -u 'petr@gmail.com:petr_pass'
    
#### User

*Create*

    $ curl 'http://localhost:8080/rest/user/registration' -i -X POST \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "name" : "new_user",
      "email" : "test.mail@mail.ru",
      "password" : "pass123"
    }'    