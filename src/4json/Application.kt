package `4json`

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.ContentType.Application.Xml
import io.ktor.request.receive
import io.ktor.request.receiveText
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import kotlin.math.roundToInt

// Handling JSON

data class Person(val name: String, val age: Int)

fun Application.json() {

    // A feature
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {

        get("/resources/{resourceId}") {

            val name = call.parameters["resourceId"] ?: "no name provided"
            val person = Person(name.replace("-", " "), (Math.random() * 100.0).roundToInt())

            call.respond(person)

        }

        post("/resources") {

            val person = call.receive(Person::class)
            call.respondText("Saving a person named: ${person.name}")

        }
    }
}

