package `7combination`.features

import io.ktor.application.*
import io.ktor.util.AttributeKey
import `7combination`.services.*
import io.ktor.util.pipeline.PipelineContext

class UserServiceFeature(private val config: Configuration) {

    val userService = UserService()

    class Configuration {
        // Database config here
    }

    companion object Feature : ApplicationFeature<Application, Configuration, UserServiceFeature> {

        override val key = AttributeKey<UserServiceFeature>("UserServiceFeature")

        override fun install(pipeline: Application, configure: Configuration.() -> Unit): UserServiceFeature {

            val config = Configuration().apply(configure)
            val feature = UserServiceFeature(config)

            pipeline.intercept(ApplicationCallPipeline.Setup) {
                call.attributes.put(key, feature)
            }

            return feature
        }
    }
}

// Make it accessible in a pipeline context
inline val PipelineContext<*, ApplicationCall>.userService: UserService
    get() = call.attributes.get(UserServiceFeature.key).userService
