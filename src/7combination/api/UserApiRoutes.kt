package `7combination`.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import `7combination`.services.*
import `7combination`.features.*
import io.ktor.routing.*

fun Route.userApiRoutes() {

    route("/users") {

        get("/") {
            call.respond(userService.allUsers())
        }

        post {
            val user = call.receive(User::class)
            userService.saveUser(user)
            call.respond(HttpStatusCode.OK)
        }

        get("/{name}") {
            val name = call.parameters["name"] ?: ""
            val user = userService.getUserByName(name)

            if (user == null)
                call.respond(HttpStatusCode.NotFound)
            else
                call.respond(user)
        }

    }
}
