package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(val id: String, val firstName: String, val lastName: String, val email: String)


/* Hardcoded list of customers for understanding the concepts of HTTP-REQUESTS (GET, POST, DELETE, PUT)
* Without worrying about the database connection and complexities.
*/
val customerStorage = mutableListOf(
    Customer("1","Amit","Birthalia","amit@gmail.com"),
    Customer("2","Avinash","Singh","avi@gmail.com"),
    Customer("3","Bipul","Devashish","bipsi@gmail.com"),
    Customer("4","Aditya","Kumar","adi@gmail.com"),
    Customer("5","Gaurav","Kumar","gaurav@gmail.com"),
)