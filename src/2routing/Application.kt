package `2routing`

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType.Application.Xml
import io.ktor.request.receiveText
import io.ktor.response.header
import io.ktor.response.respondText
import io.ktor.routing.*

// Interacting with `call`

fun Application.routing() {

    routing {

        route("/resources") {

            get("/") {

                call.respondText("spitting out all resources")

            }

            post("/") {

                val input = call.receiveText()
                call.respondText("saved input: $input")

            }

            contentType(Xml) {

                get("/{resourceId}") {

                    val resourceId = call.parameters["resourceId"]

                    call.response.header("X-Powered-by", "OpenValue")
                    call.respondText("Requested $resourceId")

                }
            }
        }

    }
}

