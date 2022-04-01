># Excute step

**tool:** `docker-compose`

**Description:** run `[docker-compose up -d]` in root directory and make sure port 8080 is not used by other application.

># API document

**URL:** `/books`

**Type:** `POST`

**Content-Type:** `application/json; charset=utf-8`

**Description:** `create book data`

**Body-parameters:**

Parameter|Type|Description|Required
---|---|---|---
name|string|Book's name.|true
author|string|Book's author.|true
translator|string|Book's translator.|true
isbn|string|Book's ISBN.|true
publisher|string|Book's publisher.|true
publicationDate|string|Book's publication date.|true
price|int32|Book's price.|true

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -i /books --data '{
  "name": "branda.boyer",
  "author": "Annette Brown",
  "translator": "ben smith",
  "isbn": "978-3-16-148410-0",
  "publisher": "big book",
  "publicationDate": "2022-03-21",
  "price": 830
}'
```

**Response-example:**
```
{
  "message": "success"
}
```

#
##
**URL:** `/books/{id}`

**Type:** `PUT`

**Content-Type:** `application/json; charset=utf-8`

**Description:** `update book data by id`

**Path-parameters:**

Parameter|Type|Description|Required
---|---|---|---
id|int64|Book's id|true


**Body-parameters:**

Parameter|Type|Description|Required
---|---|---|---
name|string|Book's name.|false
author|string|Book's author.|false
translator|string|Book's translator.|false
isbn|string|Book's ISBN.|false
publisher|string|Book's publisher.|false
publicationDate|string|Book's publication date.|false
price|int32|Book's price.|false

**Request-example:**
```
curl -X PUT -H 'Content-Type: application/json; charset=utf-8' -i /books/805 --data '{
  "name": "branda.boyer",
  "author": "Annette Brown",
  "translator": "ben smith",
  "isbn": "978-3-16-148410-0",
  "publisher": "big book",
  "publicationDate": "2022-03-21",
  "price": 830
}'
```

**Response-example:**
```
{
  "message": "success"
}
```

#
##
**URL:** `/books/{id}`

**Type:** `DELETE`

**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** `delete book by id`

**Path-parameters:**

Parameter|Type|Description|Required
---|---|---|---
id|int64|No comments found.|true



**Request-example:**
```
curl -X DELETE -i /books/976
```

**Response-example:**
```
{
  "message": "success"
}
```

#
##
**URL:** `/books`

**Type:** `GET`

**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** get all books

**Request-example:**
```
curl -X GET -i /books
```
**Response-fields:**

Field | Type|Description
---|---|---
name|string|Book's name.
author|string|Book's author.
translator|string|Book's translator.
isbn|string|Book's ISBN.
publisher|string|Book's publisher.
publicationDate|string|Book's publication date.
price|int32|Book's price.

**Response-example:**
```
[
  {
    "id": 922,
    "name": "branda.boyer",
    "author": "Annette Brown",
    "translator": "ben smith",
    "isbn": "978-3-16-148410-0",
    "publisher": "big book",
    "publicationDate": "1992-03-21",
    "price": 830
  },
  {
    "id": 999,
    "name": "Big Bang",
    "author": "Annette Brown",
    "translator": "Sonice Guy",
    "isbn": "978-3-16-148410-0",
    "publisher": "little book",
    "publicationDate": "1992-03-31",
    "price": 611
  }
]
```
