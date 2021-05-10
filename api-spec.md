### Person API

#### Create a new person

> **POST** /v1/person

Request body:

 ```json
 {
  "fullName": "Sok San",
  "dateOfBirth": "01-01-2020"
}
 ```

Response body:

 ```json
 {
  "id": 1,
  "fullName": "Sok San",
  "dateOfBirth": "01-01-2020",
  "createdDate": "01-01-2021"
}
```
