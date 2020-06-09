package `6htmldsl`

import io.ktor.application.*
import io.ktor.html.respondHtml
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.*

// HTML dsl

// For html templating you can use:
// - Freemarker
// - Mustache
// - Thymeleaf

// But html dsl... seems nicer :)
// Also exists for CSS ...

fun Application.htmlDsl() {

    routing {
        static("/static") {
            resources("static")
        }

        get("/html") {
            call.respondHtml {

                head {
                    link("/static/basic.css", rel = "stylesheet")
                    meta(charset = "UTF-8")
                }

                body {

                    h1 {
                        +"KTOR html dsl <hr />"
                    }

                    h2 {
                        unsafe {
                            raw("Testing <hr />")
                        }
                    }

                    ul {

                        // Comes close to JSX ;)
                        (1..10).forEach {
                            li {
                                +"$it"
                            }
                        }
                    }
                }
            }
        }
    }
}

