package `7combination`.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.movieApiRoutes() {
    route("/movies") {

        get("/") {
            call.respond(OK)
        }
    }
}