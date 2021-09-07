
# Product and Category Management REST Controller

This my first spring boot project to learn spring framework. while writing this project I learned best practices for project structure, code structure,ensure the code is loosely coupled and that all the layers in the application are completely independent of each other and talk using neutral objects.

I have used pure JDBC classes to implement this project.

I have also provided pagination api using pure JDBC classes.

Learnings:

JDBC

Collections

Dependency injection

HTTP methods and code

REST request and response

SQL queries

Centralized exception handling

Used MYSQL Database


## Documentation

[Documentation](https://linktodocumentation)

  This project has two important entities:

    1. Product 
    2. Category

    We can perform crud operation on database using REST apis.
    We can also link product to categories.
    Provided valid validations

## Database Schema

<a href="https://ibb.co/qRnzxrH"><img src="https://i.ibb.co/sFPhtVT/Whats-App-Image-2021-09-03-at-10-20-21-AM.jpg" alt="Whats-App-Image-2021-09-03-at-10-20-21-AM" border="0"></a>


## Sample API Reference

#### Get all products

```http
  GET "http://localhost:8080/api/products"
```



#### Get product

```http
  GET "http://localhost:8080/api/products"
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | **Required**. Name of item to fetch |




## 🚀 About Me
I'm a fresher java back end developer , completed bachelor's degree in computer engineering in 2020.

Finding intership opptunities.

  