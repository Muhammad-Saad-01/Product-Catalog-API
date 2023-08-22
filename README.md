# Product Catalog API

This API provides a RESTful interface for managing products, brands, and categories with the following features:

* Create, read, update, and delete products
* Create, read, update, and delete brands
* Create, read, update, and delete categories
* Search for products by name, brand, or category
* Filter products by price, availability, and other criteria

## Getting Started

To get started with the API, you will need to install the following dependencies:

* Java 17
* Maven
* Postgresql 

Once you have installed the dependencies you can clone the repository 
```
https://github.com/Null-Pointer-Squad/Product-Catalog-API.git
```
then overwrite your configuration details (`Database` , `Port Number`, etc) to the configuration file, and run the following command to start the API:

```
mvn spring-boot:run
```


If you didn't change the port, The API will be available on port 8008. You can use Swagger to explore the API documentation at http://localhost:8080/swagger-ui.html.
 
## Usage

To use the API, you will need to make RESTful requests to the following endpoints:

* `/products`: The endpoint for managing products
* `/brands`: The endpoint for managing brands
* `/categories`: The endpoint for managing categories

For more information on how to use the API, please refer to the Swagger documentation.

## Technologies

The following technologies are used in the development of the API:

* Springboot
* SpringDataJPA
* QueryDSL 
* MapStruct
* Lombok
* Postgresql
* Maven
* Swagger

## Contributing

Contributions to the API are welcome. Please submit your changes via pull request.

## License

The API is licensed under the Apache License, Version 2.0.
