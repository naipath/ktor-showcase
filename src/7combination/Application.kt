package `7combination`

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.gson.gson
import io.ktor.routing.route
import io.ktor.routing.routing
import `7combination`.api.*
import `7combination`.pages.*
import `7combination`.features.*
import io.ktor.application.call
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.path
import io.ktor.routing.get
import org.slf4j.event.Level

// A more complete example with more splitting of resources

fun Application.combination() {

    // Setup of the application
    install(DefaultHeaders) {
        header("X-Powered-By", "OpenValue")
    }
    install(ContentNegotiation) {
        gson { }
    }
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
    install(UserServiceFeature) {}

    // Start with routing
    routing {
        static("/static") {
            resources("static")
        }

        // Api routes
        route("/api") {

            userApiRoutes()

            movieApiRoutes()
        }

        // Page routes

        get("/") {
            call.respondHtml(OK, homePage())
        }

        get("/movies") {
            call.respondHtml(OK, movieListPage())
        }

        get("/users") {
            val allUsers = userService.allUsers()
            call.respondHtml(OK, usersListPage(allUsers))
        }
    }
}

// Ktor gripes
//
// - CDI???
// - Type locations? (Yes but experimental)
// - Extending the types of Route ...
// - Async services is still hard ...
