package `1helloworld`

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


// Starting a server + modules

// Application
// Running is Application environment
fun main() {

    // Select server to use, can be:
    // - Tomcat
    // - Netty
    // - Jetty
    // - CIO
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/demo") {
                call.respondText("HELLO WORLD!")
            }
        }
    }

    // Prevent main from exiting
    server.start(wait = true)
}

// Module
fun Application.helloWorld() {
    routing {
        get("/") {
            call.respondText("HELLO WORLD!")
        }
    }
}

