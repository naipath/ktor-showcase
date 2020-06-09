package `7combination`.pages

import kotlinx.html.*

fun basePage(title: String, content: DIV.() -> Unit): HTML.() -> Unit = {
    head {
        meta(charset = "UTF-8")
        link("/static/basic.css", rel = "stylesheet")
    }
    body {
        div("container") {
            h1 { +title }
            hr {}


            content.invoke(this)
        }
    }
}
