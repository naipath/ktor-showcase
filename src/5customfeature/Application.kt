package `5customfeature`

import io.ktor.application.*
import io.ktor.response.header
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.AttributeKey

// Custom feature

fun Application.customFeature() {

    install(CustomDefaultHeaderFeature) {
        name = "X-Powered-By"
        value = "OpenValue"
    }

    routing {
        get("/custom-feature") {
            call.respondText("a response")
        }
    }
}

class CustomDefaultHeaderFeature(private val config: Configuration) {

    class Configuration {
        var name: String = "testing"
        var value: String = "value"
    }

    private fun intercept(call: ApplicationCall) {
        call.response.header(config.name, config.value)
    }

    companion object Feature : ApplicationFeature<Application, Configuration, CustomDefaultHeaderFeature> {

        override val key = AttributeKey<CustomDefaultHeaderFeature>("Custom feature")

        override fun install(pipeline: Application, configure: Configuration.() -> Unit): CustomDefaultHeaderFeature {

            val config = Configuration().apply(configure)
            val feature = CustomDefaultHeaderFeature(config)

            // IMPORTANT BIT
            pipeline.intercept(ApplicationCallPipeline.Features) { feature.intercept(call) }

            return feature
        }
    }
}

// Ktor under the hood is a pipeline with multiple phases and some default features
// Can be abused for CI/CD with extensions

// Phases
//            Setup       for preparing the call, and processing attributes
//            Monitoring  for tracing calls: logging, metrics, error handling etc.
//            Features    for infrastructure features, most intercept at this phase
//            Call        for processing a call and sending a response
//            Fallback    for handling unprocessed calls


