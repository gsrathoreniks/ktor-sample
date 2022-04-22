package com.example.routes

import com.example.models.Customer
import com.example.models.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/*
* Extension function for adding routes for "Customer"
* */
fun Route.customerRouting() {
    /*
    * This is the parent route ["/customer"], all the requests get,put,post,delete
    * will be under same route.
    *
    * */
    route("/customer") {

        /*
        * Sample GET-REQUEST
        **/
        get {
            /*
            * Checks the customerStorage:-
            * if Not Empty -> returning customerStorage
            * if Empty -----> returning ["No customers found"]
            * */
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }

        /*
        * Sample GET-REQUEST with parameter named ["id"]
        * */
        get("{id?}") {
            /*
            * Check if parameter is missing or not
            * if Missing -------> responding with status as BadRequest
            * */
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            /* if ID Not Found --> responding withs status as NotFound*/
            val customer =
                customerStorage.find { it.id == id } ?: return@get call.respondText(
                    "No customer with id $id",
                    status = HttpStatusCode.NotFound
                )
            /* if Not Missing ---> finding the customer with ["id"] and returning the customer*/
            call.respond(customer)
        }

        /*
        * Sample POST-REQUEST
        **/
        post {
            /* Receiving the body[Customer] using [call.receive] method */
            val customer = call.receive<Customer>()
            /* Adding the received customer into the storage */
            customerStorage.add(customer)
            /* Responding with [Created] status and message */
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }

        /*
        * Sample DELETE-REQUEST with PARAMETERS
        * */
        delete("delete/{id?}") {
            /* Getting parameter [ID] and if Id is missing then responding as BAD REQUEST */
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

            /* Checking if id is present in [customerStorage]
            *  if Present -----> removing the Customer based on ID and responding as ACCEPTED
            *  if Not Present--> responding with status NOT-FOUND
            * */
            if (customerStorage.removeIf { it.id == id }) {
                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}