package `7combination`.pages

import `7combination`.services.*
import kotlinx.html.li
import kotlinx.html.ul

fun usersListPage(users: List<User>) =
    basePage("Users") {
        ul {
            users.forEach {
                li {
                    +"User: ${it.name} with ${it.age}"
                }
            }
        }
    }